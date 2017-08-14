package events;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.alexandeh.glaedr.scoreboards.Entry;
import com.alexandeh.glaedr.scoreboards.PlayerScoreboard;

import me.banterlol.bantercore.Main;

public class WarOutbreakEvent implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		PlayerScoreboard scoreboard = PlayerScoreboard.getScoreboard(p);
		if(cmd.getName().equalsIgnoreCase("waroutbreak")) {
			if(!p.hasPermission("bantercore.command.waroutbreak")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("noperm-message")));
			}
			if(args.length == 0) {
				p.sendMessage(ChatColor.RED + "Usage: /waroutbreak <start|pause|unpause|stop>");
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("start")) {
					if(scoreboard.getEntry("waroutbreak") == null) {
						new Entry("waroutbreak", scoreboard)
						.setCountdown(true)
						.setText(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("war-outbreak")))
						.setTime(1800)
						.send();
					}
					else if(scoreboard.getEntry("waroutbreak") != null) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("war-outbreak-active")));
					}
				}
				if(args[0].equalsIgnoreCase("pause")) {
					if(scoreboard.getEntry("waroutbreak") != null) {
						scoreboard.getEntry("waroutbreak").setPaused(true);
						new Entry("waroutbreakpaused", scoreboard)
						.setCountdown(false)
						.setText(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("war-outbreak-paused")))
						.setTime(1800)
						.send();
					}
					else if(scoreboard.getEntry("waroutbreak") == null) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("war-outbreak-notactive")));
					}
					else if(scoreboard.getEntry("waroutbreakpaused") != null) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("war-outbreak-already-paused")));
					}
				}
				if(args[0].equalsIgnoreCase("unpause")) {
					if(scoreboard.getEntry("waroutbreakpaused") != null) {
						scoreboard.getEntry("waroutbreakpaused").setCancelled(true);
						scoreboard.getEntry("waroutbreak").setPaused(false);
					}
					if(scoreboard.getEntry("waroutbreakpaused") == null) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("war-outbreak-not-paused")));
					}
				}
				if(args[0].equalsIgnoreCase("stop")) {
					if(scoreboard.getEntry("waroutbreak") != null) {
						scoreboard.getEntry("waroutbreak").setCancelled(true);
					}
					if(scoreboard.getEntry("waroutbreak") == null) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("war-outbreak-notactive")));
					}
				}
			}
		}
		return true;
	}

}
