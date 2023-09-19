package me.allvaa.breadcaster.commands;

import me.allvaa.breadcaster.Breadcaster;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.jetbrains.annotations.NotNull;


public class ReloadConfig extends BukkitCommand {
    private Breadcaster plugin;
    public ReloadConfig(Breadcaster plugin) {
        super("breadcasterrc");
        this.plugin = plugin;
        this.setPermission("breadcaster.reloadconfig");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        plugin.reloadConfig();
        sender.sendMessage("Reloaded config!");
        return false;
    }
}
