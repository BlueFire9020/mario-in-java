package zamoss.mario;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MarioGame
{
	//remind me why this is static again? maybe consider changing it to be an instance var
	//for example, we could easily just swap out the static calls for some sort
	//of reference method that allows us to simply call the instance variable from the gui class
	//through like getGame() or some similar method that returns what we're looking for. 
	public static World world;
	public Mario mario;
	
	public MarioGame()
	{
		try
		{
			world = WorldLoader.loadWorld("src/worlds/world_1.wrld");
			System.out.println(world);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		System.out.println("The init begins...");
		mario = new Mario(5, new Point(0, 24));
	}
	
	//is this a bad way of doing things? I think it might be
	public void update()
	{
		for (AbstractSprite sprite : world.getCollidableSprites())
		{
			sprite.update();
		}
	}
	
}
