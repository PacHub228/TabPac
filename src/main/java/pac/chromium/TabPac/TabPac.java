package pac.chromium.TabPac;

import org.bukkit.plugin.java.JavaPlugin;

import pac.chromium.TabPac.command.TabCommand;
import pac.chromium.TabPac.listener.TabListener;
import pac.chromium.TabPac.manager.AnimationManager;
import pac.chromium.TabPac.manager.MessageManager;
import pac.chromium.TabPac.manager.ScoreboardManager;
import pac.chromium.TabPac.manager.TabManager;
import pac.chromium.TabPac.utils.ColorUtils;

public final class TabPac extends JavaPlugin {
    private TabManager tabManager;
    private MessageManager messageManager;
    private AnimationManager animationManager;
    private ScoreboardManager scoreboardManager;
    private boolean placeholderApiEnabled;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        
        this.messageManager = new MessageManager(this);
        this.animationManager = new AnimationManager(this);
        this.scoreboardManager = new ScoreboardManager(this);
        
        this.placeholderApiEnabled = getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");
        if (placeholderApiEnabled) {
            getLogger().info("PlaceholderAPI found and hooked!");
        } else {
            getLogger().warning("PlaceholderAPI not found! Some features may not work.");
        }
        
        this.tabManager = new TabManager(this);
        this.tabManager.startTask();
        
        // Start Animation and Scoreboard tasks
        getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {
            animationManager.update();
            scoreboardManager.updateAll();
        }, 0L, 1L); // Fast update for animations, scoreboard has its own interval logic if needed
        
        getServer().getPluginManager().registerEvents(new TabListener(this), this);
        
        getCommand("tabpac").setExecutor(new TabCommand(this));
        getCommand("tabpac").setTabCompleter(new TabCommand(this));
        
        String logo = getConfig().getString("branding.logo", "&b&lTab&f&lPac");
        getServer().getConsoleSender().sendMessage(ColorUtils.format("&8&m----------------------------------------"));
        getServer().getConsoleSender().sendMessage(ColorUtils.format("  " + logo + " &7v" + getDescription().getVersion()));
        getServer().getConsoleSender().sendMessage(ColorUtils.format("  &aPlugin successfully enabled!"));
        getServer().getConsoleSender().sendMessage(ColorUtils.format("&8&m----------------------------------------"));
    }

    public TabManager getTabManager() {
        return tabManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

    public AnimationManager getAnimationManager() {
        return animationManager;
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public boolean isPlaceholderApiEnabled() {
        return placeholderApiEnabled;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
