package pac.chromium.TabPac.manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pac.chromium.TabPac.TabPac;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MessageManager {
    private final TabPac plugin;
    private FileConfiguration messages;

    public MessageManager(TabPac plugin) {
        this.plugin = plugin;
        loadMessages();
    }

    public void loadMessages() {
        String lang = plugin.getConfig().getString("language", "en");
        String fileName = "languages/messages_" + lang + ".yml";
        
        File langDir = new File(plugin.getDataFolder(), "languages");
        if (!langDir.exists()) langDir.mkdirs();

        File file = new File(plugin.getDataFolder(), fileName);

        // If file exists, we still want to make sure it has all the latest keys from the JAR
        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }

        messages = YamlConfiguration.loadConfiguration(file);

        // Load internal resource to fill missing keys in the file on disk
        try (InputStream defStream = plugin.getResource(fileName)) {
            if (defStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defStream, StandardCharsets.UTF_8));
                boolean modified = false;
                for (String key : defConfig.getKeys(true)) {
                    if (!messages.contains(key)) {
                        messages.set(key, defConfig.get(key));
                        modified = true;
                    }
                }
                if (modified) {
                    messages.save(file);
                }
            }
        } catch (Exception e) {
            plugin.getLogger().severe("Could not sync messages for " + fileName);
        }
    }

    public String getMessage(String path) {
        return messages.getString(path, "Missing message: " + path);
    }

    public String getPrefix() {
        return getMessage("prefix");
    }
}
