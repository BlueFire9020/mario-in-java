package zamoss.mario;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdjustmentGUI extends JPanel implements MouseListener
{
	
	public int mouseX, mouseY;
	public Timer repaintTimer;
	public Point testPos = new Point(100, 200);
	
	public AdjustmentGUI()
	{
		repaintTimer = new Timer();
		repaintTimer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				repaint();
			}
			
			
		}, 0, 1);
	}
	
	public void paintComponent(Graphics page)
	{
		Graphics2D g = (Graphics2D) page;
		
		Point mPos = this.getMousePosition();
		Point adPos = new Point(mPos.x, getHeight() - mPos.y);
		page.drawString("Actual" + mPos.x + " " + mPos.y, mPos.x, mPos.y - 10);
		page.drawString("Adjusted" + adPos.x + " " + adPos.y, mPos.x, mPos.y + 10);
		
		g.translate(adPos.x, adPos.y);
	}
	
	private static void runGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);

		JFrame frame = new JFrame("Mario's Java Adventure");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		AdjustmentGUI minesweeperGUI = new AdjustmentGUI();
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


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
