package com.SamB440.InventoryLock.listeners;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import com.SamB440.InventoryLock.InventoryLock;

public class JoinListener implements Listener {
	
	InventoryLock plugin;
	
	public JoinListener(InventoryLock plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent pje)
	{
		Player p = pje.getPlayer();
		List<String> slots = getConfig().getStringList("Server.Slots.Enabled_Locked");
		for(String s : slots)
		{
			if(!p.hasPermission("InventoryLock.slot." + s))
			{
				ItemStack item = p.getInventory().getItem(Integer.parseInt(s) - 1);
				if(item == null)
				{
					p.getInventory().setItem(Integer.parseInt(s), plugin.getLockedItem());
				} else {
					if(!item.equals(plugin.getLockedItem()))
					{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Server.Messages.Dropped_Item")));
						p.getWorld().dropItem(p.getLocation(), item);
						p.getInventory().setItem(Integer.parseInt(s) - 1, plugin.getLockedItem());
					}
				}
			}
		}
	}
	
	private FileConfiguration getConfig()
	{
		return plugin.getConfig();
	}
}
