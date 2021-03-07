package dev.nadie.togglecommand;

import dev.nadie.togglecommand.Commands.Runner;
import dev.nadie.togglecommand.Commands.TCcmd;
import dev.nadie.togglecommand.Etc.SettingsManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Togglecommand extends JavaPlugin {
    public static Togglecommand pl;
    public static Logger plogger;

    @Override
    public void onEnable() {
        pl = this;
        plogger = pl.getLogger();
        SettingsManager.setup(pl, "config.yml");
        FileConfiguration gcfg = SettingsManager.getConfig();

        gcfg.options().copyDefaults(true);
        gcfg.addDefault("strings.ran_successfully", "§6Command Ran successfully");
        gcfg.addDefault("strings.access_denied", "§4Access denied");
        gcfg.addDefault("strings.usage", "/run <cmd>");
        gcfg.addDefault("strings.cmd_disabled", "§4 Sorry! Command is disabled");
        gcfg.addDefault("strings.cmd_notfound", "§4Command not found");

        if (gcfg.getString("commands") == null) {
            gcfg.addDefault("commands.expcmd.run", "help");
            gcfg.addDefault("commands.expcmd.enabled", true);
        }
        SettingsManager.saveConfig();

        this.getCommand("run").setExecutor(new Runner());
        this.getCommand("tc").setExecutor(new TCcmd());
    }
}
