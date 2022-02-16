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
			
		}
		return true;
}
	public static boolean gameRunning()
	{
		return gamerunning;
	}
}
