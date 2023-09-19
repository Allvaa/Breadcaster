package me.allvaa.breadcaster.events;

import me.allvaa.breadcaster.Breadcaster;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PlayerJoin implements Listener {
    private Breadcaster plugin;
    public PlayerJoin(Breadcaster plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.runningTask == null || plugin.runningTask.isCancelled()) {
            plugin.runningTask = plugin.getServer().getAsyncScheduler().runAtFixedRate(plugin, (scheduledTask) -> {
                String message =
                        plugin.getConfig().getString("prefix") +
                                plugin.getConfig().getStringList("messages").get(new Random().nextInt(plugin.getConfig().getStringList("messages").size())) +
                                plugin.getConfig().getString("suffix");

                plugin.getServer().broadcast(MiniMessage.miniMessage().deserialize(message));
            }, 60L, plugin.getConfig().getLong("interval"), TimeUnit.SECONDS);
            plugin.getLogger().info("Started automatic broadcast.");
        }
    }
}
