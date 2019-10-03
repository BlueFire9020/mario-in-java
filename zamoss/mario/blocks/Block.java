package zamoss.mario.blocks;

import java.awt.Rectangle;

import zamoss.mario.AbstractSprite;
import zamoss.mario.Direction;

public abstract class Block extends AbstractSprite
{	
	protected boolean destructible;
	
	@Override
	public boolean followsGravity() 
	{
		return false;
	}
	
	@Override
	public int getUpdateFrame()
	{
		return 150;
	}
	
	@Override
	public Rectangle getBounds()
	{
		return new Rectangle(position.x,position.y,16,16);
	}
	
	@Override
	public boolean hasCollision()
	{
		return true;
	}
	
	public abstract void hit(Direction dir);
}
