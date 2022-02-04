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
	private Main plugin;
	public beBlockHunter()
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
				blockHunters.removePlayer(p);
				p.sendMessage(utils.chat("&7You are no longer a blockhunter"));
				return true;
			}
		}
		return false;
	}
}
