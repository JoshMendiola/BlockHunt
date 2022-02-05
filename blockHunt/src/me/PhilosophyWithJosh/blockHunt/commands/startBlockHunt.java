package me.PhilosophyWithJosh.blockHunt.commands;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.PhilosophyWithJosh.blockHunt.Main;
import me.PhilosophyWithJosh.blockHunt.blockHunters.blockHunters;
import me.PhilosophyWithJosh.blockHunt.utils.utils;
public class startBlockHunt implements CommandExecutor
{
	private Main plugin;
	private static boolean gamerunning = false;
	private static ArrayList<Material> blocks = new ArrayList<Material>();
	
	public startBlockHunt()
	{
		this.plugin = plugin;
		plugin.getCommand("startblockhunt").setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command startblockhunt, String label, String[] args)
	{
		if(gameRunning())
		{
			sender.sendMessage(utils.chat("&cERROR: &7Can not start game, as game is already running"));
			return false;
		}
		else
		{
			new BukkitRunnable()
			{
				@Override
	            public void run()
	            {
					for(int x = 0; x < blockHunters.blockHunterList().size();x++)
					 {
						Player bh = blockHunters.blockHunterList(x);
						blockHunters.clearBlocks();
						if(!blockHunters.getSuccess(bh))
						{
							 bh.setHealth(0.0);
							 Bukkit.broadcastMessage(utils.chat("&c" + bh.getName() + " has failed and died"));
							 blockHunters.removePlayer(bh);
						}
						else
						{
							blockHunters.setSuccess(bh, false);
						}
					 }
					setUp();
	            }
			}.runTaskLater(this.plugin, 6000);
		}
		return false;
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
	
	public static boolean gameRunning()
	{
		return gamerunning;
	}
}
