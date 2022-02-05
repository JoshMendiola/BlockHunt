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
	//return success for each block player
	private static ArrayList<Boolean> success = new ArrayList<Boolean>();
	private static boolean isSafe = true;
	
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
		success.add(false);
	}
	
	public static void removePlayer(Player p)
	{
		int index = blockhunters.indexOf(p);
		blockhunters.remove(index);
		ranBlocks.remove(index);
		success.remove(index);
	}
	
	public static void addBlock(Material b)
	{
		ranBlocks.add(b);
	}
	public static void clearBlocks()
	{
		ranBlocks.clear();
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
	
	public static void setSuccess(Player p, Boolean b)
	{
		int index = blockhunters.indexOf(p);
		success.set(index, b);
	}
	
	public static boolean getSuccess(Player p)
	{
		int index = blockhunters.indexOf(p);
		return success.get(index);
	}
	
	public static int getIndex(Player p)
	{
		return blockhunters.indexOf(p);
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
