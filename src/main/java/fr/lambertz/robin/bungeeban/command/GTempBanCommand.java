package fr.lambertz.robin.bungeeban.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import fr.lambertz.robin.bungeeban.BanManager;
import fr.lambertz.robin.bungeeban.banstore.BanEntry;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class GTempBanCommand extends Command {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("d'd'k'h'm'm's's'");
	
	public GTempBanCommand() {
		super("gtempban", "bungeeban.command.gtempban");
	}
	

	@Override
	public void execute(CommandSender sender, String[] args) {
		BanEntry newban;
		
		if (args.length < 2) {
			sender.sendMessage(ChatColor.RED + "Wrong command format. <required> [optional]");
			sender.sendMessage(ChatColor.RED + "/gtempban <ip> <time> [reason]");
			return;
		}
		
		try {
			newban = new BanEntry(args[0])
						.setGlobal()
						.setSource(sender.getName())
						.setExpiry(dateFormat.parse(args[1]));
		} catch (ParseException e) {
			sender.sendMessage(ChatColor.RED + "Wrong time format.");
			sender.sendMessage(ChatColor.RED + "#d#h#m#s");
			return;
		} 
		
		if (args.length > 2) {
			StringBuilder reasonBuilder = new StringBuilder();
			for (int i = 1;i < args.length; i++) {
				reasonBuilder.append(args[i]);
			}
			newban.setReason(reasonBuilder.toString());
		}
		BanManager.ban(newban);
		sender.sendMessage(ChatColor.RED + newban.getBanned() + " has been banned.");
	}
}
