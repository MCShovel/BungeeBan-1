package fr.lambertz.robin.bungeeban.command;

import fr.lambertz.robin.bungeeban.BanManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class UnbanIpCommand extends Command {

	public UnbanIpCommand() {
		super("unbanip", "bungeeban.command.unbanip");
	}
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length != 2) {
			sender.sendMessage(ChatColor.RED + "Wrong command format. <required> [optional]");
			sender.sendMessage(ChatColor.RED + "/unbanip <ip> <server>");
			return;
		} 
		BanManager.unban(args[0], args[1]);
		sender.sendMessage(ChatColor.RED + args[0] + " has been unbanned");
	}
}