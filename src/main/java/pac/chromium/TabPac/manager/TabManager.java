package pac.chromium.TabPac.manager;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import pac.chromium.TabPac.TabPac;
import pac.chromium.TabPac.utils.ColorUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TabManager {
    private final TabPac plugin;
    private final Set<String> warnedGroups = new HashSet<>();

    public TabManager(TabPac plugin) {
        this.plugin = plugin;
    }

    public void updateAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            updatePlayer(player);
        }
    }

    public void reload() {
        warnedGroups.clear();
        updateAll();
    }

    public void updatePlayer(Player player) {
        FileConfiguration config = plugin.getConfig();

        // Header and Footer
        List<String> headerLines = config.getStringList("tablist.header");
        List<String> footerLines = config.getStringList("tablist.footer");

        Component header = formatLines(player, headerLines);
        Component footer = formatLines(player, footerLines);

        player.sendPlayerListHeaderAndFooter(header, footer);

        // Prefix/Suffix handling via PlaceholderAPI (LuckPerms integration)
        String nameWithPrefix = player.getName();
        if (plugin.isPlaceholderApiEnabled()) {
            String prefix = "%luckperms_prefix%";
            
            // AFK Support
            if (config.getBoolean("afk.enabled", true)) {
                String afkStatus = PlaceholderAPI.setPlaceholders(player, "%essentials_is_afk%");
                if (afkStatus.equalsIgnoreCase("yes") || afkStatus.equalsIgnoreCase("true")) {
                    prefix = config.getString("afk.prefix", "&7[AFK] ") + prefix;
                }
            }
            
            nameWithPrefix = PlaceholderAPI.setPlaceholders(player, prefix + "%player_name%");
        }
        
        Component displayName = ColorUtils.format(nameWithPrefix);
        player.playerListName(displayName);

        // Sorting by groups
        setupScoreboardSorting(player);
    }

    public String replaceGlobalPlaceholders(Player player, String text) {
        if (text == null || text.isEmpty()) return "";
        
        // Replace animations
        while (text.contains("%animation:")) {
            int start = text.indexOf("%animation:");
            int end = text.indexOf("%", start + 11);
            if (end == -1) break;
            
            String animName = text.substring(start + 11, end);
            String frame = plugin.getAnimationManager().getFrame(animName);
            text = text.replace("%animation:" + animName + "%", frame);
        }
        
        // Replace PAPI
        if (plugin.isPlaceholderApiEnabled()) {
            text = PlaceholderAPI.setPlaceholders(player, text);
        }
        
        return text;
    }

    private void setupScoreboardSorting(Player player) {
        if (!plugin.isPlaceholderApiEnabled()) return;

        List<String> priorityList = plugin.getConfig().getStringList("groups-priority");
        String primaryGroup = PlaceholderAPI.setPlaceholders(player, "%luckperms_primary_group_name%").toLowerCase();
        
        // If PAPI returns the placeholder itself, it means the expansion is not working correctly
        if (primaryGroup.equals("%luckperms_primary_group_name%")) {
            primaryGroup = "default";
        }

        int priority = -1;
        String groupName = primaryGroup;

        for (int i = 0; i < priorityList.size(); i++) {
            String g = priorityList.get(i).toLowerCase();
            if (primaryGroup.equals(g)) {
                priority = i;
                break;
            }
        }

        // If group not found in priority list, move to bottom
        if (priority == -1) {
            priority = 999;
            if (!warnedGroups.contains(primaryGroup)) {
                notifyAdminsAboutMissingPriority(primaryGroup);
                warnedGroups.add(primaryGroup);
            }
        }

        // Team name format: 001_admin, 002_moderator, etc.
        String teamName = String.format("%03d_%s", priority, groupName);
        if (teamName.length() > 16) teamName = teamName.substring(0, 16);

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getTeam(teamName);
        
        if (team == null) {
            team = scoreboard.registerNewTeam(teamName);
        }

        if (!team.hasEntry(player.getName())) {
            // Remove from other teams first to avoid conflicts
            for (Team t : scoreboard.getTeams()) {
                if (t.hasEntry(player.getName()) && t.getName().matches("\\d{3}_.*")) {
                    t.removeEntry(player.getName());
                }
            }
            team.addEntry(player.getName());
        }
    }

    private void notifyAdminsAboutMissingPriority(String group) {
        String prefix = plugin.getMessageManager().getPrefix();
        String rawMessage = plugin.getMessageManager().getMessage("error-group-not-in-priority");
        String message = rawMessage.replace("{group}", group);
        
        String detail = "&7(Group &b" + group + " &7is assigned to player but not found in &bconfig.yml &7under &bgroups-priority&7)";
        
        for (Player admin : Bukkit.getOnlinePlayers()) {
            if (admin.hasPermission("tabpac.admin")) {
                admin.sendMessage(ColorUtils.format(prefix + message));
                admin.sendMessage(ColorUtils.format(prefix + detail));
            }
        }
        plugin.getLogger().warning(message + " " + detail);
    }

    private Component formatLines(Player player, List<String> lines) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            sb.append(replaceGlobalPlaceholders(player, lines.get(i)));
            if (i < lines.size() - 1) sb.append("\n");
        }
        return ColorUtils.format(sb.toString());
    }

    public void startTask() {
        int interval = Math.max(1, plugin.getConfig().getInt("update-interval-ticks", 20));
        Bukkit.getScheduler().runTaskTimer(plugin, this::updateAll, 0L, interval);
    }
}
