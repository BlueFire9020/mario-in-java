package zamoss.mario;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class AbstractSprite
{
	protected ArrayList<BufferedImage> sprites;
	protected int spriteIndex;
	protected int spriteID;
	protected int weight = 1;
	
	protected boolean collision;
	protected boolean followsGravity;
	protected Rectangle bounds;

	protected Point position;
	protected int zIndex = 0;
	
	protected int updateFrame = 1;

	public BufferedImage getSprite()
	{
		return sprites.get(spriteIndex);
	}

	public int getSpriteIndex()
	{
		return spriteIndex;
	}

	protected void setSpriteIndex(int newIndex)
	{
		spriteIndex = newIndex;
	}

	public int getSpriteID()
	{
		return spriteID;
	}
	
	public int getWeight()
	{
		return weight;
	}
	
	public void setWeight(int newWeight)
	{
		weight = newWeight;
	}
	
	public String getName()
	{
		return "Unnamed Sprite";
	}

	public boolean hasCollision()
	{
		return collision;
	}
	
	public boolean followsGravity()
	{
		return followsGravity;	
	}

	public Rectangle getBounds()
	{
		return bounds;
	}

	public void setCollision(boolean collision)
	{
		this.collision = collision;
	}

	public Point getPosition()
	{
		return position;
	}

	protected void setPosition(int newXPos, int newYPos)
	{
		position = new Point(newXPos, newYPos);
	}

	protected void setPosition(Point newPos)
	{
		position = newPos;
	}

	public int getZIndex()
	{
		return zIndex;
	}

	public void setZIndex(int newZ)
	{
		zIndex = newZ;
	}
	
	public int getUpdateFrame()
	{
		return updateFrame;
	}
	
	public void setUpdateFrame(int newUpdateFrame)
	{
		//obviously we can't have negative frames, so we need to abs it
		updateFrame = Math.max(1, newUpdateFrame);
	}

	public boolean grounded()
	{
		Point leftBoundingBottom = new Point(position.x, position.y - (getBounds().height / 2));
		Point rightBoundingBottom = new Point(position.x + getBounds().width, position.y - (getBounds().height / 2));
		AbstractSprite leftGroundObj = MarioGame.world.getCollidableSpriteAtPosition(leftBoundingBottom);
		AbstractSprite rightGroundObj = MarioGame.world.getCollidableSpriteAtPosition(rightBoundingBottom);
		if (leftGroundObj != null || rightGroundObj != null)
		{
			return true;
		}
		
		return false;
	}

	public String toString()
	{
		return "POS:" + position.x + " " + position.y + " ZINDEX:" + zIndex;
	}
	
	public void applyGravity()
	{
		if (!grounded())
		{
			//check if inside an object before setting position then adjust
			setPosition(new Point(position.x, position.y - MarioGame.world.gravity * weight));	
		}
		else
		{
			//snap position
		}
	}

	public void update()
	{
		if (followsGravity) 
		{
			applyGravity();
		}
	}
}
