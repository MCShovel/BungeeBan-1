package fr.lambertz.robin.bungeeban.command;

import fr.lambertz.robin.bungeeban.banstore.IBanStore;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BanIpCommand extends Command {
	private IBanStore banstore;
	public BanIpCommand(IBanStore banstore) {
		super("banip", "bungeeban.command.banip");
		this.banstore = banstore;
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer player;
		if (sender instanceof ProxiedPlayer) {
			player = (ProxiedPlayer) sender;
		} else {
			sender.sendMessage(ChatColor.RED + "Console can't local-ban yet. Get your ass in-game.");
			return;
		}
		if (args.length < 1) {
			sender.sendMessage(ChatColor.RED + "Wrong command format. <required> [optional]");
			sender.sendMessage(ChatColor.RED + "/banip <ip> [reason]");
			return;
		} else if (args.length == 1) {
			banstore.banIP(args[0], player.getServer().getInfo().getName(), sender.getName(), "");
		} else {
			StringBuilder reasonBuilder = new StringBuilder();
			for (int i = 1;i < args.length; i++) {
				reasonBuilder.append(args[i]);
			}
			banstore.banIP(args[0], player.getServer().getInfo().getName(), sender.getName(), reasonBuilder.toString());
		}
	}
}
