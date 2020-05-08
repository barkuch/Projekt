package pl.edu.pw.fizyka.pojava.Trela_Kucharski;

import java.awt.Graphics2D;
import java.awt.Image;


import javax.swing.ImageIcon;

import pl.edu.pw.fizyka.pojava.Trela_Kucharski.GlobalPosition;

public class Object extends GlobalPosition
{

	private String plyerimage = "/images/Arrow.png";
	
	double Vx; //wartosic kt�re s� ze sliderow
	double Vy;	//  odpowiadaja za ruch
	
	double dt = 0.1; //krok ca�kowania
	
	float t;
	
	public Object(int x, int y)
	{
		super(x, y);
		
	}
	
	public void move() //tu konrtolujemy jego ruch?
	{
			 
	Vy += 9.81f*dt;
	y += Vy * dt;
	x += Vx * dt;		

	//WLA�CIWY WZ�R NA RZUT UKO�NY, JEDNAK�� NIE DZIA�A POPRAWNIE
/*
	x =  (int) (Vx / (controlPanel.resistance/controlPanel.mass) * 
			(1 - Math.pow(2.72f, (-controlPanel.resistance * t) / controlPanel.mass)));
	
	y =  (int) (Vy / (controlPanel.resistance/controlPanel.mass) + 
			9.81f / (controlPanel.resistance/controlPanel.mass) * 
			((1 - Math.pow(2.72f, (-controlPanel.resistance * t) / 
					controlPanel.mass)))- 9.81f / (controlPanel.resistance/controlPanel.mass));	
	
	t+=dt;
*/	
		
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
		if (y == 380)
		{
			pl.edu.pw.fizyka.pojava.Trela_Kucharski.AnimationPanel.gamelooptimer.stop(); //zatrzymanie animacji po zetkni�ciu si� strzaly z ziemi�	
			
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
