package zamoss.mario;

import java.awt.Dimension;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import zamoss.mario.blocks.*;

public class WorldLoader 
{	
	public static World loadWorld(String path) throws IOException, ClassNotFoundException
	{
		//load the world here
		World w;
		Scanner input = new Scanner(new File(path));
		String worldName = input.nextLine().split(":")[1];
		String line = input.nextLine();
		int gravity = Integer.parseInt(line.split(":")[1]);
		int gravityFrame = Integer.parseInt(line.split(":")[2]);
		int time = Integer.parseInt(input.nextLine().split(":")[1]);
		String split[] = input.nextLine().split(":");
		Dimension size = new Dimension(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
		ArrayList<AbstractSprite> sprites = new ArrayList<AbstractSprite>();
		while (input.hasNextLine())
		{
			//THIS LITERALLY ONLY WORKS FOR BLOCKS THIS NEEDS TO BE FIXED ASAP ONCE BLOCKS ARE WORKING
			split = input.nextLine().split(":");
			//make this better later
			AbstractSprite block = null; 
			if (split[1].equals("Mystery Block"))
			{
				block = new MysteryBlock();
			}
			else if (split[1].equals("Exclamation Block"))
			{
				block = new ExclamationBlock();
			}
			else
			{
				block = new RotatingBlock();
			}
			block.position = new Point(Integer.parseInt(split[2]), Integer.parseInt(split[3]));
			sprites.add(block);
		}
		
		return new World(size, sprites, gravity, gravityFrame, time, worldName);
	}

	public static void saveWorld(String path, World w) throws IOException
	{
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(path + "/" + w.worldName.replace(' ', '_') + ".wrld"), "utf-8"))) 
		{
			writer.write("name:" + w.worldName);
			writer.write("\ngravity:" + w.gravity + ":" + w.gravityFrame);
			writer.write("\ntime:" + w.timeSpan);
			writer.write("\nsize:" + w.worldSize.width + ":" + w.worldSize.height);
			for (AbstractSprite sprite : w.getWorldSprites())
			{
				writer.write("\nsprite:" + sprite.getName() + ":" + sprite.position.x + ":" + sprite.position.y);
			}
		}
	}
}
