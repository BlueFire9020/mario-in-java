package zamoss.mario;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

public class World
{
	protected Dimension worldSize;	
	protected ArrayList<AbstractSprite> sprites;
	protected int gravity;
	protected int gravityFrame;
	protected int timeSpan;
	protected String worldName;
	protected AbstractSprite[][] worldMap;
	
	public World(Dimension worldSize, ArrayList<AbstractSprite> sprites, int gravity, int gravityFrame, int timeSpan, String worldName)
	{
		this.worldSize = worldSize;
		this.sprites = sprites;
		this.gravity = gravity;
		this.gravityFrame = gravityFrame;
		this.timeSpan = timeSpan;
		this.worldName = worldName;
		//initialize world map, maybe update this? IDK this is all weird
		//this should work in 16x16 since currently tiles are all 16x16 I think
		worldMap = new AbstractSprite[worldSize.width][worldSize.height];
		for (AbstractSprite s : sprites) 
		{
			worldMap[s.getPosition().x / 16][s.getPosition().y / 16] = s;
		}
	}
	
	public ArrayList<AbstractSprite> getWorldSprites()
	{
		return sprites;
	}
	
	public ArrayList<AbstractSprite> getCollidableSprites()
	{
		ArrayList<AbstractSprite> collidableSprites = new ArrayList<AbstractSprite>();
		
		for (AbstractSprite sprite : sprites)
		{
			if (sprite.hasCollision())
			{
				collidableSprites.add(sprite);
			}
		}
		
		return collidableSprites;
	}
	
	public AbstractSprite getCollidableSpriteAtPosition(Point position)
	{
		for (AbstractSprite sprite : getCollidableSprites())
		{
			//System.out.println(sprite.getBounds().getMaxY());
			//System.out.println("Checking bounds... " + sprite.getBounds() + " vs " + position);
			if (sprite.getBounds().contains(position))
			{
				return sprite;
			}
		}
		
		return null;
	}
	
	public ArrayList<AbstractSprite> getSpritesInBounds(Rectangle bounds)
	{
		ArrayList<AbstractSprite> boundedSprites = new ArrayList<AbstractSprite>();
		
		for (AbstractSprite sprite : sprites)
		{
			try
			{
				if (bounds.contains(sprite.position) || bounds.intersects(sprite.getBounds()))
				{
					boundedSprites.add(sprite);
				}	
			}
			catch (Exception e)
			{
				System.out.println("first, the exception: " + e);
				System.out.println("the offending item: " + sprite.getName() + " and bounds " + bounds + " vs " + sprite.getBounds());
			}
		}
		
		return boundedSprites;
	}
	
	public String toString()
	{
		String str = "World: " + worldName + " Gravity: " + gravity + " Time: " + timeSpan + " Sprites ";
		for (AbstractSprite spr : sprites)
		{
			str += "\n " + spr.getClass().getName() + " at " + spr.position.toString() + " ";
		}
		return str;
	}
	
}
