package pac.chromium.TabPac.manager;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import pac.chromium.TabPac.TabPac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationManager {
    private final TabPac plugin;
    private final Map<String, Animation> animations = new HashMap<>();

    public AnimationManager(TabPac plugin) {
        this.plugin = plugin;
        loadAnimations();
    }

    public void loadAnimations() {
        animations.clear();
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection section = config.getConfigurationSection("animations");
        if (section == null) return;

        for (String key : section.getKeys(false)) {
            List<String> frames = section.getStringList(key + ".frames");
            int interval = section.getInt(key + ".update-interval-ticks", 10);
            animations.put(key, new Animation(frames, interval));
        }
    }

    public String getFrame(String name) {
        Animation anim = animations.get(name);
        return anim != null ? anim.getCurrentFrame() : "";
    }

    public void update() {
        for (Animation anim : animations.values()) {
            anim.tick();
        }
    }

    private static class Animation {
        private final List<String> frames;
        private final int interval;
        private int currentFrameIndex = 0;
        private int ticks = 0;

        public Animation(List<String> frames, int interval) {
            this.frames = frames != null ? frames : new ArrayList<>();
            this.interval = interval;
        }

        public void tick() {
            if (frames.isEmpty()) return;
            ticks++;
            if (ticks >= interval) {
                ticks = 0;
                currentFrameIndex = (currentFrameIndex + 1) % frames.size();
            }
        }

        public String getCurrentFrame() {
            if (frames.isEmpty()) return "";
            return frames.get(currentFrameIndex);
        }
    }
}
