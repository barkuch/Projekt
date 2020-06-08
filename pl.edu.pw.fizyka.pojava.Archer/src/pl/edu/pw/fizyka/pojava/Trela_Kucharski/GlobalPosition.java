package pl.edu.pw.fizyka.pojava.Trela_Kucharski;

	//BARTOSZ KUCHARSKI
public class GlobalPosition 
{
	
	double Vx; 	//wartosic kt�re s� ze sliderow
	double Vy;	//  odpowiadaja za ruch
	
	double dt = 0.1; //krok ca�kowania
	
	public int x, y;
	
	public GlobalPosition(int x, int y) 
	{
		this.x = x; //po�o�enie
		this.y = y;
	}
	public void move() //tu konrtolujemy jego ruch
	{
	//		 
		Vy += 9.81f*dt;
		y += Vy * dt;
		x += Vx * dt;		

	//WLA�CIWY WZ�R NA RZUT UKO�NY, JEDNAK�� NIE DZIA�A POPRAWNIE
/*
		x =   (int) (Vx / (controlPanel.resistance/controlPanel.mass) * 
				(1 - Math.pow(2.72f, (-controlPanel.resistance * t) / controlPanel.mass)));
	
		y =   (int) (Vy / (controlPanel.resistance/controlPanel.mass) + 
				9.81f / (controlPanel.resistance/controlPanel.mass) * 
				((1 - Math.pow(2.72f, (-controlPanel.resistance * t) / 
					controlPanel.mass)))- 9.81f / (controlPanel.resistance/controlPanel.mass));	
	
		t+=dt;
	*/
		
		//KOLIZJE ZE SCIANA
		if (x < 0)
				x = 0;		
		if (y < 0)
				y = 0;		
		if (x > 973)		
				x = 973;		
		if (y > 380)		
				y = 380;		
		if (y == 380)	//zatrzymanie animacji po zetkni�ciu si� strzaly z ziemi�			
			pl.edu.pw.fizyka.pojava.Trela_Kucharski.AnimationPanel.gamelooptimer.stop(); 
					
	}
	
	public void reset(int xx, int yy, double vX, double vY)
	{		
		x = xx;
		y = yy;
		Vx = 0;
		Vy = 0;		
	}
		
	public void load(int xxx, int yyy)
	{		
		x = xxx;
		y = yyy;
	
	}
}
