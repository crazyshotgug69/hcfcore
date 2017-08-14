package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.banterlol.bantercore.Main;

public class HelpCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(!sender.hasPermission("bantercore.command.help")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("nopermmessage")));
			return true;
		}
		for(String helpcmd : Main.getInstance().config.getStringList("help")) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', helpcmd));
		}
		return false;
	
	}

}
