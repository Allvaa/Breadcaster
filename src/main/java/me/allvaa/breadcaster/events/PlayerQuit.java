package me.allvaa.breadcaster.events;

import me.allvaa.breadcaster.Breadcaster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    private Breadcaster plugin;
    public PlayerQuit(Breadcaster plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (plugin.getServer().getOnlinePlayers().size() <= 1) {
            plugin.runningTask.cancel();
            plugin.getLogger().info("Stopped automatic broadcast.");
        }
    }
}
