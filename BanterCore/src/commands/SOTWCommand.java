package commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.alexandeh.glaedr.scoreboards.Entry;
import com.alexandeh.glaedr.scoreboards.PlayerScoreboard;

import me.banterlol.bantercore.Main;
import net.md_5.bungee.api.ChatColor;

public class SOTWCommand implements CommandExecutor {
	
ArrayList<Player> sotw = new ArrayList<Player>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		PlayerScoreboard scoreboard = PlayerScoreboard.getScoreboard(p);
		if(cmd.getName().equalsIgnoreCase("sotw")) {
			if(p.hasPermission("legionmc.sotw")) {
				if(args.length == 0) {
					p.sendMessage(ChatColor.RED + "Usage: /sotw <start|pause|unpause|stop>");
				}
				for (Player o : Bukkit.getOnlinePlayers()) {
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("start")) {
						if(scoreboard.getEntry("sotw") == null) {
						new Entry("sotw", scoreboard)
						.setCountdown(true)
						.setText(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("sotw")))
						.setTime(7200)
						.send();
						sotw.add(o);
						}else{
							p.sendMessage(ChatColor.RED + "SOTW is already active!");
						}
					}
					if(args[0].equalsIgnoreCase("stop")) {
						if(!(scoreboard.getEntry("sotw") == null)) {
							scoreboard.getEntry("sotw").setCancelled(true);
							sotw.remove(o);
						}else{
							p.sendMessage(ChatColor.RED + "SOTW is not active!");
						}
					}
					if(args[0].equalsIgnoreCase("pause")) {
						if(!(scoreboard.getEntry("sotw") == null)) {
							scoreboard.getEntry("sotw").setPaused(true);
							new Entry("paused", scoreboard)
							.setCountdown(false)
							.setText(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("sotw-paused")))
							.setTime(7200)
							.send();
						}else{
							p.sendMessage(ChatColor.RED + "SOTW is not active!");
						}
					}
					if(args[0].equalsIgnoreCase("unpause")) {
						if(!(scoreboard.getEntry("sotw") == null)) {
							if(!(scoreboard.getEntry("paused") == null)) {
								scoreboard.getEntry("paused").setCancelled(true);
								scoreboard.getEntry("sotw").setPaused(false);
							}else{
								p.sendMessage(ChatColor.RED + "SOTW is not paused!");
							}
						}else{
							p.sendMessage(ChatColor.RED + "SOTW is not active!");
						}
					}
				}
				}
			}else{
				Main.getInstance();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.config.getString("noperm-message")));
			}
		}
		return true;
	}
	{
		{
	}
		
		

	
	
		
	


}

}
