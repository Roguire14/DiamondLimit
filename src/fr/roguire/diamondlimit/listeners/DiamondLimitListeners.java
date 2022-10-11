package fr.roguire.diamondlimit.listeners;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import fr.roguire.diamondlimit.DiamondLimit;

public class DiamondLimitListeners implements Listener {
	
	private DiamondLimit plugin;
	
	public DiamondLimitListeners(DiamondLimit plugin) {
		
		this.plugin = plugin;
		
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		
		if(!plugin.getStatus()) return;
		
		if(e.getBlock().getType() != Material.DIAMOND_ORE) return;
		
		HashMap<Player, Integer> map = plugin.getCompteur();
		Player p = e.getPlayer();
		int minerai_mine;
		int level_drops = e.getExpToDrop();
		
		if(map.get(p) == null) minerai_mine = 0;
		else minerai_mine = map.get(p)+1;
		
		int limit = plugin.getLimit();
		
		map.put(p, minerai_mine);
		
		World world = e.getBlock().getWorld();
		
		if(minerai_mine>=limit) {
			
			ItemStack is = new ItemStack(Material.GOLD_INGOT);
			world.dropItemNaturally(e.getBlock().getLocation(), is);
			e.getBlock().setType(Material.AIR);
			p.giveExp(level_drops);
			
		}else {
			
			plugin.setCompteur(map);
			
		}
		
	}

}
