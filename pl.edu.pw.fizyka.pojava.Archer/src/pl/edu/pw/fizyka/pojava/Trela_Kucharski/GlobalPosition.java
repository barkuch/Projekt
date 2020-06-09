package pl.edu.pw.fizyka.pojava.Trela_Kucharski;

	//BARTOSZ KUCHARSKI
public class GlobalPosition 
{
	
	double Vx; 	//wartosic które s¹ ze sliderow
	double Vy;	//  odpowiadaja za ruch
	
	double dt = 0.1; //krok ca³kowania
	
	public int x, y;
	
	public GlobalPosition(int x, int y) 
	{
		this.x = x; //po³o¿enie
		this.y = y;
	}
	public void move() //tu konrtolujemy jego ruch
	{
			 
		Vy += ControlPanel.gravAcceleration*dt;
		y += Vy * dt;
		x += Vx * dt;		
	
		//KOLIZJE ZE SCIANA
		if (x < 0)
				x = 0;		
		if (y < 0)
				y = 0;		
		if (x > 973)		
				x = 973;		
		if (y > 380)		
				y = 380;		
		if (y == 380)	//zatrzymanie animacji po zetkniêciu siê strzaly z ziemi¹			
			pl.edu.pw.fizyka.pojava.Trela_Kucharski.AnimationPanel.gamelooptimer.stop(); 
					
	}
	
	public void reset(int xx, int yy, double vX, double vY)
	{		
		x = xx;
		y = yy;
		Vx = 0;
		Vy = 0;		
	}
		
	public void load(int xxx, int yyy) //stworzony na potrzeby ustaiwenia strza³y w pierwotnej pozycji
	{		
		x = xxx;
		y = yyy;
	
	}
	
}
