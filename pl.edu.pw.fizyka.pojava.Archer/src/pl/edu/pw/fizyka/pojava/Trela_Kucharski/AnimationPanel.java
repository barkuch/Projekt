package pl.edu.pw.fizyka.pojava.Trela_Kucharski;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;



public class AnimationPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private String background = "/images/background.png";
	
	int intHeight; 
	int intWidth;
		
	public static Timer gamelooptimer;
	
	Object arrow =new Object(10,360); //po³o¿enie pocz¹tkowe obiektu


	public AnimationPanel()
	{
		
		gamelooptimer = new Timer(10, this); //ze co sto milisknd sie powatarza		
		//gamelooptimer.start();
		
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(getBackgroundImage(), 0, 0, null);
		arrow.draw(g2d);		
	}

	public Image getBackgroundImage()
	{
		ImageIcon i = new ImageIcon(getClass().getResource(background));
		return i.getImage();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		arrow.move();		
		repaint();
	}
	public void setSize( int intH, int intW) 
	{ 
		intHeight = intH; 
		intWidth = intW;
	}
	
}

	

