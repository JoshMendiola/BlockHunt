package me.PhilosophyWithJosh.blockHunt.commands;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import me.PhilosophyWithJosh.blockHunt.Main;
import me.PhilosophyWithJosh.blockHunt.blockHunters.blockHunters;
import me.PhilosophyWithJosh.blockHunt.utils.utils;
public class startBlockHunt implements CommandExecutor
{
	private Main plugin;
	private static boolean gamerunning = false;
	private static ArrayList<Material> blocks = new ArrayList<Material>();
	public static int id = 0;
	
	public startBlockHunt(Main plugin)
	{
		this.plugin = plugin;
		for (Material block : Material.values()) 
		{
			if (block.isBlock()) 
			{
				blocks.add(block);
			}
		}
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
			setUp();
			Bukkit.broadcastMessage("The game is now starting");
			gamerunning = true;
	        new BukkitRunnable()
	        {
				@Override
	            public void run()
	            {
					if(blockHunters.blockHunterList().size() > 0)
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
							this.cancel();
						}
						Bukkit.broadcastMessage(utils.chat("&eThe next round is now starting"));
						blockHunters.clearBlocks();
						setUp();
					}
					else
					{
						Bukkit.broadcastMessage(utils.chat("&cThe game cannot start/continue with no players"));
					}
	            }
			}.runTaskTimer(plugin, 6000L, 6000L);
		}
		return true;
	}
	
	public void setUp()
	{
		for(int x = 0; x < blockHunters.blockHunterList().size();x++)
		{
			Material randomBlock = blocks.get(new Random().nextInt(blocks.size()));
			blockHunters.addBlock(randomBlock);
			Player bh = blockHunters.blockHunterList(x);
			blockHunters.addSuccess(false);
			bh.sendMessage(utils.chat("&eYour assigned block is " + blockHunters.ranBlockList(x)));
		}
	}
	
	public static boolean gameRunning()
	{
		return gamerunning;
	}
}
