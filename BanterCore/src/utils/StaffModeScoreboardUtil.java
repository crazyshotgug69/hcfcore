package utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.alexandeh.glaedr.scoreboards.Entry;
import com.alexandeh.glaedr.scoreboards.PlayerScoreboard;

import me.banterlol.bantercore.Main;
import me.diegom.hcmode.HCMode;
import net.md_5.bungee.api.ChatColor;

public class StaffModeScoreboardUtil {
	
	  private HCMode hCMode;
	  private Player p;
	  
	  public void registerStaffBoard() {
		  PlayerScoreboard scoreboard = PlayerScoreboard.getScoreboard(p);
		  if(hCMode.isMod(p)) {
		  if(hCMode.vanish.isVanished(p)) {
			  if(scoreboard.getEntry("vanish") == null) {
			  new Entry("vanish", scoreboard).setCountdown(false).setText(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("vanish") + ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("vanish-boolean-enabled")))).setTime(1).send();
			  }
		  }
		  else if(!hCMode.vanish.isVanished(p)) {
			  if(scoreboard.getEntry("vanish") != null) {
				  scoreboard.getEntry("vanish").setText(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("vanish") + ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("vanish-boolean-disabled"))));
			  }
		  }
		  }
	  }

}
