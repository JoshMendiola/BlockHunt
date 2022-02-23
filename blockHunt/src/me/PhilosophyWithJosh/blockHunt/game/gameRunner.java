package me.PhilosophyWithJosh.blockHunt.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.PhilosophyWithJosh.blockHunt.Main;
import me.PhilosophyWithJosh.blockHunt.blockHunters.blockHunters;
import me.PhilosophyWithJosh.blockHunt.commands.startBlockHunt;
import me.PhilosophyWithJosh.blockHunt.utils.utils;

public class gameRunner extends BukkitRunnable
{
	@SuppressWarnings("unused")
	private Main plugin;
	
	public gameRunner(Main plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public void run() 
	{
		if(!blockHunters.blockHunterList().isEmpty())
		{
			for(int x = 0; x < blockHunters.blockHunterList().size();x++)
			 {
				Player bh = blockHunters.blockHunterList(x);
				if(!blockHunters.getSuccess(bh))
				{
					 bh.setHealth(0.0);
					 Bukkit.broadcastMessage(utils.chat("&c" + bh.getName() + " &7has failed and died"));
					 blockHunters.removePlayer(bh);
				}
				else
				{
					blockHunters.setSuccess(bh, false);
				}
			 }
			if(blockHunters.blockHunterList().size() == 0)
			{
				Bukkit.broadcastMessage(utils.chat("&eThe game has ended as everyone failed"));
				this.cancel();
				startBlockHunt.setGameRunning(false);
			}
			else if(blockHunters.blockHunterList().size() == 1)
			{
				Player winner =blockHunters.blockHunterList(0);
				Bukkit.broadcastMessage(utils.chat("&a" + winner.getName() + "&7 has won BlockHunt !"));
			}
			else if(blockHunters.blockHunterList().size() > 1)
			{
				Bukkit.broadcastMessage(utils.chat("&eThe next round is now starting"));
				blockHunters.clearBlocks();
				BukkitTask gameCountdown = new gameEndCountdown(this.plugin).runTaskTimer(this.plugin, 0L, 20L);
			}
		}
		else
		{
			Bukkit.broadcastMessage(utils.chat("&cThe game cannot start/continue with no players"));
		}
	}
}
