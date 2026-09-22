package pac.chromium.TabPac.manager;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import pac.chromium.TabPac.TabPac;
import pac.chromium.TabPac.utils.ColorUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ScoreboardManager {
    private final TabPac plugin;
    private final Map<UUID, Scoreboard> scoreboards = new HashMap<>();
    private int ticks = 0;

    public ScoreboardManager(TabPac plugin) {
        this.plugin = plugin;
    }

    public void updateAll() {
        if (!plugin.getConfig().getBoolean("scoreboard.enabled", false)) {
            removeAll();
            return;
        }

        ticks++;
        int interval = Math.max(1, plugin.getConfig().getInt("scoreboard.update-interval-ticks", 20));
        if (ticks < interval) {
            // Still update existing scoreboards to refresh animations if they are in the title
            for (Player player : Bukkit.getOnlinePlayers()) {
                updateTitleOnly(player);
            }
            return;
        }
        ticks = 0;

        for (Player player : Bukkit.getOnlinePlayers()) {
            updatePlayer(player);
        }
    }

    private void updateTitleOnly(Player player) {
        Scoreboard scoreboard = scoreboards.get(player.getUniqueId());
        if (scoreboard == null) return;
        Objective objective = scoreboard.getObjective("tabpac_sb");
        if (objective == null) return;

        String title = plugin.getConfig().getString("scoreboard.title", "Scoreboard");
        objective.displayName(ColorUtils.format(plugin.getTabManager().replaceGlobalPlaceholders(player, title)));
    }

    public void updatePlayer(Player player) {
        if (!plugin.getConfig().getBoolean("scoreboard.enabled", false)) {
            removePlayer(player);
            return;
        }

        Scoreboard scoreboard = scoreboards.computeIfAbsent(player.getUniqueId(), k -> Bukkit.getScoreboardManager().getNewScoreboard());
        Objective objective = scoreboard.getObjective("tabpac_sb");
        
        if (objective == null) {
            objective = scoreboard.registerNewObjective("tabpac_sb", Criteria.DUMMY, ColorUtils.format(plugin.getConfig().getString("scoreboard.title", "Scoreboard")));
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        }

        FileConfiguration config = plugin.getConfig();
        List<String> lines = config.getStringList("scoreboard.lines");
        
        // Update title with animations/placeholders
        String title = config.getString("scoreboard.title", "Scoreboard");
        objective.displayName(ColorUtils.format(plugin.getTabManager().replaceGlobalPlaceholders(player, title)));

        // Remove old scores (simplistic way to refresh)
        for (String entry : scoreboard.getEntries()) {
            scoreboard.resetScores(entry);
        }

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String formatted = plugin.getTabManager().replaceGlobalPlaceholders(player, line);
            String legacyFormatted = ColorUtils.legacy(formatted);
            
            // Scoreboard lines are reversed in priority
            int score = lines.size() - i;
            objective.getScore(legacyFormatted.isEmpty() ? " ".repeat(i) : legacyFormatted).setScore(score);
        }

        if (player.getScoreboard() != scoreboard) {
            player.setScoreboard(scoreboard);
        }
    }

    public void removePlayer(Player player) {
        scoreboards.remove(player.getUniqueId());
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    public void removeAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            removePlayer(player);
        }
        scoreboards.clear();
    }
}
