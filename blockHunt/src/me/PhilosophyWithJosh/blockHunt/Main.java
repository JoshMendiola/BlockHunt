package me.PhilosophyWithJosh.blockHunt;


import org.bukkit.plugin.java.JavaPlugin;

import me.PhilosophyWithJosh.blockHunt.blockHunters.blockHunters;
import me.PhilosophyWithJosh.blockHunt.commands.beBlockHunter;
import me.PhilosophyWithJosh.blockHunt.commands.leaveBlockHunt;
import me.PhilosophyWithJosh.blockHunt.commands.startBlockHunt;
import me.PhilosophyWithJosh.blockHunt.game.gameRunner;
import me.PhilosophyWithJosh.blockHunt.game.leaveListener;
import me.PhilosophyWithJosh.blockHunt.game.successChecker;


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
