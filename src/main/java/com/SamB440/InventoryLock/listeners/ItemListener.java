package com.SamB440.InventoryLock.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import com.SamB440.InventoryLock.InventoryLock;

public class ItemListener implements Listener {
	
	InventoryLock plugin;
	
	public ItemListener(InventoryLock plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent pdie)
	{
		Player p = pdie.getPlayer();
		ItemStack item = pdie.getItemDrop().getItemStack();
		if(item.equals(plugin.getLockedItem()))
		{
			if(!getConfig().getStringList("Server.Slots.Enabled_Locked").contains(String.valueOf(p.getInventory().getHeldItemSlot() + 1))) p.getInventory().getItemInMainHand().setType(Material.AIR);
			else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Server.Messages.Unlock")));
				pdie.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onMove(InventoryMoveItemEvent pime)
	{
		ItemStack item = pime.getItem();
		if(pime.getSource().getHolder() instanceof Player)
		{
			Player p = (Player) pime.getSource().getHolder();
			if(item.equals(plugin.getLockedItem()))
			{
				if(!getConfig().getStringList("Server.Slots.Enabled_Locked").contains(String.valueOf(p.getInventory().getHeldItemSlot() + 1))) p.getInventory().getItemInMainHand().setType(Material.AIR);
				else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Server.Messages.Unlock")));
					pime.setCancelled(true);
				}
			}
		}
	}
	private FileConfiguration getConfig()
	{
		return plugin.getConfig();
	}
}
