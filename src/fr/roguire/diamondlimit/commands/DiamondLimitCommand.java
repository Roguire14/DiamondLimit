package fr.roguire.diamondlimit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.roguire.diamondlimit.DiamondLimit;

public class DiamondLimitCommand implements CommandExecutor {

	private DiamondLimit plugin;
	
	public DiamondLimitCommand(DiamondLimit plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		boolean success = false;
		
		boolean status = plugin.getStatus();
			
		if(!sender.hasPermission("dl-change")) {
			
			sender.sendMessage(plugin.getStringFromConfig("no-permission"));
			return true;
			
		}
		
		if(status) {
			
			plugin.setStatus(!status);
			sender.sendMessage(plugin.getStringFromConfig("desactivation"));
			
		}
		
		else {
			
			plugin.setStatus(!status);
			sender.sendMessage(plugin.getStringFromConfig("activation"));
			
		}
		
		success = true;
		
		return success;
		
		
	}

}
