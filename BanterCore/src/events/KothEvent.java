package events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.alexandeh.glaedr.scoreboards.Entry;
import com.alexandeh.glaedr.scoreboards.PlayerScoreboard;

import me.banterlol.bantercore.Main;
import net.md_5.bungee.api.ChatColor;
import subside.plugins.koth.events.KothEndEvent;
import subside.plugins.koth.events.KothInitializeEvent;

public class KothEvent implements Listener {
	
	@EventHandler
    public void onKothInitialize(KothInitializeEvent event){
		for (Player o : Bukkit.getOnlinePlayers()) {
		PlayerScoreboard scoreboard = PlayerScoreboard.getScoreboard(o);
         int timeleft = event.getRunningKoth().getTimeObject().getLengthInMinutes();
         if(scoreboard.getEntry("koth") == null) {
        	 new Entry("koth", scoreboard)
        	 .setCountdown(false)
        	 .setText(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("koth-color") + ChatColor.translateAlternateColorCodes('&', "koth-timer-color") + event.getRunningKoth().getTimeObject().getTimeLeftFormatted()))
        	 .setTime(timeleft)
        	 .send();
        	 return;
         }
         }
		}
	
	@EventHandler
	public void onKothStop(KothEndEvent e) {
		for (Player o : Bukkit.getOnlinePlayers()) {
			PlayerScoreboard scoreboard = PlayerScoreboard.getScoreboard(o);
			if(scoreboard.getEntry("koth") != null) {
				scoreboard.getEntry("koth").setCancelled(true);
			}
		}
	}
    }
