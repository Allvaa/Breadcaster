package me.allvaa.breadcaster;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import me.allvaa.breadcaster.commands.ReloadConfig;
import me.allvaa.breadcaster.events.PlayerJoin;
import me.allvaa.breadcaster.events.PlayerQuit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Breadcaster extends JavaPlugin {
    public ScheduledTask runningTask;
    @Override
    public void onEnable() {
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);

        getServer().getCommandMap().register("bcrc", new ReloadConfig(this));

        getLogger().info("Waiting players to join to start the automatic broadcast.");
    }

    @Override
    public void onDisable() {
        if (runningTask == null || runningTask.isCancelled()) return;
        runningTask.cancel();
    }
}
