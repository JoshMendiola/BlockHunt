package me.PhilosophyWithJ.blockHunt.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;

import me.PhilosophyWithJosh.blockHunt.Main;
import me.PhilosophyWithJosh.blockHunt.blockHunters.blockHunters;
import me.PhilosophyWithJosh.blockHunt.commands.startBlockHunt;
import me.PhilosophyWithJosh.blockHunt.game.gameRunner;
import me.PhilosophyWithJosh.blockHunt.utils.utils;


public class successChecker implements Listener
{
private Main plugin;
	
	public successChecker(Main plugin)
	{
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void check(PlayerMoveEvent e)
	{	
		if(!blockHunters.blockHunterList().isEmpty())
		{
			if(startBlockHunt.gameRunning() && blockHunters.isBlockHunter(e.getPlayer()) && blockHunters.getSuccess(e.getPlayer()) == false)
			{
				for(int x = 0; x < blockHunters.blockHunterList().size();x++)
				{
					Player bh = blockHunters.blockHunterList(x);
					Location loc = bh.getPlayer().getLocation().clone().subtract(0, 1, 0);
					Block b = loc.getBlock();
	
					if(b.getType() == blockHunters.ranBlockList(blockHunters.getIndex(bh)))
					{
						blockHunters.setSuccess(bh, true);
						Bukkit.broadcastMessage(utils.chat("&a" + bh.getName() + "&7 has found their block in time"));
						if(blockHunters.allPlayersSucceed())
						{
							Bukkit.broadcastMessage(utils.chat("&7All blockhunters have found their block"));
							startBlockHunt.minigame.cancel();
							startBlockHunt.setUp();
							BukkitTask minigame = new gameRunner(this.plugin).runTaskTimer(plugin, 6000L, 6000L);
							startBlockHunt.setMinigame(minigame);
						}
					}
				}
			}
		}
	}
}
