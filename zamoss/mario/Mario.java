package zamoss.mario;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Mario extends AbstractMovingSprite
{
	public int startingLives;
	public int lives;
	public Direction direction;
	public boolean running;
	public boolean crouching;
	public boolean moving;
	public int runSpeed = 3, walkSpeed = 1;
	private BufferedImage walk_0, walk_1, up_0, crouch_0;

	//in the future, save the starting and ending positions with the world
	public Mario(int startingLives, Point startPos)
	{
		lives = startingLives;
		setPosition(startPos);
		followsGravity = true;
		setUpdateFrame(100);
		crouching = false;
		running = false;
		bounds = new Rectangle(startPos.x,startPos.y,16,24);
		try
		{
			walk_0 = ImageIO.read(new File("src/sprites/players/mario/small_walk_0.png"));
			walk_1 = ImageIO.read(new File("src/sprites/players/mario/small_walk_1.png"));
			up_0 = ImageIO.read(new File("src/sprites/players/mario/small_up_0.png"));
			crouch_0 = ImageIO.read(new File("src/sprites/players/mario/small_crouch_0.png"));
			sprites = new ArrayList<BufferedImage>();
			sprites.add(walk_0);
			sprites.add(walk_1);
			sprites.add(up_0);
			sprites.add(crouch_0);
		}
		catch (Exception e)
		{
			System.out.println("e" + e);
		}
		direction = Direction.East;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (!crouching)
		{
			bounds = new Rectangle(position.x,position.y,16,24);	
		}
		else if (crouching)
		{
			bounds = new Rectangle(position.x, position.y - 8, 16, 16);
		}
	}
	
	public int getMoveSpeed()
	{
		return (running) ? runSpeed : walkSpeed;
	}

	@Override
	public void move()	
	{
		if (!crouching)
		{
			if (direction == Direction.East)
			{
				position.x += getMoveSpeed();
			}
			else if (direction == Direction.West)
			{
				position.x -= getMoveSpeed();
			}	
		}
	}
	
	public void setRunSprite()
	{
		if (!crouching)
		{
			if (spriteIndex < 1)
			{
				spriteIndex++;
			}
			else
			{
				spriteIndex = 0;
			}
		}
	}

	public void jump()
	{
		//implement properly soon, probably should thread this
		position.y += 15;
	}
	
	public void crouch()
	{
		bounds = new Rectangle(position.x, position.y, 16, 16);
		spriteIndex = 3;
		crouching = true;
	}
	
	public void uncrouch()
	{
		bounds = new Rectangle(position.x, position.y, 16, 24);
		spriteIndex = 0;
		crouching = false;
	}
	
	public void climb()
	{
		
	}

}
