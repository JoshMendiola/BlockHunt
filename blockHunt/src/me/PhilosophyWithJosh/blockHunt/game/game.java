package me.PhilosophyWithJosh.blockHunt.game;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import me.PhilosophyWithJosh.blockHunt.*;
import me.PhilosophyWithJosh.blockHunt.blockHunters.blockHunters;
import me.PhilosophyWithJosh.blockHunt.utils.utils;
public class game implements Listener
{
	private static Main plugin;
	static ArrayList<Material> blocks = new ArrayList<Material>();
	public game(Main plugin)
	{
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		for (Material block : Material.values()) 
		{
			if (block.isBlock()) 
			{
				blocks.add(block);
			}
		}
	}
	public static Plugin runGame()
	{
		//runs through a list of every block, randomizes the, and adds them to an arraylist
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
		BukkitScheduler timer = Bukkit.getServer().getScheduler();
		timer.scheduleSyncDelayedTask(plugin,new BukkitRunnable()
		{
			@Override
			public void run()
			{
				for(int x = 0; x < blockHunters.blockHunterList().size();x++)
				{
					Player bh = blockHunters.blockHunterList(x);
					if(bh.getLocation().clone().subtract(0,1,0).getBlock().getType() == blockHunters.ranBlockList(x))
					{
						
					}
				}
			}
		}, 6000);
	}
}

