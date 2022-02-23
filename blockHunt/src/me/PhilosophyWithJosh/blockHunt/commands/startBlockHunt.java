package me.PhilosophyWithJosh.blockHunt.commands;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import me.PhilosophyWithJosh.blockHunt.Main;
import me.PhilosophyWithJosh.blockHunt.blockHunters.blockHunters;
import me.PhilosophyWithJosh.blockHunt.game.gameStartCountdown;
import me.PhilosophyWithJosh.blockHunt.game.gameRunner;
import me.PhilosophyWithJosh.blockHunt.utils.utils;
public class startBlockHunt implements CommandExecutor
{
	private Main plugin;
	private static boolean gamerunning = false;
	private static ArrayList<Material> blocks = new ArrayList<Material>();
	public static int id = 0;
	public static BukkitTask minigame;
	
	public startBlockHunt(Main plugin)
	{
		this.plugin = plugin;
		plugin.getCommand("startblockhunt").setExecutor(this);
		for (Material block : Material.values()) 
		{
			if (block.isBlock()) 
			{
				blocks.add(block);
			}
		}
	}
	
	public boolean onCommand(CommandSender sender, Command startblockhunt, String label, String[] args)
	{
		if(gameRunning())
		{
			sender.sendMessage(utils.chat("&cERROR: &7Can not start game, as game is already running"));
			return false;
		}
		else if (blockHunters.blockHunterList().isEmpty())
		{
			sender.sendMessage(utils.chat("&cERROR: &7Can not start game, as there are no blockhunters"));
			return false;
		}
		gameStartCountdown.setSeconds(10);
		BukkitTask countdown = new gameStartCountdown(this.plugin).runTaskTimer(plugin, 0L, 20L);
		return true;
	}
	public static void setUp()
	{
		blockHunters.clearBlocks();
		blockHunters.clearSuccess();
		for(int x = 0; x < blockHunters.blockHunterList().size();x++)
		{
			Material randomBlock = blocks.get(new Random().nextInt(blocks.size()));
			blockHunters.addBlock(randomBlock);
			Player bh = blockHunters.blockHunterList(x);
			blockHunters.addSuccess(false);
			bh.sendMessage(utils.chat("&eYour assigned block is " + blockHunters.ranBlockList(x)));
		}
	}
	
	public static void setGameRunning(boolean state)
	{
		gamerunning = state;
	}
	public static boolean gameRunning()
	{
		return gamerunning;
	}
	public static void setMinigame(BukkitTask mini)
	{
		minigame = mini;
	}
}
