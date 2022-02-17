package me.PhilosophyWithJosh.blockHunt;


import org.bukkit.plugin.java.JavaPlugin;

import me.PhilosophyWithJ.blockHunt.listeners.leaveListener;
import me.PhilosophyWithJ.blockHunt.listeners.successChecker;
import me.PhilosophyWithJosh.blockHunt.blockHunters.blockHunters;
import me.PhilosophyWithJosh.blockHunt.commands.beBlockHunter;
import me.PhilosophyWithJosh.blockHunt.commands.leaveBlockHunt;
import me.PhilosophyWithJosh.blockHunt.commands.startBlockHunt;
import me.PhilosophyWithJosh.blockHunt.game.gameRunner;


public class Main extends JavaPlugin
{
	public void onEnable()
	{
		new blockHunters(this);
		new beBlockHunter(this);
		new leaveBlockHunt(this);
		new startBlockHunt(this);
		new successChecker(this);
		new leaveListener(this);
		new gameRunner(this);
	}
}
