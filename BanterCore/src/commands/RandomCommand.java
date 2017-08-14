package commands;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.banterlol.bantercore.Main;
import net.md_5.bungee.api.ChatColor;

public class RandomCommand implements CommandExecutor {
	
	ArrayList<Player> players = new ArrayList<Player>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("random")) {
			if(!p.hasPermission("bantercore.command.random")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("noperm-message")));
			}
			for (Player e : Bukkit.getOnlinePlayers()) players.add(e);
			Player randomPlayer = players.get(new Random().nextInt(players.size()));
			p.teleport(randomPlayer.getLocation());
			p.sendMessage(ChatColor.GREEN + "You have been randomly teleported to " + randomPlayer.getName());
		}
		return true;
	}

}
