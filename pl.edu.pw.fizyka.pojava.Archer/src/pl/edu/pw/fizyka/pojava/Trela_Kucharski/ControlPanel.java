package pl.edu.pw.fizyka.pojava.Trela_Kucharski;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



	//ALEKSANDRA TRELA, BARTOSZ KUCHARSKI
public class ControlPanel extends JFrame 
{
	 
	
		private static final long serialVersionUID = 1L;

		private static final int SLIDER_MIN1 = 0;  //ustawiam warto�� minimaln� suwaka 1 i 2
	    private static final int SLIDER_MAX1 = 90;  //ustawiam warto�� maksymaln� suwaka 1 i 2
	    private static final int SLIDER_INIT1 = 0;  //ustawiam warto�� pocz�tkow� suwaka 1 i 2
	    private static final int SLIDER_MIN2 = 0;  
	    private static final int SLIDER_MAX2 = 30;  
	    private static final int SLIDER_INIT2 = 0;  
	   
	    static final double gravity = 9.80665; //stale przyspieszenie ziemiskie do obliczen
	   
	    
	    //variable values
	    static int resistance;	 
	    static double gravAcceleration; //gravAcceleration
	    static double gravAcceleration1 = 9.8; 
	    static double gravAcceleration2 = 3.7;
	    static double gravAcceleration3 = 23.1;
	    static int speedValue; 	//zmienne niezb�dne do ChangeListenera
	    static int angleValue;
	    
	   
	    static double range;
	    static double maxheight;
	    static double flighttime;	   	 
	    	   
	    //menu
	    JMenuBar menuBar;  //Tworz� pasek, w kt�rym umieszczam 2 opcje: Menu oraz More
	    JMenu menu;
	    JMenu more;
	    JMenuItem itemExit; //Tworz� elementy, kt�re b�d� zawarte w opcjach Menu i More
	    static JMenuItem itemSave;	   
	    JMenuItem itemLoad;
	    static JMenuItem itemAuthors;

	    //panels
	    JFrame frame;			//Tworz� 2 panele
		static AnimationPanel AnimationPanel;
		JPanel buttonsPanelLine_End, buttonsPanelPage_End;
	    
		public int intWidth = 1235; 
		public int intHeight = 500; 
		
		//bottom panel
		static JSlider sliderAngleValue;  //Tworz� 2 suwaki 
	    static JSlider sliderSpeedValue;  	  	   
	    static JTextField textAirResistance; //Tworz� pola niezb�dne pola tekstowe, guziki, etykiety oraz pole wyboru
	    static JTextField textFlightTime;	
	    static JTextField textMaxHeight;
	    static JTextField textRange;	    	        
	    static JButton buttonRandom;
	    static JButton buttonStart;
	    static JButton buttonStop;
	    static JButton buttonRestart;
	    static JLabel labelAngleValue;
	    static JLabel labelSpeedValue;
	    
	    static JLabel labelGravAcceleration;
	    static JComboBox<String> comboboxGravAcceleration;
	  
	     //Save file
	   //  public static Scanner skaner;
	  //   public static String file_speedValue, file_angleValue, file_mass, 
	   // 					file_textAirResistance, file_textFlightTime, 
	   // 						file_textMaxHeight, file_textRange;
		
	   //  public static String inFile;
	    
	     public ControlPanel() 
	     {
	    	
	    	setTitle("Archer");
			setSize(intWidth, intHeight);			
			this.setLayout(new BorderLayout());
			
			//Menu
			menuBar = new JMenuBar();   
	        	menu = new JMenu("Menu");	 		        		
	        		itemSave = new JMenuItem("Zapisz dane");//dodaje opcje do Menu wybrane opcje
	        		itemSave.addActionListener(aL);
	        			        		
	        		itemLoad = new JMenuItem("Wczytaj dane"); 	// niestety tekst wyswietla si� tylko w jednym polu
	        		itemLoad.addActionListener(aL);
	        			        		
	        		itemExit = new JMenuItem("Wyj�cie");
	        		itemExit.addActionListener(aL);	        		        		        	
	        	menu.add(itemSave);	//dodaje elementy do Menu   
	        	menu.add(itemLoad);
				menu.addSeparator(); //ta kreska miedzy nimi
	        	menu.add(itemExit);
	      
	        	more = new JMenu("More");
					itemAuthors = new JMenuItem("Tw�rcy"); 	//wy�wietlenie informacji na temat autor�w programu
					itemAuthors.addActionListener(aL);										
				more.add(itemAuthors);	//dodaje elementy do More
	        menuBar.add(menu); //dodaje opcje do paska
	        menuBar.add(more);
	        setJMenuBar(menuBar);
	        
	        
			//PANEL ANIMACJI
			AnimationPanel = new AnimationPanel();					
			this.add(AnimationPanel, BorderLayout.CENTER);
			
					
	       //PANEL STEROWANIA
			buttonsPanelLine_End = new JPanel();
			buttonsPanelLine_End.setLayout(new GridLayout(12,1));
	   	        
			labelAngleValue = new JLabel("K�t nachylenia �uku do ziemi: 0 �"); //Dodaje etykiet� nad suwakiem 1	   
			buttonsPanelLine_End.add(labelAngleValue); 	
  	
	    	sliderAngleValue = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN1, SLIDER_MAX1, SLIDER_INIT1);	     
	    	sliderAngleValue.setMajorTickSpacing(30);  //warto�ci na podzia�ce co 30
	    	sliderAngleValue.setMinorTickSpacing(5);  //ka�dy kolejny punkt na podzia�ce wi�kszy o 5
	    	sliderAngleValue.setPaintLabels(true);
	    	sliderAngleValue.addChangeListener(new SliderChangeListener());	//dodaje ChangeListener
	    	buttonsPanelLine_End.add(sliderAngleValue);
	     	
	    	labelSpeedValue = new JLabel("Pr�dko�� strza�y: 0 m/s"); //Dodaje etykiet� nad suwakiem 2	
	    	buttonsPanelLine_End.add(labelSpeedValue);
	    	
	    	sliderSpeedValue = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN2, SLIDER_MAX2, SLIDER_INIT2);	 
	    	sliderSpeedValue.setMajorTickSpacing(10);  //warto�ci na podzia�ce co 10
	    	sliderSpeedValue.setMinorTickSpacing(2);  //ka�dy kolejny punkt na podzia�ce wi�kszy o 2
	    	sliderSpeedValue.setPaintLabels(true);
	    	sliderSpeedValue.addChangeListener(new SliderChangeListener());	//dodaje ChangeListener        	         
	    	buttonsPanelLine_End.add(sliderSpeedValue);
			    	
	    	labelGravAcceleration = new JLabel("Przyspieszenie grawitacjne: ");  //etykieta z wyswietlana mas�
	    	buttonsPanelLine_End.add(labelGravAcceleration); 
	    	
	    	comboboxGravAcceleration = new JComboBox<String>(); //dodaje pole wyboru oraz tworz� mu opcje
	    	comboboxGravAcceleration.addItem("na Ziemi");
	    	comboboxGravAcceleration.addItem("na Marsie");
	    	comboboxGravAcceleration.addItem("na Jowiszu");
	    	comboboxGravAcceleration.setActionCommand("cGravAcceleration");	
	    	comboboxGravAcceleration.addActionListener(aL);
	    	    		    
	    	buttonsPanelLine_End.add(comboboxGravAcceleration);
	    	    	
	    	textAirResistance = new JTextField("");  //pole tekstowe, w kt�rym wy�wietla� si� b�dzie op�r powietrza
	    	textAirResistance.setEditable(false);
	    	buttonsPanelLine_End.add(textAirResistance);
	    	
	   		buttonRandom = new JButton("Losuj op�r powietrza");  //przycisk Losuj, kt�ry generuje liczb� z zakresu 1-100	
	   		buttonRandom.addActionListener(aL);	   	    
	    	buttonsPanelLine_End.add(buttonRandom);
	    	
	    	textFlightTime = new JTextField("Czas lotu strza�y"); //pole tekstowe, w kt�rym wy�wietla� si� b�dzie Czas lotu strza�y 
	    	textFlightTime.setEditable(false);
	    	buttonsPanelLine_End.add(textFlightTime);
	   	    	 	       
	    	textMaxHeight = new JTextField("Max. wysoko��"); //pole tekstowe, w kt�rym wy�wietla� si� b�dzie Maksymalna wysoko�� 
	    	textMaxHeight.setEditable(false);
	    	buttonsPanelLine_End.add(textMaxHeight);
	    	 	 
	    	textRange = new JTextField("Zasi�g strza�y");  //pole tekstowe, w kt�rym wy�wietla� si� b�dzie Zasi�g strza�y 
	    	textRange.setEditable(false);
	    	buttonsPanelLine_End.add(textRange);	  
	    	this.add(buttonsPanelLine_End, BorderLayout.LINE_END);
	              	         
	    	
	    	buttonsPanelPage_End = new JPanel();  			    	
	    	buttonStart = new JButton("Start");  //przycisk, kt�ry pozwoli uruchomi� i wstrzyma� gr�		
	    	buttonStart.addActionListener(aL);	    		    
			buttonsPanelPage_End.add(buttonStart); 
			
			buttonStop = new JButton("Stop");  //przycisk, kt�ry pozwoli uruchomi� i wstrzyma� gr�	
			buttonStop.addActionListener(aL);  
			buttonsPanelPage_End.add(buttonStop); 
			
			buttonRestart = new JButton("Reset");  //przycisk wyzerowania wszystkich komponent�w oraz resetuj�cy po�o�enie strza�y		
			buttonRestart.addActionListener(aL);		
			buttonsPanelPage_End.add(buttonRestart); 
			this.add(buttonsPanelPage_End, BorderLayout.PAGE_END);	   
	    	    		       	      	   
	    }
	    	 
	Actions aL = new Actions();
	     
	     
	public class SliderChangeListener implements ChangeListener  //klasa implementacyjna suwaka
    {
		@Override
		public void stateChanged(ChangeEvent e) 
		{
			speedValue = sliderSpeedValue.getValue();
			labelSpeedValue.setText("Pr�dko�� strza�y: " + speedValue + " m/s");	
							
			angleValue = sliderAngleValue.getValue();
			labelAngleValue.setText("K�t nachylenia �uku do ziemi: " + angleValue + " �");												
				
			AnimationPanel.Arrow.Vx = Actions.Vx(speedValue, angleValue);
			AnimationPanel.Arrow.Vy = Actions.Vy(speedValue, angleValue);
		}	
	}	 
	 
	
	

	
}
