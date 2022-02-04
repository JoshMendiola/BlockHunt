package me.PhilosophyWithJosh.blockHunt.blockHunters;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import me.PhilosophyWithJosh.blockHunt.*;
public class blockHunters implements Listener
{
	
	private Main plugin;
	//array of all current players of blockhunters
	private static ArrayList<Player> blockhunters = new ArrayList<Player>();
	//array of the assigned blocks for each player to hunt
	private static ArrayList<Material> ranBlocks = new ArrayList<Material>();
	
	public blockHunters(Main plugin)
	{
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this,plugin);
	}
	//adds a player to the list of players and sets their assigned block as "null"
	public static void addPlayer(Player p)
	{
		blockhunters.add(p);
		ranBlocks.add(null);
	}
	public static void removePlayer(Player p)
	{
		int index = blockhunters.indexOf(p);
		blockhunters.remove(index);
		ranBlocks.remove(index);
	}
	public static void addBlock(Material b)
	{
		ranBlocks.add(b);
	}
	public static void removeBlock(Material b)
	{
		ranBlocks.remove(b);
	}
	public static boolean isBlockHunter(Player p)
	{
		if(blockhunters.contains(p))
		{
			return true;
		}
		return false;
	}
	
	public static ArrayList<Player> blockHunterList()
	{
		return blockhunters;
	}
	public static Player blockHunterList(int x)
	{
		return blockhunters.get(x);
	}
	public static ArrayList<Material> ranBlockList()
	{
		return ranBlocks;
	}
	public static Material ranBlockList(int x)
	{
		return ranBlockList().get(x);
	}
}
