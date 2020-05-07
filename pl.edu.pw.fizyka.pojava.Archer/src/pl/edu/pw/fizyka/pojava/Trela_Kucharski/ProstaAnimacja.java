package pl.edu.pw.fizyka.pojava.Trela_Kucharski;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class PanelAnimowany extends JPanel implements Runnable 
{

	// zmienne x,y modyfikowane przez watek
	int x = 0, y = 0;

	public PanelAnimowany()
	{
		super();
		setPreferredSize(new Dimension(300, 300));
	}

	// metoda run() zmienajaca x,y co 20 ms
	public void run() 
	{

		while (x < 200) 
		{
			x++;
			y++;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			repaint(); //repaint domyslnie odpali sie w EDT
		}

	}

	public void paintComponent(Graphics g) 
	{

		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		g.fillArc(x, y, 100, 100, 50,30);

	}

}

public class ProstaAnimacja 
{

	public static void main(String[] args) 
	{

		SwingUtilities.invokeLater(new Runnable() 
		{

			@Override
			public void run() 
			{

				JFrame f = new JFrame();
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(350, 350);
				f.setLocation(50,50);
				PanelAnimowany panel = new PanelAnimowany();
				f.add(panel);
				f.setVisible(true);

				ExecutorService exec = Executors.newFixedThreadPool(1);
				exec.execute(panel);
				exec.shutdown();

				// new Thread(panel).start();

			}
		});

	}

	public void draw(Graphics2D g2d)
	{
		// TODO Auto-generated method stub
		
	}

}
