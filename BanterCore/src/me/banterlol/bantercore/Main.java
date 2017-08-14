package me.banterlol.bantercore;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.alexandeh.glaedr.Glaedr;

import commands.HelpCommand;
import commands.SOTWCommand;
import events.ArmorClassEvent;
import events.CombatTagEvent;
import events.EnderpearlEvent;
import events.KitmapEvent;
import events.KothEvent;
import events.PvPTimer;
import events.WarOutbreakEvent;
import net.md_5.bungee.api.ChatColor;
import utils.EnchantLimiter;
import utils.StaffModeScoreboardUtil;

public class Main<HCFactionPlugin> extends JavaPlugin implements Listener {
	
	private static Main instance;
	public static FileConfiguration config;
	public static FileConfiguration reports;
	public static File rep;
	public static File conf;
	private StaffModeScoreboardUtil staffboard;
	
	private Glaedr glaedr;
	
	public void onEnable() {
		System.out.println("[EmpireHCF] Core Loaded");
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		instance = this;
		
		config = getConfig();
		config.options().copyDefaults(true);
		conf = new File(getDataFolder(), "config.yml");
		saveConfig();
		saveDefaultConfig();
		
		register();
		
		reports = getConfig();
		reports.options().copyDefaults(true);
		rep = new File(getDataFolder(), "reports.yml");
		saveConfig();
		
		register();
		
		getCommand("sotw").setExecutor(new SOTWCommand());
		getCommand("pvp").setExecutor(new PvPTimer());
		getCommand("help").setExecutor(new HelpCommand());
		getCommand("waroutbreak").setExecutor(new WarOutbreakEvent());
		
		glaedr = new Glaedr(this, ChatColor.translateAlternateColorCodes('&', config.getString("scoreboard-title")), true, false, false);
		  glaedr.getBottomWrappers().add(ChatColor.translateAlternateColorCodes('&', config.getString("scoreboard-lines")));
		  glaedr.getTopWrappers().add(ChatColor.translateAlternateColorCodes('&', config.getString("scoreboard-lines")));
		  glaedr.registerPlayers();
	}
	
	public void onDisable() {
		System.out.println("[EmpireHCF] Core Disabled");
	}
	
	public void register() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		
		pm.registerEvents(new EnderpearlEvent(), this);
		pm.registerEvents(new PvPTimer(), this);
		pm.registerEvents(new KitmapEvent(), this);
		pm.registerEvents(new CombatTagEvent(), this);
		pm.registerEvents(new KothEvent(), this);
		pm.registerEvents(new ArmorClassEvent(), this);
		pm.registerEvents(new EnchantLimiter(), this);
	}
	
	public static Main getInstance() {
		return instance;
	}

}
