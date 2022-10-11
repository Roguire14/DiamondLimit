package fr.roguire.diamondlimit;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.roguire.diamondlimit.commands.DiamondLimitCommand;
import fr.roguire.diamondlimit.listeners.DiamondLimitListeners;

public class DiamondLimit extends JavaPlugin {
	
	private boolean status;
	private HashMap<Player, Integer> compteur;
	private int limit;
	
	public DiamondLimit() {
		
		setStatus(false);
		setCompteur(new HashMap<>());
		limit = getConfig().getInt("diamond-limit");
		
	}
	
	@Override
	public void onEnable() {
		
		getConfig().options().copyDefaults();
		saveDefaultConfig();
		
		getCommand("diamondlimit").setExecutor(new DiamondLimitCommand(this));
		getServer().getPluginManager().registerEvents(new DiamondLimitListeners(this), this);
		
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getStringFromConfig(String arg) {
		
		return getConfig().getString(arg).replace('&', '§');
		
	}

	public HashMap<Player, Integer> getCompteur() {
		return compteur;
	}

	public void setCompteur(HashMap<Player, Integer> compteur) {
		this.compteur = compteur;
	}

	public int getLimit() {
		return limit;
	}

}
