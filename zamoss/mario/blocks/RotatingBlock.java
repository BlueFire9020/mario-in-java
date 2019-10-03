package zamoss.mario.blocks;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import zamoss.mario.Direction;

public class RotatingBlock extends Block
{	
	public boolean touched;
	
	public RotatingBlock()
	{
		try {
			sprites = new ArrayList<BufferedImage>();
			sprites.add(ImageIO.read(new File("src/sprites/blocks/rotating/rotating_0.png")));
			sprites.add(ImageIO.read(new File("src/sprites/blocks/rotating/rotating_1.png")));
			sprites.add(ImageIO.read(new File("src/sprites/blocks/rotating/rotating_2.png")));
			sprites.add(ImageIO.read(new File("src/sprites/blocks/rotating/rotating_3.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spriteIndex = 0;
	}
	
	@Override
	public void update()
	{
		//implement touched later!
		if (touched)
		{
			if (spriteIndex < 3)
			{
				spriteIndex++;
			}
			else
			{
				spriteIndex = 0;
			}	
		}
	}
	
	@Override
	public void hit(Direction dir) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getName()
	{
		return "Rotating Block";
	}

}
