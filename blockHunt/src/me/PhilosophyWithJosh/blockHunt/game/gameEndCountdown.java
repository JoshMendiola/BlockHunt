package me.PhilosophyWithJosh.blockHunt.game;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import me.PhilosophyWithJosh.blockHunt.Main;
import me.PhilosophyWithJosh.blockHunt.commands.startBlockHunt;
import me.PhilosophyWithJosh.blockHunt.utils.utils;

public class gameEndCountdown extends BukkitRunnable
{
	private static int seconds;
	@SuppressWarnings("unused")
	private Main plugin;
	
	public gameEndCountdown(Main plugin)
	{
		this.plugin = plugin;
	}
	
	public void run()
	{
		 if (seconds == 0) 
		 {
             cancel();
         }
		 else if(seconds <= 10) 
         {
             Bukkit.broadcastMessage(utils.chat("&e" + seconds + " second/s left"));
             seconds--;
         }
		 else
		 {
			 seconds--;
		 }
	}
	
	public static void setSeconds(int seconds)
	{
		gameEndCountdown.seconds = seconds;
	}
}
