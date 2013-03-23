package net.craftminecraft.bungee.bungeeban.command;


import net.craftminecraft.bungee.bungeeban.BanManager;
import net.craftminecraft.bungee.bungeeban.banstore.BanEntry;
import net.craftminecraft.bungee.bungeeban.util.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class GBanIpCommand extends Command {
	public GBanIpCommand() {
		super("gbanip");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		BanEntry.Builder newban;
		if (args.length < 1) {
			sender.sendMessage(ChatColor.RED + "Wrong command format. <required> [optional]");
			sender.sendMessage(ChatColor.RED + "/gbanip <username/ip> [reason]");
			return;
		}

		newban = new BanEntry.Builder(args[0])
					.global()
					.source(sender.getName());
		
		if (args.length > 1) {
			newban.reason(Utils.buildReason(args, 1));
		}
		newban.ipban();
		BanEntry entry;
		try {
			entry = newban.build();
		} catch (IllegalArgumentException ex) {
			sender.sendMessage(ChatColor.RED + ex.getMessage());
			return;
		}
		if (!Utils.hasPermission(sender, "gbanip", args[0])) {
			sender.sendMessage(ChatColor.RED + "You don't have permission to do this.");
			return;
		}
		if (BanManager.ban(entry)) {
			sender.sendMessage(ChatColor.RED + entry.getBanned() + " has been banned.");
		} else {
			sender.sendMessage(ChatColor.RED + "An error has occured. Check the proxy.log or notify an admin.");
		}
	}
}
