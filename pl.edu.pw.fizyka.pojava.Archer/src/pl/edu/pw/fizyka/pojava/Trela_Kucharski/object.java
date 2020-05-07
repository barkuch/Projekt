package pl.edu.pw.fizyka.pojava.Trela_Kucharski;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import pl.edu.pw.fizyka.pojava.Trela_Kucharski.GlobalPosition;

public class object extends GlobalPosition
{

	private String plyerimage = "Arrow.png";
	
	int velX = 10; //wartosic ktre beda ze sliderow
	int velY = 0;
	
	
	
	public object(int x, int y)
	{
		super(x, y);
		
	}
	
	public void move() //tu konrtolujemy jego ruch?
	{
		x+=velX; //predkosc ruchy: x+=2
		y+=velY;
		//x+=velX^3+velX;
		//y+=velY^44+velY;
		//x+=x+velX*velX+velY;          //lub tu uzycie wzoru?
		//y+=velX*velX+velY;
		//if(y <= 0 || y>= 380) velY *= -1;
		//if(x <= 0 || x>= 1128) velX *= 1;
		
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
	
	public void keyPressed(KeyEvent e) //odpowiada za kierunek ruchu zasady ruchu
	{
		int key = e.getKeyCode();
		
		if(key== KeyEvent.VK_RIGHT)			//tu uzycie wzoru na parobole zeby uzykac x i y
		{
			velX = 5;
		}
		else if(key == KeyEvent.VK_LEFT)
		{
			velX = -5;
		}
		else if(key == KeyEvent.VK_UP)
		{
			velY = -5;
		}
		else if(key == KeyEvent.VK_DOWN)
		{
			velY = 5;
		}
	}
	public void keyReleased(KeyEvent e)
	{
int key = e.getKeyCode();
		
		if(key== KeyEvent.VK_RIGHT)
		{
			velX = 0;
		}
		else if(key == KeyEvent.VK_LEFT)
		{
			velX = 0;
		}
		else if(key == KeyEvent.VK_UP)
		{
			velY = 0;
		}
		else if(key == KeyEvent.VK_DOWN)
		{
			velY = 0;
		}
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

