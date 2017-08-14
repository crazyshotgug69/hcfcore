package utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.alexandeh.glaedr.scoreboards.Entry;
import com.alexandeh.glaedr.scoreboards.PlayerScoreboard;

import me.banterlol.bantercore.Main;
import net.libhalt.dev.plugin.armor.ArmorPlugin;
import net.libhalt.dev.plugin.armor.utils.Armor;

public class ArmorClassUtils implements Listener {
	
	private ArmorPlugin armorPlugin;
	private static ArmorClassUtils instance;
	  
	  public void init()
	  {
	    this.armorPlugin = ((ArmorPlugin)Main.getPlugin(ArmorPlugin.class));
	  }
	  
	  public String getActiveKit(Player paramPlayer)
	  {
	    Armor localArmor = this.armorPlugin.getActiveArmor(paramPlayer);
	    if (localArmor == null) {
	      return "None";
	    }
	    return localArmor.toKit();
	  }
	  
	  public boolean isBardActive(Player paramPlayer)
	  {
	    return this.armorPlugin.getActiveArmor(paramPlayer) == Armor.GOLD;
	  }
	  
	  public boolean isAnyActive(Player paramPlayer)
	  {
	    return this.armorPlugin.getActiveArmor(paramPlayer) != null;
	  }
	  
	  public double getBardPower(Player paramPlayer)
	  {
	    try
	    {
	      return Math.min(100.0D, this.armorPlugin.getBard().getPower(paramPlayer));
	    }
	    catch (NullPointerException nullPointerException) {}
	    return 0.0D;
	  }
	  
	  public static ArmorClassUtils getInstance() {
			return instance;
		}
}
