package events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.alexandeh.glaedr.scoreboards.Entry;
import com.alexandeh.glaedr.scoreboards.PlayerScoreboard;

import me.banterlol.bantercore.Main;
import net.md_5.bungee.api.ChatColor;

public class PvPTimer implements Listener, CommandExecutor {
	
	ArrayList<UUID> pvptimer = new ArrayList<UUID>();
	
	int time = Main.getInstance().config.getInt("pvptimer-time");
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		PlayerScoreboard scoreboard = PlayerScoreboard.getScoreboard(p);
		if(!p.hasPlayedBefore()) {
			if(Main.getInstance().config.getBoolean("kitmap") == false) {
				if(scoreboard.getEntry("pvptimer") == null) {
					new Entry("pvptimer", scoreboard)
					.setCountdown(true)
					.setText(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("pvp-timer")))
					.setTime(time)
					.send();
					return;
				}
			}
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		PlayerScoreboard scoreboard = PlayerScoreboard.getScoreboard(p);
		if(cmd.getName().equalsIgnoreCase("pvp")) {
			if(!p.hasPermission("bantercore.command.pvp")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("noperm-message")));
			}
			if(args.length == 0) {
				for(String pvptimermessage : Main.getInstance().config.getStringList("pvptimer-msg")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', pvptimermessage));
				}
			}
			if(args.length == 1) {
				if(!p.hasPermission("bantercore.command.pvp.enable")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("noperm-message")));
				}
				if(args[0].equalsIgnoreCase("enable")) {
					if(scoreboard.getEntry("pvptimer") == null) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("nopvptimer")));
					}
					if(scoreboard.getEntry("pvptimer") != null) {
						scoreboard.getEntry("pvptimer").setCancelled(true);
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("pvp-enabled")));
					}
					
				}
			}
			if(args.length == 2) {
				if(!p.hasPermission("bantercore.command.pvp.give")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("noperm-message")));
				}
				Player t = (Player) Bukkit.getPlayerExact(args[1]);
				if(t == null) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("player-not-online")));
				}
				PlayerScoreboard scoreboardt = PlayerScoreboard.getScoreboard(t);
				if(scoreboardt.getEntry("pvptimer") == null) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("given-pvptimer")));
					new Entry("pvptimer", scoreboard)
					.setCountdown(true)
					.setText(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("pvp-timer")))
					.setTime(time)
					.send();
					return false;
				}
				else
				{
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("player-already-has-pvp-timer")));
				}
			}
		}
		return true;
	}

}
