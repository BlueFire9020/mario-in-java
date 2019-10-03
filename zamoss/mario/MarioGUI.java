package zamoss.mario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MarioGUI extends JPanel implements KeyListener
{
	public MarioGame game;
	public Timer timer;
	//man we have a lot of timers, this definitely needs a rework at some point
	public int frameCount = 0;
	public BufferedImage testBG;
	
	public Point cameraPos = new Point(0, 0);

	public MarioGUI()
	{
		game = new MarioGame();
		timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				//game.update();
				//how to create the crappiest system ever
				//use frame count
				//pepega
				if (frameCount == Integer.MAX_VALUE)
				{
					frameCount = 0;
				}
				else
				{
					frameCount++;	
				}
				for (AbstractSprite sprite : game.world.sprites)
				{
					if (frameCount % sprite.getUpdateFrame() == 0)
					{
						sprite.update();
					}
					if (frameCount % game.world.gravityFrame == 0 && sprite.followsGravity())
					{
						sprite.applyGravity();
					}
				}
				if (frameCount % game.world.gravityFrame == 0)
				{
					game.mario.applyGravity();
				}
				if (game.mario.moving && !game.mario.crouching)
				{
					if (game.mario.running && frameCount % 10 == 0 || frameCount % 15 == 0 && !game.mario.running)
					{
						game.mario.move();
					}
					if (game.mario.running && frameCount % 30 == 0 || frameCount % 75 == 0 && !game.mario.running)
					{
						game.mario.setRunSprite();
					}
				}
				else if (!game.mario.crouching)
				{
					if (game.mario.spriteIndex == 1)
					{
						game.mario.spriteIndex = 0;
					}
				}
				game.mario.update();
				paintImmediately(new Rectangle(0, 0, getWidth(), getHeight()));
			}
		}, 1, 1);
		//okay so i just realized that we could totally consolidate these two timers into one
		//and simplify the process significantly
		//4head
		addKeyListener(this);
		setFocusable(true);

		try {
			testBG = ImageIO.read(new File("src/sprites/backgrounds/mountains.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics page)
	{
		Point pos = game.mario.position;
		Dimension size = this.getSize();
		ArrayList<AbstractSprite> sprites = game.world.getSpritesInBounds(new Rectangle(pos.x - size.width / 2, pos.y - size.height / 2, size.width, size.height));
		ArrayList<String> diffs = new ArrayList<String>();
		page.drawImage(testBG, 0, 0, size.width, size.height, null);
		int finalDiv2 = 0;

		//LEGACY
		/*for (int i = 0; i < sprites.size(); i++) 
		{
			AbstractSprite sprite = sprites.get(i);
			//System.out.println("global:" + sprite.position + " vs player pos " + pos);
			//i think the calculations are right, now to convert it to screen space
			int wDiv = size.width / sprite.getBounds().width;
			int hDiv = size.height / sprite.getBounds().height;
			int finalDiv = wDiv > hDiv ? wDiv : hDiv;
			double XPosOffset = (double)(wDiv) / sprite.getBounds().width;
			double YPosOffset = (double)(hDiv) / sprite.getBounds().height;
			Point localpos = new Point(sprite.position.x - pos.x, sprite.position.y - pos.y);
			Point screenmid = new Point(size.width / 2, size.height / 2);
			Point screenpos = new Point(localpos.x + screenmid.x, (localpos.y + screenmid.y));

			int YOff2 = screenpos.y - size.width / 2;
			int XOff2 = screenpos.x - size.height / 2;

			//System.out.println(zoom);
			//System.out.println("local:" + localpos + " vs player pos " + new Point(0,0) + " vs screen pos " + screenpos + " an addition " + YPosOffset);
			//formulize that 1.8 value, but for now: 256x256 = 0.025, 512x512 = 1, 724x724 = 1.8
			page.drawImage(sprite.getSprite(), (int)(screenpos.x + XOff2 * 1.8), (int)(screenpos.y + YOff2 * 1.8), finalDiv, finalDiv, null);
			page.drawString((int)(screenpos.x + XOff2 * 1.8) + " " + (int)(screenpos.y + YOff2 * 1.8), (int)(screenpos.x + XOff2 * 1.8), (int)(screenpos.y + YOff2 * 1.8) - 10);
			page.drawString(localpos.x + " " + localpos.y, (int)(screenpos.x + XOff2 * 1.8), (int)(screenpos.y + YOff2 * 1.8));
			//FIX THIS LATER REMEMBER ALL THE POSITIONS ARE INVERTED WHEN USING JAVA DRAWING
			//let's just try something different for now, maybe we'll change this later.
			int xDiff = Math.abs(pos.x - sprite.position.x);
			int yDiff = Math.abs(pos.y + sprite.position.y);
			diffs.add(xDiff + " " + yDiff + " for " + sprite.getName());
			page.drawString(xDiff + " " + yDiff, xDiff * zoom, yDiff * zoom);
			page.drawImage(sprite.getSprite(), xDiff * zoom, yDiff * zoom, sprite.bounds.width * zoom, sprite.bounds.height * zoom, null);

			finalDiv2 = finalDiv;
		}*/

		//testing something
		Graphics2D g2d = (Graphics2D) page;
		
		//g2d.translate(0.0, getHeight());
		//somehow this makes a really cool system
		//idk what i did
		//g2d.translate(pos.x, pos.y + getHeight());
		//g2d.scale(1.0, -1.0);
		
		int mult = 3; 
		
		for (int i = 0; i < sprites.size(); i++) 
		{
			/*AbstractSprite sprite = sprites.get(i);
			//System.out.println("global:" + sprite.position + " vs player pos " + pos);
			//i think the calculations are right, now to convert it to screen space
			Point localpos = new Point((sprite.position.x - pos.x) * mult, (sprite.position.y - pos.y) * mult);
			Point screenmid = new Point(size.width / 2, size.height / 2);
			Point screenpos = new Point(screenmid.x + localpos.x, screenmid.y + localpos.y);
			
			//System.out.println(zoom);
			//System.out.println("local:" + localpos + " vs player pos " + new Point(0,0) + " vs screen pos " + screenpos + " an addition " + YPosOffset);
			//formulize that 1.8 value, but for now: 256x256 = 0.025, 512x512 = 1, 724x724 = 1.8
			page.drawString(localpos.x + " " + localpos.y, localpos.x, localpos.y);
			page.drawImage(sprite.getSprite(), screenpos.x, screenpos.y, sprite.getBounds().width * mult, sprite.getBounds().height * mult, null);
			//FIX THIS LATER REMEMBER ALL THE POSITIONS ARE INVERTED WHEN USING JAVA DRAWING
			//let's just try something different for now, maybe we'll change this later.
			int xDiff = Math.abs(pos.x - sprite.position.x);
			int yDiff = Math.abs(pos.y + sprite.position.y);
			diffs.add(xDiff + " " + yDiff + " for " + sprite.getName());
			page.drawString(xDiff + " " + yDiff, xDiff * zoom, yDiff * zoom);
			page.drawImage(sprite.getSprite(), xDiff * zoom, yDiff * zoom, sprite.bounds.width * zoom, sprite.bounds.height * zoom, null);
			 */
		}
		
		//YES IT FINALLY WORKS ZACH YOU'RE NOT A RETARD
		Point cameraOffset = new Point(getWidth() / 2, getHeight() / 2);
		cameraPos = new Point(game.mario.position.x - (cameraOffset.x / mult), game.mario.position.y - (cameraOffset.y / mult));
		page.drawRect(getWidth() / 2 - 5, getHeight() / 2 - 5, 10, 10);
		
		page.drawString(cameraPos.x + " " + cameraPos.y, 10, 10);
		
		for (int i = 0; i < sprites.size(); i++)
		{ 
			AbstractSprite sprite = sprites.get(i);
			
			Point cartesian = convertToCartesian(new Point((sprite.position.x - cameraPos.x) * mult, (sprite.position.y - cameraPos.y) * mult));
			
			Point localpos = new Point((cartesian.x), (cartesian.y));
			
			//System.out.println(localpos.x + " " + localpos.y);
			
			page.drawString(localpos.x + " " + localpos.y, localpos.x, localpos.y);
			page.drawRect(localpos.x, localpos.y, sprite.getBounds().width * mult, sprite.getBounds().height * mult);
			page.drawImage(sprite.getSprite(), localpos.x, localpos.y, sprite.getBounds().width * mult, sprite.getBounds().height * mult, null);
		}
					
		
		BufferedImage image = game.mario.getSprite();
		if (game.mario.direction.equals(Direction.East))
		{
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(-image.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			image = op.filter(image, null);		
		}

		Point cartesian = convertToCartesian(new Point((game.mario.position.x - cameraPos.x) * mult, (game.mario.position.y - cameraPos.y) * mult));
		
		Point localpos = new Point((cartesian.x), (cartesian.y));

		
		page.drawString(game.mario.position + "",  localpos.x, localpos.y);
		page.drawImage(image, localpos.x, localpos.y, (int)(game.mario.getBounds().width * mult), (int)(game.mario.getBounds().height * mult), null);
		
		cartesian = convertToCartesian(new Point((game.mario.bounds.x - cameraPos.x) * mult, (game.mario.bounds.y - cameraPos.y) * mult));		
		localpos = new Point((cartesian.x), (cartesian.y));
		
		page.drawRect(localpos.x, localpos.y, game.mario.getBounds().width * mult, game.mario.getBounds().height * mult);
		
		cartesian = convertToCartesian(new Point((game.mario.position.x + (game.mario.getBounds().width / 2) - cameraPos.x) * mult, (game.mario.bounds.y - cameraPos.y) * mult));		
		localpos = new Point((cartesian.x), (cartesian.y));
		
		page.setColor(Color.blue);
		page.drawRect(localpos.x - 2, localpos.y - 2, 4, 4);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) 
		{
			Direction newDir = null;
			if (e.getKeyCode() == KeyEvent.VK_A)
			{
				newDir = Direction.West;
			}
			else if (e.getKeyCode() == KeyEvent.VK_D)
			{
				newDir = Direction.East;
			}
			if (newDir != null)
			{
				game.mario.direction = newDir;
				game.mario.moving = true;
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_S)
		{
			System.out.println("crouching");
			game.mario.crouch();
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			game.mario.jump();
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT)
		{
			game.mario.running = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A)
		{
			//ensure animation is resting pose
			game.mario.moving = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_S)
		{
			game.mario.uncrouch();
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT)
		{
			game.mario.running = false;
		}
		//R1.interrupt();
	}
	
	
	public Point convertToCartesian(Point awtPoint)
	{
		return new Point(awtPoint.x, getHeight() - awtPoint.y);
	}

	//NOTE: ensure that these are eventually deprecated
	private static void runGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);

		JFrame frame = new JFrame("Mario's Java Adventure");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		MarioGUI minesweeperGUI = new MarioGUI();
		frame.add(minesweeperGUI);

		frame.pack();
		frame.setResizable(false);
		frame.setSize((724), (724));
		frame.setVisible(true);
	}


	public static void main(String[] args) {
		//Methods that create and show a GUI should be 
		//run from an event-dispatching thread
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				runGUI();
			}
		});
	}
}
