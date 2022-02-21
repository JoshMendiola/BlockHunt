package me.PhilosophyWithJosh.blockHunt.game;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import me.PhilosophyWithJosh.blockHunt.Main;
import me.PhilosophyWithJosh.blockHunt.commands.startBlockHunt;
import me.PhilosophyWithJosh.blockHunt.utils.utils;

public class countdown extends BukkitRunnable
{
	private static int seconds;
	@SuppressWarnings("unused")
	private Main plugin;
	
	public countdown(Main plugin)
	{
		this.plugin = plugin;
	}
	
	public void run()
	{
		 if (seconds == 0) 
		 {
			 startBlockHunt.setUp();
			 startBlockHunt.minigame = new gameRunner(this.plugin).runTaskTimer(plugin, 6000L, 6000L);
             cancel(); // Cancels the timer
         }
		 else 
         {
             Bukkit.broadcastMessage(utils.chat("&e" + seconds + " second/s till the game starts"));
             seconds--;
         }
	}
	
	public static void setSeconds(int seconds)
	{
		countdown.seconds = seconds;
	}
}
