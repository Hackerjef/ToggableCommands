package dev.nadie.togglecommand.Commands;

import dev.nadie.togglecommand.Etc.SettingsManager;
import dev.nadie.togglecommand.Togglecommand;
import dev.nadie.togglecommand.Etc.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Locale;

public class Runner implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Utils.getString("strings.usage"));
            return true;
        }

        if (!sender.hasPermission("tc.cmd." + args[0])) {
            sender.sendMessage(Utils.getString("strings.access_denied"));
            return true;
        }

        if (SettingsManager.getConfig().get("commands." + args[0].toLowerCase(Locale.ROOT)) == null) {
            sender.sendMessage(Utils.getString("strings.cmd_notfound"));
            return true;
        };



        boolean cmd_enabled = SettingsManager.getConfig().getBoolean(String.format("commands.%s.enabled", args[0].toLowerCase(Locale.ROOT)), false);
        String actualCommand = SettingsManager.getConfig().getString(String.format("commands.%s.run", args[0].toLowerCase(Locale.ROOT)));
        if (cmd_enabled) {
            Togglecommand.pl.getServer().dispatchCommand(sender, actualCommand);

            if (SettingsManager.getConfig().getString("strings.ran_successfully") != null) {
                sender.sendMessage(Utils.getString("strings.ran_successfully"));
            }
        } else {
            sender.sendMessage(Utils.getString("strings.cmd_disabled"));
        }

        return true;
    }
}
