package pac.chromium.TabPac.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pac.chromium.TabPac.TabPac;
import pac.chromium.TabPac.utils.ColorUtils;

import java.util.ArrayList;
import java.util.List;

public class TabCommand implements CommandExecutor, TabCompleter {
    private final TabPac plugin;

    public TabCommand(TabPac plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String prefix = plugin.getMessageManager().getPrefix();
        
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("tabpac.reload")) {
                    sender.sendMessage(ColorUtils.format(prefix + plugin.getMessageManager().getMessage("no-permission")));
                    return true;
                }
                
                plugin.reloadConfig();
                plugin.getMessageManager().loadMessages();
                plugin.getAnimationManager().loadAnimations();
                plugin.getTabManager().reload();
                plugin.getScoreboardManager().updateAll();
                sender.sendMessage(ColorUtils.format(prefix + plugin.getMessageManager().getMessage("config-reloaded")));
                return true;
            }

            if (args[0].equalsIgnoreCase("papi") && args.length > 1 && args[1].equalsIgnoreCase("download")) {
                if (!sender.hasPermission("tabpac.admin")) {
                    sender.sendMessage(ColorUtils.format(prefix + plugin.getMessageManager().getMessage("no-permission")));
                    return true;
                }

                sender.sendMessage(ColorUtils.format(prefix + plugin.getMessageManager().getMessage("papi-downloading")));
                
                // Execute PAPI commands via console
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "papi ecloud download Player");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "papi ecloud download Server");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "papi ecloud download LuckPerms");
                
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "papi reload");
                    sender.sendMessage(ColorUtils.format(prefix + plugin.getMessageManager().getMessage("papi-downloaded")));
                    plugin.getTabManager().updateAll();
                }, 100L); // Wait 5 seconds for downloads
                
                return true;
            }
        }

        sender.sendMessage(ColorUtils.format(prefix + plugin.getMessageManager().getMessage("version-info").replace("{version}", plugin.getDescription().getVersion())));
        sender.sendMessage(ColorUtils.format(plugin.getMessageManager().getMessage("help-reload")));
        sender.sendMessage(ColorUtils.format(plugin.getMessageManager().getMessage("help-papi")));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            if (sender.hasPermission("tabpac.reload")) completions.add("reload");
            if (sender.hasPermission("tabpac.admin")) completions.add("papi");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("papi")) {
            if (sender.hasPermission("tabpac.admin")) completions.add("download");
        }
        return completions;
    }
}
