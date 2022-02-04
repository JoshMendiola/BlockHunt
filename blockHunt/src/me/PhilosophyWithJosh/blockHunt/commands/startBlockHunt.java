package me.PhilosophyWithJosh.blockHunt.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.PhilosophyWithJosh.blockHunt.*;
import me.PhilosophyWithJosh.blockHunt.game.game;
import me.PhilosophyWithJosh.blockHunt.utils.utils;

import java.util.ArrayList;
import java.util.Random;
public class startBlockHunt implements CommandExecutor
{
	private Main plugin;
	static boolean gamerunning = false;
	public startBlockHunt()
	{
		this.plugin = plugin;
		plugin.getCommand("startblockhunt").setExecutor(this);
	}
	public boolean onCommand(CommandSender sender, Command startblockhunt, String label, String[] args)
	{
		if(gamerunning == true)
		{
			sender.sendMessage(utils.chat("&cERROR: &7Can not start game, as game is already running"));
			return false;
		}
		else
		{
			gamerunning = true;
			game.runGame();
			return true;
		}
	}
	public static boolean gameRunning()
	{
		return gamerunning;
	}
}
