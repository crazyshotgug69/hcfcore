package events;

import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.alexandeh.glaedr.scoreboards.Entry;
import com.alexandeh.glaedr.scoreboards.PlayerScoreboard;

import me.banterlol.bantercore.Main;

public class KitmapEvent implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		PlayerScoreboard scoreboard = PlayerScoreboard.getScoreboard(p);
		if(scoreboard.getEntry("kills") == null) {
			new Entry("kills", scoreboard).setCountdown(false).setText(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("kills") + " " + p.getStatistic(Statistic.PLAYER_KILLS))).setTime(1).send();
		}
	}

}
