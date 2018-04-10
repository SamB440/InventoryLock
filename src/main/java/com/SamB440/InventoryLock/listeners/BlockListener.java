package com.SamB440.InventoryLock.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.SamB440.InventoryLock.InventoryLock;

public class BlockListener implements Listener {

	InventoryLock plugin;
	
	public BlockListener(InventoryLock plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent bpe)
	{
		ItemStack item = bpe.getItemInHand();
		Player p = bpe.getPlayer();
		if(item.equals(plugin.getLockedItem()))
		{
			if(!getConfig().getStringList("Server.Slots.Enabled_Locked").contains(String.valueOf(p.getInventory().getHeldItemSlot()))) p.getInventory().getItemInMainHand().setType(Material.AIR);
			else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Server.Messages.Unlock")));
				bpe.setCancelled(true);
			}
		}
	}
	
	private FileConfiguration getConfig()
	{
		return plugin.getConfig();
	}
}
