package zamoss.mario.blocks;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import zamoss.mario.Direction;

public class MysteryBlock extends Block
{
	public boolean used;
	
	public MysteryBlock()
	{
		try {
			sprites = new ArrayList<BufferedImage>();
			sprites.add(ImageIO.read(new File("src/sprites/blocks/mystery/mystery_0.png")));
			sprites.add(ImageIO.read(new File("src/sprites/blocks/mystery/mystery_1.png")));
			sprites.add(ImageIO.read(new File("src/sprites/blocks/mystery/mystery_2.png")));
			sprites.add(ImageIO.read(new File("src/sprites/blocks/mystery/mystery_3.png")));
			sprites.add(ImageIO.read(new File("src/sprites/blocks/mystery/mystery_4.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spriteIndex = 0;
		used = false;
	}
	
	@Override
	public void hit(Direction dir) {
		// TODO Auto-generated method stub
		if (dir.equals(Direction.South) && !used)
		{
			//drop item
			spriteIndex = 4;
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (zIndex > 3)
		{
			zIndex = 0;
		}
		else
		{
			zIndex++;
		}
	}
	
	@Override
	public String getName()
	{
		return "Mystery Block";
	}

}
