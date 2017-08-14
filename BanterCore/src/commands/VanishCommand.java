package commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.banterlol.bantercore.Main;

public class VanishCommand implements CommandExecutor {
	
	private static VanishCommand instance;
	
	public static VanishCommand getInstance() {
		return instance;
	}
	
	ArrayList<Player> vanished = new ArrayList<Player>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("v")) {
			if(!p.hasPermission("bantercore.command.vanish")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("noperm-message")));
			}
			for (Player o : Bukkit.getOnlinePlayers()) {
			if(vanished.contains(p)) {
				vanished.remove(p);
				o.showPlayer(p);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("vanish-disabled")));
				return false;
			}
			if(!vanished.contains(p)) {
				vanished.add(p);
				o.hidePlayer(p);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("vanish-enabled")));
				return false;
			}
			}
		}
		return true;
	}

}
