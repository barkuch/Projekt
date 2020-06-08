package pl.edu.pw.fizyka.pojava.Trela_Kucharski;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Random;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



	//ALEKSANDRA TRELA, BARTOSZ KUCHARSKI
public class ControlPanel extends JFrame 
{
	 
	
		private static final long serialVersionUID = 1L;

		private static final int SLIDER_MIN1 = 1;  //ustawiam wartoœæ minimaln¹ suwaka 1 i 2
	    private static final int SLIDER_MAX1 = 90;  //ustawiam wartoœæ maksymaln¹ suwaka 1 i 2
	    private static final int SLIDER_INIT1 = 1;  //ustawiam wartoœæ pocz¹tkow¹ suwaka 1 i 2
	    private static final int SLIDER_MIN2 = 1;  
	    private static final int SLIDER_MAX2 = 30;  
	    private static final int SLIDER_INIT2 = 1;  
	   
	    static final double g = 9.80665; //stale przyspieszenie ziemiskie do obliczen
	   
	    
	    //variable values
	    static int resistance;	 
	    static int mass1 = 7; 
	    static int mass2 = 2;
	    static int mass3 = 13;
	    static int speedValue; 	//zmienne niezbêdne do ChangeListenera
	    static int angleValue;
	    static int mass;
	   
	    static double range;
	    static double maxheight;
	    static double flighttime;	   	 
	    	   
	    //menu
	    JMenuBar menuBar;  //Tworzê pasek, w którym umieszczam 2 opcje: Menu oraz More
	    JMenu menu;
	    JMenu more;
	    JMenuItem itemExit; //Tworzê elementy, które bêd¹ zawarte w opcjach Menu i More
	    static JMenuItem itemSave;	   
	    JMenuItem itemLoad;
	    static JMenuItem itemAuthors;

	    //panels
	    JFrame frame;			//Tworzê 2 panele
		static AnimationPanel AnimationPanel;
		JPanel buttonsPanelLine_End, buttonsPanelPage_End;
	    
		public int intWidth = 1227; 
		public int intHeight = 500; 
		
		//bottom panel
		static JSlider sliderAngleValue;  //Tworzê 2 suwaki 
	    static JSlider sliderSpeedValue;  	  	   
	    static JTextField textAirResistance; //Tworzê pola niezbêdne pola tekstowe, guziki, etykiety oraz pole wyboru
	    static JTextField textFlightTime;	
	    static JTextField textMaxHeight;
	    static JTextField textRange;	    	        
	    static JButton buttonRandom;
	    static JButton buttonStart;
	    static JButton buttonStop;
	    static JButton buttonRestart;
	    static JLabel labelAngleValue;
	    static JLabel labelSpeedValue;
	    
	    static JLabel labelMass;
	    static JComboBox<String> comboboxMass;
	  
	     //Save file
	     public static Scanner skaner;
	     public static String file_speedValue, file_angleValue, file_mass, 
	    					file_textAirResistance, file_textFlightTime, 
	    						file_textMaxHeight, file_textRange;
		
	     public static String inFile;
	    
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
	        			        		
	        		itemLoad = new JMenuItem("Wczytaj dane"); 	// niestety tekst wyswietla siê tylko w jednym polu
	        		itemLoad.addActionListener(aL);
	        			        		
	        		itemExit = new JMenuItem("Wyjœcie");
	        		itemExit.addActionListener(aL);	        		        		        	
	        	menu.add(itemSave);	//dodaje elementy do Menu   
	        	menu.add(itemLoad);
				menu.addSeparator(); //ta kreska miedzy nimi
	        	menu.add(itemExit);
	      
	        	more = new JMenu("More");
					itemAuthors = new JMenuItem("Twórcy"); 	//wyœwietlenie informacji na temat autorów programu
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
	   	        
			labelAngleValue = new JLabel("K¹t nachylenia ³uku do ziemi: 1 °"); //Dodaje etykietê nad suwakiem 1	   
			buttonsPanelLine_End.add(labelAngleValue); 	
  	
	    	sliderAngleValue = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN1, SLIDER_MAX1, SLIDER_INIT1);	    
	    	sliderAngleValue.setPreferredSize(new Dimension(200, 50));  //rozmiar suwaka
	    	sliderAngleValue.setMajorTickSpacing(30);  //wartoœci na podzia³ce co 30
	    	sliderAngleValue.setMinorTickSpacing(5);  //ka¿dy kolejny punkt na podzia³ce wiêkszy o 5
	    	sliderAngleValue.setPaintLabels(true);
	    	sliderAngleValue.addChangeListener(new SliderChangeListener());	//dodaje ChangeListener
	    	buttonsPanelLine_End.add(sliderAngleValue);
	     	
	    	labelSpeedValue = new JLabel("Prêdkoœæ strza³y: 1 m/s"); //Dodaje etykietê nad suwakiem 2	
	    	buttonsPanelLine_End.add(labelSpeedValue);
	    	
	    	sliderSpeedValue = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN2, SLIDER_MAX2, SLIDER_INIT2);
	    	sliderSpeedValue.setPreferredSize(new Dimension(200, 50)); 
	    	sliderSpeedValue.setMajorTickSpacing(10);  //wartoœci na podzia³ce co 10
	    	sliderSpeedValue.setMinorTickSpacing(2);  //ka¿dy kolejny punkt na podzia³ce wiêkszy o 2
	    	sliderSpeedValue.setPaintLabels(true);
	    	sliderSpeedValue.addChangeListener(new SliderChangeListener());	//dodaje ChangeListener        	         
	    	buttonsPanelLine_End.add(sliderSpeedValue);
			    	
	    	labelMass = new JLabel("Masa wybranej strza³y: ");  //etykieta z wyswietlana mas¹
	    	buttonsPanelLine_End.add(labelMass); 
	    	
	    	comboboxMass = new JComboBox<String>(); //dodaje pole wyboru oraz tworzê mu opcje
	    	comboboxMass.addItem("stalowa");
	    	comboboxMass.addItem("aluminiowa");
	    	comboboxMass.addItem("tytanowa");
	    	comboboxMass.setActionCommand("cMass");	
	    	comboboxMass.addActionListener(aL);
	    	    		    
	    	buttonsPanelLine_End.add(comboboxMass);
	    	    	
	    	textAirResistance = new JTextField("");  //pole tekstowe, w którym wyœwietlaæ siê bêdzie opór powietrza
	    	textAirResistance.setEditable(false);
	    	buttonsPanelLine_End.add(textAirResistance);
	    	
	   		buttonRandom = new JButton("Losuj opór powietrza");  //przycisk Losuj, który generuje liczbê z zakresu 1-100	
	   		buttonRandom.addActionListener(aL);	   	    
	    	buttonsPanelLine_End.add(buttonRandom);
	    	
	    	textFlightTime = new JTextField("Czas lotu strza³y"); //pole tekstowe, w którym wyœwietlaæ siê bêdzie Czas lotu strza³y 
	    	textFlightTime.setEditable(false);
	    	buttonsPanelLine_End.add(textFlightTime);
	   	    	 	       
	    	textMaxHeight = new JTextField("Max. wysokoœæ"); //pole tekstowe, w którym wyœwietlaæ siê bêdzie Maksymalna wysokoœæ 
	    	textMaxHeight.setEditable(false);
	    	buttonsPanelLine_End.add(textMaxHeight);
	    	 	 
	    	textRange = new JTextField("Zasiêg strza³y");  //pole tekstowe, w którym wyœwietlaæ siê bêdzie Zasiêg strza³y 
	    	textRange.setEditable(false);
	    	buttonsPanelLine_End.add(textRange);	  
	    	this.add(buttonsPanelLine_End, BorderLayout.LINE_END);
	              	         
	    	
	    	buttonsPanelPage_End = new JPanel();  			    	
	    	buttonStart = new JButton("Start");  //przycisk, który pozwoli uruchomiæ i wstrzymaæ grê		
	    	buttonStart.addActionListener(aL);	    		    
			buttonsPanelPage_End.add(buttonStart); 
			
			buttonStop = new JButton("Stop");  //przycisk, który pozwoli uruchomiæ i wstrzymaæ grê	
			buttonStop.addActionListener(aL);  
			buttonsPanelPage_End.add(buttonStop); 
			
			buttonRestart = new JButton("Reset");  //przycisk wyzerowania wszystkich komponentów oraz resetuj¹cy po³o¿enie strza³y		
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
			labelSpeedValue.setText("Prêdkoœæ strza³y: " + speedValue + " m/s");	
							
			angleValue = sliderAngleValue.getValue();
			labelAngleValue.setText("K¹t nachylenia ³uku do ziemi: " + angleValue + " °");												
				
			AnimationPanel.Arrow.Vx = Vx(speedValue, angleValue, mass, resistance);
			AnimationPanel.Arrow.Vy = Vy(speedValue, angleValue, mass, resistance);
		}	
	}	 
	 
	
	public static double Vy(int alfa, int v, int mas, int res)
	{
		return -(v*(Math.sin(Math.toRadians(alfa)))); //"zdefiniowanie Vx jako 	speedValue * sin(angleValue)"
	}															//dodanie 12 u¿yte w celu uzyskania 
																//wiarygodniejszej jakosci animacji
	public static double Vx( int v, int alfa, int mas, int res)
	{
		return (v*(Math.cos(Math.toRadians(alfa))) * mas); //mno¿enie przez 8 u¿yte w celu uzyskania 
														//wiarygodniejszej jakosci animacji
	}

	
}
