package events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import com.alexandeh.glaedr.scoreboards.Entry;
import com.alexandeh.glaedr.scoreboards.PlayerScoreboard;

import me.banterlol.bantercore.Main;
import net.md_5.bungee.api.ChatColor;
import utils.ArmorClassUtils;

public class ArmorClassEvent implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		PlayerScoreboard scoreboard = PlayerScoreboard.getScoreboard(p);
		if(p.getInventory().getBoots().equals(Material.GOLD_BOOTS) | p.getInventory().getLeggings().equals(Material.GOLD_LEGGINGS) | p.getInventory().getChestplate().equals(Material.GOLD_CHESTPLATE) | p.getInventory().getHelmet().equals(Material.GOLD_HELMET)) {
			if(scoreboard.getEntry("bard") == null) {
				new Entry("bard", scoreboard).setCountdown(false).setText(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("class") + " " + ArmorClassUtils.getInstance().getActiveKit(p))).setTime(1).send();
			}
		}
	}

}
