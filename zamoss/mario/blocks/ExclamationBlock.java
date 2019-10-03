package zamoss.mario.blocks;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import zamoss.mario.Direction;

public class ExclamationBlock extends Block 
{
	
	public ExclamationBlock()
	{
		try {
			sprites = new ArrayList<BufferedImage>();
			BufferedImage ex1 = ImageIO.read(new File("src/sprites/blocks/exclamation/exclamation_0.png"));
			sprites.add(ex1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spriteIndex = 0;
	}
	
	@Override
	public void hit(Direction dir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getName()
	{
		return "Exclamation Block";
	}

}
