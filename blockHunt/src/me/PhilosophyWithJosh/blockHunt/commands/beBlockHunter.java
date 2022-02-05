package me.PhilosophyWithJosh.blockHunt.commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.PhilosophyWithJosh.blockHunt.*;
import me.PhilosophyWithJosh.blockHunt.blockHunters.blockHunters;
import me.PhilosophyWithJosh.blockHunt.utils.utils;
public class beBlockHunter implements CommandExecutor
{
	@SuppressWarnings("unused")
	private Main plugin;
	public beBlockHunter(Main plugin)
	{
		this.plugin = plugin;
		plugin.getCommand("beblockhunter").setExecutor(this);
	}
	public boolean onCommand(CommandSender sender, Command beblockhunter, String label, String[] args)
	{
		if( sender instanceof Player)
		{
			Player p = (Player)sender;
			if (!blockHunters.isBlockHunter((Player)p) && !startBlockHunt.gameRunning())
			{
				blockHunters.addPlayer((Player)p);
				p.sendMessage(utils.chat("&bYou are now a blockhunter :)"));
				return true;
			}
			else if(blockHunters.isBlockHunter(p))
			{
				p.sendMessage(utils.chat("&7You are already a block hunter"));
				return true;
			}
			else if(startBlockHunt.gameRunning())
			{
				p.sendMessage(utils.chat("&7You cannot join while a game is running"));
				return true;
			}
		}
		return false;
	}
}
