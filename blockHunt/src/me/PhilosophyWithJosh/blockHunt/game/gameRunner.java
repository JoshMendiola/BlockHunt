package me.PhilosophyWithJosh.blockHunt.game;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.PhilosophyWithJosh.blockHunt.Main;
import me.PhilosophyWithJosh.blockHunt.blockHunters.blockHunters;
import me.PhilosophyWithJosh.blockHunt.utils.utils;

public class gameRunner extends BukkitRunnable
{
	private static ArrayList<Material> blocks = new ArrayList<Material>();
	private Main plugin;
	
	public gameRunner(Main plugin)
	{
		this.plugin = plugin;
		for (Material block : Material.values()) 
		{
			if (block.isBlock()) 
			{
				blocks.add(block);
			}
		}
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
			if(blockHunters.blockHunterList().size() == 1)
			{
				Player winner =blockHunters.blockHunterList(0);
				Bukkit.broadcastMessage(utils.chat("&a" + winner.getName() + "&7 has won BlockHunt !"));
			}
			Bukkit.broadcastMessage(utils.chat("&eThe next round is now starting"));
			blockHunters.clearBlocks();
		}
		else
		{
			Bukkit.broadcastMessage(utils.chat("&cThe game cannot start/continue with no players"));
		}
	}
}
