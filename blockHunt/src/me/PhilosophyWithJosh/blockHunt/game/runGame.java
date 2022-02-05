package me.PhilosophyWithJosh.blockHunt.game;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import me.PhilosophyWithJosh.blockHunt.*;
import me.PhilosophyWithJosh.blockHunt.blockHunters.blockHunters;
import me.PhilosophyWithJosh.blockHunt.utils.utils;

public class runGame extends JavaPlugin
{
	private static ArrayList<Material> blocks = new ArrayList<Material>();
	
	public void onEnable() 
	{
		 BukkitScheduler scheduler = getServer().getScheduler();
		 scheduler.scheduleSyncRepeatingTask((Plugin) this, new Runnable() 
		 {
			 @Override
			 public void run() 
			 {
				 for(int x = 0; x < blockHunters.blockHunterList().size();x++)
				{
					Player bh = blockHunters.blockHunterList(x);
					if(!blockHunters.getSuccess(bh))
					{
						 bh.setHealth(0.0);
						 Bukkit.broadcastMessage(utils.chat("&c" + bh.getName() + " has failed and died"));
						 blockHunters.removePlayer(bh);
						 blockHunters.clearBlocks();
						 
					}
				}
				setUp();
			 }
		 }, 0L, 6000L);
		 
	 }
 public void setUp()
	{
	 	for (Material block : Material.values()) 
		{
			if (block.isBlock()) 
			{
				blocks.add(block);
			}
		}
		for(int x = 0; x < blockHunters.blockHunterList().size();x++)
		{
			Material randomBlock = blocks.get(new Random().nextInt(blocks.size()));
			blockHunters.addBlock(randomBlock);
		}
			
		//assigns every player a block
		for(int x = 0; x < blockHunters.blockHunterList().size();x++)
		{
			Player bh = blockHunters.blockHunterList(x);
			bh.sendMessage(utils.chat("&eYour assigned block is " + blockHunters.ranBlockList(x)));
		}
	}
}
