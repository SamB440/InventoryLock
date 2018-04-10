package com.SamB440.InventoryLock.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.SamB440.InventoryLock.InventoryLock;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class InventoryLockCommand implements CommandExecutor {
	
	InventoryLock plugin;
	
	public InventoryLockCommand(InventoryLock plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender p, Command cmd, String commandLabel, String[] args)
	{
		if(args.length == 0)
		{
			TextComponent help = new TextComponent(ChatColor.YELLOW + "Showing help for InventoryLock " + ChatColor.WHITE + "1/1");
			help.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.WHITE + "Showing page 1/1 click to go to the next page or use /rr 2.").create()));
			//help.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rr 2"));
			p.spigot().sendMessage(help);
			TextComponent c1 = new TextComponent(ChatColor.GREEN + "/InventoryLock");
			c1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.WHITE + "Click to paste command.").create()));
			c1.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/InventoryLock"));
			p.spigot().sendMessage(c1);
			p.sendMessage(ChatColor.WHITE + "   Aliases: /il, invl.");
			p.sendMessage(ChatColor.WHITE + "   Description: Displays help page.");
			TextComponent c2 = new TextComponent(ChatColor.GREEN + "/InventoryLock reload");
			c2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.WHITE + "Click to paste command.").create()));
			c2.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/InventoryLock reload"));
			p.spigot().sendMessage(c2);
			p.sendMessage(ChatColor.WHITE + "   Aliases: None.");
			p.sendMessage(ChatColor.WHITE + "   Description: Reload the plugin.");
			p.sendMessage(ChatColor.WHITE + "   Permission(s): OP");
			TextComponent github = new TextComponent(ChatColor.YELLOW + "© 2018 InventoryLock. Made with" + " ❤ " + "by SamB440.");
			github.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.WHITE + "Click to visit the Github!").create()));
			github.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://www.github.com/SamB440/InventoryLock"));
			p.spigot().sendMessage(github);
		} else if(args.length == 1) {
			
			if(args[0].equalsIgnoreCase("reload") && p.isOp() || p.hasPermission("inventorylock.reload"))
			{
				p.sendMessage(ChatColor.GREEN + "Reloading...");
				plugin.reloadConfig();
				plugin.saveConfig();
				p.sendMessage(ChatColor.GREEN + "Done!");
			}
		} return true;
	}
}
