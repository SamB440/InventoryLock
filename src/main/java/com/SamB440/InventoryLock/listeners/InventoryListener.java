package com.SamB440.InventoryLock.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.SamB440.InventoryLock.InventoryLock;

public class InventoryListener implements Listener {
	
	InventoryLock plugin;
	
	public InventoryListener(InventoryLock plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent ice)
	{
		if(ice.getClickedInventory() != null && ice.getCurrentItem() != null)
		{
			if(ice.getWhoClicked() instanceof Player)
			{
				ItemStack item = ice.getCurrentItem();
				if(item.equals(plugin.getLockedItem()))
				{
					ice.setCancelled(true);
				}
			}
		}
	}
}
