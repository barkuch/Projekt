package pl.edu.pw.fizyka.pojava.Trela_Kucharski;



import java.awt.Graphics2D;
import java.awt.Image;


import javax.swing.ImageIcon;


	//BARTOSZ KUCHARSKI
public class Object extends GlobalPosition
{

	private String plyerimage = "/images/Arrow.png";

	public Object(int x, int y)
	{
		super(x, y);		
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

