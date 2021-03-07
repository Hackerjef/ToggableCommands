package dev.nadie.togglecommand.Commands;

import dev.nadie.togglecommand.Togglecommand;
import dev.nadie.togglecommand.Etc.SettingsManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Locale;

public class TCcmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.WHITE + "Usage: /tc <subcommand>");
            return true;
        }

        if (!sender.hasPermission("tc." + args[0])) {
            sender.sendMessage(ChatColor.RED + "Access Denied, missing tc." + args[0] + " perm");
            return true;
        }

        switch (args[0].toLowerCase(Locale.ROOT)) {
            case "reload":
                SettingsManager.reloadConfig();
                Togglecommand.plogger.info(ChatColor.DARK_GREEN + "Reload complete");
                sender.sendMessage(ChatColor.DARK_GREEN + "Reload complete");
                break;
            case "toggle":
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.WHITE + "Usage: /tc toggle <cmdname>");
                    return true;
                }

                if (SettingsManager.getConfig().get("commands." + args[1].toLowerCase(Locale.ROOT)) == null) {
                    sender.sendMessage(ChatColor.RED + "Command" + args[1].toLowerCase(Locale.ROOT) + "not found");
                    return true;
                };

                String path = "commands." + args[1].toLowerCase(Locale.ROOT) + ".enabled";
                boolean enabled =  SettingsManager.getConfig().getBoolean(path);
                SettingsManager.getConfig().set(path, !enabled);
                SettingsManager.saveConfig();
                SettingsManager.reloadConfig();
                Togglecommand.plogger.info(ChatColor.DARK_GREEN + "Command " + args[1].toLowerCase(Locale.ROOT) + " toggle has been set to " + !enabled);
                break;
            case "help":
                break;
            default:
                sender.sendMessage("Sub command not found");
                break;
        }

        return true;
    }
}
