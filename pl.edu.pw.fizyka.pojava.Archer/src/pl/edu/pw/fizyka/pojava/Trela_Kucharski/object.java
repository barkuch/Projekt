package pl.edu.pw.fizyka.pojava.Trela_Kucharski;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import pl.edu.pw.fizyka.pojava.Trela_Kucharski.GlobalPosition;

public class object extends GlobalPosition
{

	private String plyerimage = "/images/Arrow.png";
	
	double Vx; //wartosic które s¹ ze sliderow
	double Vy;	//  odpowiadaja za ruch
	
	
	
	public object(int x, int y)
	{
		super(x, y);
		
	}
	
	public void move() //tu konrtolujemy jego ruch?
	{
		//x+= controlPanel.speedValue / controlPanel.mass; //predkosc ruchy: x+=2
		x+=Vx;
		y+=Math.pow(Vy,2);
	
		
		//KOLIZJE ZE SCIANA
		if (x < 0)
		{
			x = 0;
		}
		if (y < 0)
		{
			y = 0;
		}
		if (x > 1140)
		{
			x = 1140;
		}
		if (y > 380)
		{
			y = 380;
		}
	}
	

	public void reset(int xx, int yy, double vX, double vY)
	{		
		x = xx;
		y = yy;
		Vx = 0;
		Vy = 0;		
	}
	
	
	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(getPlayerImage(), x, y, null);
	}
	public Image getPlayerImage()
	{
		ImageIcon i = new ImageIcon(getClass().getResource(plyerimage));
		return i.getImage();
	}
}

