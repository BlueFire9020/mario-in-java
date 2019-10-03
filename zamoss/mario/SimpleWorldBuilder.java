package zamoss.mario;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import zamoss.mario.blocks.*;

public class SimpleWorldBuilder 
{
	public String[] blockTypes = {"Normal", "Exclamation", "Mystery"};

	public static void main(String[] args)
	{
		System.out.println("Welcome to simple world builder");

		Scanner input = new Scanner(System.in);
		String response = input.nextLine();
		
		ArrayList<AbstractSprite> worldSprites = new ArrayList<AbstractSprite>();
		
		while (!response.equals("exit"))
		{
			System.out.println("Type exit to exit. \n Type add X Y BLOCKTYPE to add a block");
			String[] split = response.toLowerCase().split(" ");
			if (split[0].equals("add"))
			{
				System.out.println("Noted!");
				AbstractSprite block;
				System.out.println(split[3]);
				if (split[3].equals("exclamation"))
				{
					block = new ExclamationBlock();
				}
				else if (split[3].equals("mystery"))
				{
					block = new MysteryBlock();
				}
				else 
				{
					block = new RotatingBlock();
				}
				block.position = new Point(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
				
				worldSprites.add(block);
			}
			response = input.nextLine();
		}
		
		World world = new World(new Dimension(50, 10), worldSprites, 5, 50, 100, "World 1");
		try {
			WorldLoader.saveWorld("src/worlds", world);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
