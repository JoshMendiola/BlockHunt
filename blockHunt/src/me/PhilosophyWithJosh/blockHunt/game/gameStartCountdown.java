package me.PhilosophyWithJosh.blockHunt.game;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.PhilosophyWithJosh.blockHunt.Main;
import me.PhilosophyWithJosh.blockHunt.commands.startBlockHunt;
import me.PhilosophyWithJosh.blockHunt.utils.utils;

public class gameStartCountdown extends BukkitRunnable
{
	private static int seconds;
	@SuppressWarnings("unused")
	private Main plugin;
	public static BukkitTask endCountDown;
	
	public gameStartCountdown(Main plugin)
	{
		this.plugin = plugin;
	}
	
	public void run()
	{
		 if (seconds == 0) 
		 {
			 startBlockHunt.setUp();
			 gameEndCountdown.setSeconds(299);
			 endCountDown = new gameEndCountdown(this.plugin).runTaskTimer(plugin, 0L, 20L);
			 startBlockHunt.minigame = new gameRunner(this.plugin).runTaskTimer(plugin, 6000L, 6000L);
             cancel();
         }
		 else 
         {
			 startBlockHunt.setGameRunning(true);
             Bukkit.broadcastMessage(utils.chat("&e" + seconds + " second/s till the game starts"));
             seconds--;
         }
	}
	
	public static void setSeconds(int seconds)
	{
		gameStartCountdown.seconds = seconds;
	}
}
