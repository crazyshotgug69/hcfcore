package events;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.alexandeh.glaedr.scoreboards.Entry;
import com.alexandeh.glaedr.scoreboards.PlayerScoreboard;

import me.banterlol.bantercore.Main;
import net.md_5.bungee.api.ChatColor;

public class EnderpearlEvent implements Listener {
	
ArrayList<Player> enderpearl = new ArrayList<Player>();

int time = Main.getInstance().config.getInt("enderpearl-time");
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		PlayerScoreboard scoreboard = PlayerScoreboard.getScoreboard(p);
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(p.getItemInHand().getType() == Material.ENDER_PEARL) {
				if(!enderpearl.contains(p)) {
					if(scoreboard.getEntry("enderpearl") == null) {
						enderpearl.add(p);
						new Entry("enderpearl", scoreboard)
						.setCountdown(true)
						.setText(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("enderpearl")))
						.setTime(time)
						.send();
						p.sendMessage(ChatColor.GREEN + "You now have an enderpearl cooldown!");
						return;
					}
				}
				if(enderpearl.contains(p)) {
					if(scoreboard.getEntry("enderpearl") != null) {
						e.setCancelled(true);
						p.updateInventory();
						p.sendMessage(ChatColor.RED + "You currently have an enderpearl cooldown!");
						return;
					}
				}
	}
}
}

}
