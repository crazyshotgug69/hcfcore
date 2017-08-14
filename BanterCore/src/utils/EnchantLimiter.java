package utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import me.banterlol.bantercore.Main;
import net.md_5.bungee.api.ChatColor;

public class EnchantLimiter implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getType() == InventoryType.ANVIL) {
            if(e.getSlotType() == InventoryType.SlotType.RESULT){
                ItemStack item = e.getCurrentItem();

                if(item.getType() == Material.ENCHANTED_BOOK){
                    for (String blockedEnchantments : Main.getInstance().config.getStringList("enchant-limiter")) {

                        String[] parse = blockedEnchantments.split(":");
                        Enchantment selectedEnchantment = Enchantment.getByName(parse[0]);
                        int level = Integer.parseInt(parse[1]);

                        EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta) item.getItemMeta();

                        if (bookMeta.getStoredEnchants().containsKey(selectedEnchantment)) {
                            if (bookMeta.getStoredEnchants().get(selectedEnchantment) > level) {
                                e.setCancelled(true);
                                p.closeInventory();
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("enchant-too-high")));
                            }
                        }
                    }
                }
            }
        }
		if (e.getInventory().getType() == InventoryType.ENCHANTING) {
            if(e.getSlotType() == InventoryType.SlotType.RESULT){
                ItemStack item = e.getCurrentItem();

                if(item.getType() == Material.ENCHANTED_BOOK){
                    for (String blockedEnchantments : Main.getInstance().config.getStringList("enchant-limiter")) {

                        String[] parse = blockedEnchantments.split(":");
                        Enchantment selectedEnchantment = Enchantment.getByName(parse[0]);
                        int level = Integer.parseInt(parse[1]);

                        EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta) item.getItemMeta();

                        if (bookMeta.getStoredEnchants().containsKey(selectedEnchantment)) {
                            if (bookMeta.getStoredEnchants().get(selectedEnchantment) > level) {
                                e.setCancelled(true);
                                p.closeInventory();
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().config.getString("enchant-too-high")));
                            }
                        }
                    }
                }
            }
        }
	}

}
