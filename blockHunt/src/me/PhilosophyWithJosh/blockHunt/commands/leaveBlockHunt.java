package me.PhilosophyWithJosh.blockHunt.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.PhilosophyWithJosh.blockHunt.Main;
import me.PhilosophyWithJosh.blockHunt.blockHunters.blockHunters;
import me.PhilosophyWithJosh.blockHunt.utils.utils;

public class leaveBlockHunt implements CommandExecutor 
{
	@SuppressWarnings("unused")
	private Main plugin;
	public leaveBlockHunt(Main plugin)
	{
		this.plugin = plugin;
		plugin.getCommand("leaveblockhunt").setExecutor(this);
	}
	public boolean onCommand(CommandSender sender, Command beblockhunter, String label, String[] args)
	{
		if( sender instanceof Player)
		{
			Player p = (Player)sender;
			if (blockHunters.isBlockHunter((Player)p))
			{
				blockHunters.removePlayer((Player)p);
				p.sendMessage(utils.chat("&bYou are no longer a blockhunter"));
				if(blockHunters.blockHunterList().isEmpty())
				{
					startBlockHunt.minigame.cancel();
					startBlockHunt.setGameRunning(false);
					Bukkit.broadcastMessage(utils.chat("&cThe game has ended as there are no more players"));			
				}
				return true;
			}
			else if(!blockHunters.isBlockHunter(p))
			{
				p.sendMessage(utils.chat("&7You have to be a blockhunter to leave blockhunt"));
				return true;
			}
		}
		return false;
	}
}
