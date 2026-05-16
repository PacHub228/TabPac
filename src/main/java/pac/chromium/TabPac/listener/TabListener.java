package pac.chromium.TabPac.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pac.chromium.TabPac.TabPac;
import pac.chromium.TabPac.utils.ColorUtils;

public class TabListener implements Listener {
    private final TabPac plugin;

    public TabListener(TabPac plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        // Update the player who joined
        plugin.getTabManager().updatePlayer(event.getPlayer());
        plugin.getScoreboardManager().updatePlayer(event.getPlayer());
        
        // Also update everyone else to refresh online count if needed
        plugin.getTabManager().updateAll();

        // Notify admin if PAPI is missing or expansions are not registered
        if (event.getPlayer().hasPermission("tabpac.admin")) {
            String prefix = plugin.getMessageManager().getPrefix();
            
            if (!plugin.isPlaceholderApiEnabled()) {
                event.getPlayer().sendMessage(ColorUtils.format(prefix + plugin.getMessageManager().getMessage("papi-missing")));
            } else {
                // Check if specific expansions are missing
                boolean missingExpansions = false;
                if (!me.clip.placeholderapi.PlaceholderAPI.isRegistered("player")) missingExpansions = true;
                if (!me.clip.placeholderapi.PlaceholderAPI.isRegistered("server")) missingExpansions = true;
                if (!me.clip.placeholderapi.PlaceholderAPI.isRegistered("luckperms")) missingExpansions = true;

                if (missingExpansions) {
                    event.getPlayer().sendMessage(ColorUtils.format(prefix + plugin.getMessageManager().getMessage("papi-expansions-missing")));
                    event.getPlayer().sendMessage(ColorUtils.format(prefix + plugin.getMessageManager().getMessage("papi-fix-hint")));
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        plugin.getScoreboardManager().removePlayer(event.getPlayer());
        // Update everyone else to refresh online count
        plugin.getTabManager().updateAll();
    }
}
