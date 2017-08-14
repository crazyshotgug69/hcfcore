package events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.alexandeh.glaedr.scoreboards.Entry;
import com.alexandeh.glaedr.scoreboards.PlayerScoreboard;

import me.banterlol.bantercore.Main;
import net.md_5.bungee.api.ChatColor;

public class CombatTagEvent implements Listener {
	
	int time = Main.getInstance().config.getInt("combat-tag-time");
	
	@EventHandler
	public void playerDamageEvent(EntityDamageByEntityEvent e) {
		Player p = (Player) e.getEntity();
		Player t = (Player) e.getDamager();
		PlayerScoreboard scoreboard = PlayerScoreboard.getScoreboard(p);
		PlayerScoreboard scoreboardt = PlayerScoreboard.getScoreboard(t);
		if(scoreboard.getEntry("combattag") == null) {
			new Entry("combattag", scoreboard).setCountdown(true).setText(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("combattag"))).setTime(30).send();
		}
		else if(scoreboard.getEntry("combattag") != null) {
			scoreboard.getEntry("combattag").setTime(time);
		}
		if(scoreboardt.getEntry("combattag") == null) {
			new Entry("combattag", scoreboard).setCountdown(true).setText(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("combattag"))).setTime(30).send();
		}
		if(scoreboardt.getEntry("combattag") != null) {
			scoreboardt.getEntry("combattag").setTime(time);
		}
	}

}
