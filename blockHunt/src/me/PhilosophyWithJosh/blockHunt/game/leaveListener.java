package me.PhilosophyWithJosh.blockHunt.game;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.PhilosophyWithJosh.blockHunt.Main;
import me.PhilosophyWithJosh.blockHunt.blockHunters.blockHunters;
import me.PhilosophyWithJosh.blockHunt.utils.utils;

public class leaveListener implements Listener
{
private Main plugin;
	
	public leaveListener(Main plugin)
	{
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void playerLeave(PlayerQuitEvent e)
	{	
		if(blockHunters.isBlockHunter(e.getPlayer()))
		{
			Bukkit.broadcastMessage(utils.chat("&c" + e.getPlayer().getName() + "&7 is no longer playing blockhunt"));
			blockHunters.removePlayer(e.getPlayer());
			if(blockHunters.blockHunterList().isEmpty())
			{
				Bukkit.broadcastMessage(utils.chat("&cThe game has ended as there are no more players"));
				
			}
		}
	}
}
