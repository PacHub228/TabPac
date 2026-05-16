package pac.chromium.TabPac.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class ColorUtils {
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
    private static final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.legacyAmpersand();

    public static Component format(String text) {
        if (text == null) return Component.empty();
        // Support both MiniMessage and Legacy color codes
        if (text.contains("<") && text.contains(">")) {
            return MINI_MESSAGE.deserialize(text);
        }
        return LEGACY_SERIALIZER.deserialize(text);
    }

    public static String legacy(String text) {
        if (text == null) return "";
        return LegacyComponentSerializer.legacySection().serialize(format(text));
    }
}
