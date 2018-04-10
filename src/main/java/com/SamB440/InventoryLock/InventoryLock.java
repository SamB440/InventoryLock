package com.SamB440.InventoryLock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.SamB440.InventoryLock.commands.InventoryLockCommand;
import com.SamB440.InventoryLock.listeners.BlockListener;
import com.SamB440.InventoryLock.listeners.InventoryListener;
import com.SamB440.InventoryLock.listeners.ItemListener;
import com.SamB440.InventoryLock.listeners.JoinListener;
import com.SamB440.InventoryLock.listeners.RespawnListener;

public class InventoryLock extends JavaPlugin {
	
	Logger log = Bukkit.getLogger();
	
	@Override
	public void onEnable()
	{
		registerListeners();
		registerCommands();
		getConfig().options().copyDefaults(true);
		createConfig();
	}
	
	private void registerListeners()
	{
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new InventoryListener(this), this);
		pm.registerEvents(new BlockListener(this), this);
		pm.registerEvents(new ItemListener(this), this);
		pm.registerEvents(new JoinListener(this), this);
		pm.registerEvents(new RespawnListener(this), this);
	}
	private void createConfig()
	{
		String header;
		String eol = System.getProperty("line.separator");
		header = "InventoryLock Properties:" + eol;
		header += eol;
		header += "List of valid materials:" + eol;
		header += "  https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html" + eol;
		header += "A note on slots:" + eol;
		header += "  They are user friendly and thus you can just enter it as normal." + eol;
		header += "  Normally plugins have it like this: 0, 1, 2... etc." + eol;
		header += eol;
		getConfig().options().header(header);
		List<String> locked = Arrays.asList("1", "2", "3", "4");
		getConfig().addDefault("Server.Slots.Enabled_Locked", locked);
		getConfig().addDefault("Server.Slots.Item.Name", "&cLocked!");
		getConfig().addDefault("Server.Slots.Item.Lore", Arrays.asList("&fUnlock on our store!", "&awww.mystore.net"));
		getConfig().addDefault("Server.Slots.Item.Type", "STAINED_GLASS");
		getConfig().addDefault("Server.Slots.Item.Data", 14);
		getConfig().addDefault("Server.Messages.Unlock", "&cThis slot is locked. Unlock it by purchasing at &awww.mystore.net!");
		getConfig().addDefault("Server.Messages.Dropped_Item", "&cYour inventory had an item on a locked slot and was dropped on the ground.");
		saveConfig();
	}
	private void registerCommands()
	{
		getCommand("inventorylock").setExecutor(new InventoryLockCommand(this));
	}
	public ItemStack getLockedItem()
	{
		ItemStack item = new ItemStack(Material.valueOf(getConfig().getString("Server.Slots.Item.Type")), 1, (byte) getConfig().getInt("Server.Slots.Item.Data"));
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Server.Slots.Item.Name")));
		List<String> lore = new ArrayList<String>();
		for(String s : getConfig().getStringList("Server.Slots.Item.Lore"))
		{
			lore.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		im.setLore(lore);
		item.setItemMeta(im);
		return item;
	}
}
