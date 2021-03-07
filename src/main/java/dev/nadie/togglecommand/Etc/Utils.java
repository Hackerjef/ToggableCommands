package dev.nadie.togglecommand.Etc;

import dev.nadie.togglecommand.Etc.SettingsManager;
import net.md_5.bungee.api.ChatColor;

public class Utils {
    public static String getString(String Path) {
        String returnStr;
        String value = SettingsManager.getConfig().getString(Path);

        if (value != null) {
            returnStr = value;
        } else {
            returnStr = ChatColor.RED + Path;
        }
        return ChatColor.translateAlternateColorCodes('§', returnStr);
    }
}
