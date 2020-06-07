package pl.edu.pw.fizyka.pojava.Trela_Kucharski;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

	//ALEKSANDRA TRELA, BARTOSZ KUCHARSKI
public class ControlPanel extends JFrame 
{
	 
	
		private static final long serialVersionUID = 1L;

		private static final int SLIDER_MIN1 = 1;  //ustawiam warto�� minimaln� suwaka 1 i 2
	    private static final int SLIDER_MAX1 = 90;  //ustawiam warto�� maksymaln� suwaka 1 i 2
	    private static final int SLIDER_INIT1 = 1;  //ustawiam warto�� pocz�tkow� suwaka 1 i 2
	    private static final int SLIDER_MIN2 = 1;  
	    private static final int SLIDER_MAX2 = 30;  
	    private static final int SLIDER_INIT2 = 1;  
	   
	    static final double g = 9.80665; //stale przyspieszenie ziemiskie do obliczen
	   
	    
	    //variable values
	    static int resistance;	 
	    int mass1 = 7; 
	    int mass2 = 2;
	    int mass3 = 13;
	    static int speedValue; 	//zmienne niezb�dne do ChangeListenera
	    static int angleValue;
	    static double mass;
	   
	    double range;
	    double maxheight;
	    double flighttime;	   	 
	    	   
	    //menu
	    JMenuBar menuBar;  //Tworz� pasek, w kt�rym umieszczam 2 opcje: Menu oraz More
	    JMenu menu;
	    JMenu more;
	    JMenuItem itemExit; //Tworz� elementy, kt�re b�d� zawarte w opcjach Menu i More
	    JMenuItem itemSave;	   
	    JMenuItem itemLoad;
	    JMenuItem itemAuthors;

	    //panels
	    JFrame frame;			//Tworz� 2 panele
		AnimationPanel AnimationPanel;
		JPanel buttonsPanelLine_End, buttonsPanelPage_End;
	    
		public int intWidth = 1227; 
		public int intHeight = 500; 
		
		//bottom panel
	     JSlider sliderAngleValue;  //Tworz� 2 suwaki 
	     JSlider sliderSpeedValue;  	  	   
	     JTextField textAirResistance; //Tworz� pola niezb�dne pola tekstowe, guziki, etykiety oraz pole wyboru
	     JTextField textFlightTime;	
	     JTextField textMaxHeight;
	     JTextField textRange;	    	        
	     JButton buttonRandom;
	     JButton buttonStart;
	     JButton buttonStop;
	     JButton buttonRestart;
	     JLabel labelAngleValue;
	     JLabel labelSpeedValue;
	    
	     JLabel labelMass;
	     JComboBox<String> comboboxMass;
	  
	     //Save file
	     private Scanner skaner;
	     private String file_speedValue, file_angleValue, file_mass, 
	    					file_textAirResistance, file_textFlightTime, 
	    						file_textMaxHeight, file_textRange;
		
	     private static String inFile;
	    
	     public ControlPanel() 
	     {
	    	
	    	setTitle("Archer");
			setSize(intWidth, intHeight);
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//setVisible(true);
			this.setLayout(new BorderLayout());
			
			//Menu
			menuBar = new JMenuBar();   
	        	menu = new JMenu("Menu");	 		        		
	        		itemSave = new JMenuItem("Zapisz dane");//dodaje opcje do Menu wybrane opcje
	        		itemSave.addActionListener(new ActionListener() 
	        		{
						@Override
	        			public void actionPerformed(ActionEvent e) 
	        			{
							JFileChooser chooser = new JFileChooser(); 
        					chooser.setDialogTitle("Wybierz plik");  				
        					int returnVal = chooser.showSaveDialog(null);
        					if(returnVal == JFileChooser.APPROVE_OPTION)
        					{
        						 File file = chooser.getSelectedFile();
        						 JOptionPane.showMessageDialog(null, "Plik zapisany pomy�lnie jako " + file);
        						 try 
        						 {
       								
        							file_speedValue = labelSpeedValue.getText().trim();
        							file_angleValue = labelAngleValue.getText().trim();
        							file_mass = labelMass.getText().trim();
        							file_textAirResistance = textAirResistance.getText().trim(); //zwraca jako string 
        							file_textFlightTime = textFlightTime.getText().trim();
        							file_textMaxHeight = textMaxHeight.getText().trim();
        							file_textRange = textRange.getText().trim(); 
        							
        							inFile = file_angleValue + "; " + file_speedValue + "; " + file_mass + "; "
        										+ "Op�r powietrza: " + file_textAirResistance + "; "
        										+ "Czas lotu strza�y: " + file_textFlightTime + "; "
        										+ "Maksymalna wysoko��: " + file_textMaxHeight + "; "
        										+ "Zasi�g strza�y: " + file_textRange + "; ";	
        							      			//cos tu jszcze b�dzie		 
        							PrintWriter pw = new PrintWriter(file);
									skaner = new Scanner(inFile);
									while (skaner.hasNext())
									{
										pw.println(skaner.nextLine());
										pw.close();
									}
        						 } 
        						 catch (FileNotFoundException e1) 
        						 {							
        							 e1.printStackTrace();
        						 }
        					}
	        			}
	        		});
	        		itemLoad = new JMenuItem("Wczytaj dane"); 	// niestety tekst wyswietla si� tylko w jednym polu
	        		itemLoad.addActionListener(new ActionListener() 
	        		{
	        			@Override
	        			public void actionPerformed(ActionEvent e) 
	        			{
	        				JFileChooser chooser = new JFileChooser(); 
        					chooser.setDialogTitle("Wybierz plik");  				
        					int returnVal = chooser.showSaveDialog(null);
        					if(returnVal == JFileChooser.APPROVE_OPTION)
        					{
        						 File file = chooser.getSelectedFile();
        						 try 
        						 {
        							 BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        							 textAirResistance.read(input, "READING FILE :-)");
        							 
        						 } 
        						 catch (Exception e1) 
        						 {
          							e1.printStackTrace();
        						 }
      						}       							
	        			}
	        		});
	        		itemExit = new JMenuItem("Wyj�cie");
					itemExit.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent arg0) 
						{
							System.exit(0);
						}	
					});	        	
	        	menu.add(itemSave);	//dodaje elementy do Menu   
	        	menu.add(itemLoad);
				menu.addSeparator(); //ta kreska miedzy nimi
	        	menu.add(itemExit);
	      
	        	more = new JMenu("More");
					itemAuthors = new JMenuItem("Tw�rcy"); 	//wy�wietlenie informacji na temat autor�w programu
					itemAuthors.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent arg0) 
						{
							JOptionPane.showMessageDialog(itemAuthors, "Program zosta� napisany przez \n "
																			+ "Aleksandr� Trel� oraz Bartosza Kucharskiego.");
						
						}	
					});
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
	   	        
			labelAngleValue = new JLabel("K�t nachylenia �uku do ziemi: 1 �"); //Dodaje etykiet� nad suwakiem 1	   
			buttonsPanelLine_End.add(labelAngleValue); 	
  	
	    	sliderAngleValue = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN1, SLIDER_MAX1, SLIDER_INIT1);	    
	    	sliderAngleValue.setPreferredSize(new Dimension(200, 50));  //rozmiar suwaka
	    	sliderAngleValue.setMajorTickSpacing(30);  //warto�ci na podzia�ce co 30
	    	sliderAngleValue.setMinorTickSpacing(5);  //ka�dy kolejny punkt na podzia�ce wi�kszy o 5
	    //	sliderAngleValue.setPaintTicks(true);
	    	sliderAngleValue.setPaintLabels(true);
	    	sliderAngleValue.addChangeListener(new SliderChangeListener());	//dodaje ChangeListener
	    	buttonsPanelLine_End.add(sliderAngleValue);
	     	
	    	labelSpeedValue = new JLabel("Pr�dko�� strza�y: 1 m/s"); //Dodaje etykiet� nad suwakiem 2	
	    	buttonsPanelLine_End.add(labelSpeedValue);
	    	
	    	sliderSpeedValue = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN2, SLIDER_MAX2, SLIDER_INIT2);
	    	sliderSpeedValue.setPreferredSize(new Dimension(200, 50)); 
	    	sliderSpeedValue.setMajorTickSpacing(10);  //warto�ci na podzia�ce co 10
	    	sliderSpeedValue.setMinorTickSpacing(2);  //ka�dy kolejny punkt na podzia�ce wi�kszy o 2
	  //  	sliderSpeedValue.setPaintTicks(true);
	    	sliderSpeedValue.setPaintLabels(true);
	    	sliderSpeedValue.addChangeListener(new SliderChangeListener());	//dodaje ChangeListener        	         
	    	buttonsPanelLine_End.add(sliderSpeedValue);
			    	
	    	labelMass = new JLabel("Masa wybranej strza�y: ");  //etykieta z wyswietlana mas�
	    	buttonsPanelLine_End.add(labelMass); 
	    	
	    	comboboxMass = new JComboBox<String>(); //dodaje pole wyboru oraz tworz� mu opcje
	    	comboboxMass.addItem("stalowa");
	    	comboboxMass.addItem("aluminiowa");
	    	comboboxMass.addItem("tytanowa");
	    	comboboxMass.addActionListener(new ActionListener() 
	    	{
	    		@Override
	    		public void actionPerformed(ActionEvent e) 
	    		{		       			
	    			if (comboboxMass.getSelectedItem().equals("stalowa"))	      			
	    			{	        				
	    				mass = mass1;
	    				labelMass.setText("Masa wybranej strza�y: " + mass1 + " kg");	//7 kg
	    			}
	    			else if (comboboxMass.getSelectedItem().equals("aluminiowa"))	      			
	    			{
	    				mass = mass2;
	    				labelMass.setText("Masa wybranej strza�y: " + mass2 + " kg");	//2.4 kg
	    			}
	    			else if (comboboxMass.getSelectedItem().equals("tytanowa"))	      			
	    			{
	    				mass = mass3;
	    				labelMass.setText("Masa wybranej strza�y: " + mass3 + " kg");	//13 kg
	    			}
	    		}
	    	});
	    	buttonsPanelLine_End.add(comboboxMass);
	    	    	
	    	textAirResistance = new JTextField("");  //pole tekstowe, w kt�rym wy�wietla� si� b�dzie op�r powietrza
	    	textAirResistance.setEditable(false);
	    	buttonsPanelLine_End.add(textAirResistance);
	    	
	   		buttonRandom = new JButton("Losuj op�r powietrza");  //przycisk Losuj, kt�ry generuje liczb� z zakresu 1-100	
	    	buttonRandom.addActionListener(new ActionListener() 
	    	{
	    		@Override
	    		public void actionPerformed(ActionEvent e) 
	    		{	        			
	    			Random rand = new Random();
	    		    resistance = rand.nextInt((99-1)+1)+1;	       //losuje od 1 do 99 			      			
	    			textAirResistance.setText(String.valueOf(resistance)); //wyswietli wylosowan� warto�� oporu
	    		}	        				        		
	    	});
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
			buttonStart.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{					
					flighttime = (2 *  speedValue * Math.sin(Math.toRadians(angleValue)) ) / g; //wzory /
					textFlightTime.setText(String.valueOf(String.format("%.02f", flighttime) + " [s]")); //wyswietli  warto�� oporu
					        			       				        				
					maxheight = Math.pow(speedValue * Math.sin(Math.toRadians(angleValue)), 2) / (2 * g);
					textMaxHeight.setText(String.valueOf(String.format("%.02f", maxheight) + " [m]")); 
					        			
					range = ( Math.pow(speedValue, 2) * Math.sin( 2* Math.toRadians(angleValue)) ) / g;
					textRange.setText(String.valueOf(String.format("%.02f", range) + " [m]")); 			
					
					pl.edu.pw.fizyka.pojava.Trela_Kucharski.AnimationPanel.gamelooptimer.start();							
				}
			});
			buttonsPanelPage_End.add(buttonStart); 
			
			buttonStop = new JButton("Stop");  //przycisk, kt�ry pozwoli uruchomi� i wstrzyma� gr�	
			buttonStop.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{					
					pl.edu.pw.fizyka.pojava.Trela_Kucharski.AnimationPanel.gamelooptimer.stop();			
				}
			});
			buttonsPanelPage_End.add(buttonStop); 
			
			buttonRestart = new JButton("Reset");  //przycisk wyzerowania wszystkich komponent�w oraz resetuj�cy po�o�enie strza�y		
			buttonRestart.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{					
				AnimationPanel.Arrow.reset(25,357, Vx(speedValue, angleValue),Vy(speedValue, angleValue));					
				AnimationPanel.repaint();
				pl.edu.pw.fizyka.pojava.Trela_Kucharski.AnimationPanel.gamelooptimer.stop();
				
				textAirResistance.setText(""); 
			    textFlightTime.setText("Czas lotu strza�y");
			    textMaxHeight.setText("Max. wysoko��");
			    textRange.setText("Zasi�g strza�y");
			    labelSpeedValue.setText("Pr�dko�� strza�y: 1 m/s");
			    labelAngleValue.setText("K�t nachylenia �uku do ziemi: 1 �");
			    labelMass.setText("Masa wybranej strza�y: ");  
				sliderAngleValue.setValue(1);
				sliderSpeedValue.setValue(1);
				
				}
			});
			buttonsPanelPage_End.add(buttonRestart); 
			this.add(buttonsPanelPage_End, BorderLayout.PAGE_END);	   
	    	    		       	      	   
	    }
	    	 

	public class SliderChangeListener implements ChangeListener  //klasa implementacyjna suwaka
    {
		@Override
		public void stateChanged(ChangeEvent e) 
		{
			speedValue = sliderSpeedValue.getValue();//?
			labelSpeedValue.setText("Pr�dko�� strza�y: " + speedValue + " m/s");	
							
			angleValue = sliderAngleValue.getValue();//?
			labelAngleValue.setText("K�t nachylenia �uku do ziemi: " + angleValue + " �");												
				
			AnimationPanel.Arrow.Vx = Vx(speedValue, angleValue);
			AnimationPanel.Arrow.Vy = Vy(speedValue, angleValue);
		}	
	}

	
	public double Vy(int alfa, int v)
	{
		return -(v*(Math.sin(Math.toRadians(alfa)))*1.5); //"zdefiniowanie Vx jako 	speedValue * sin(angleValue)"
	}															//dodanie 12 u�yte w celu uzyskania 
																//wiarygodniejszej jakosci animacji
	public double Vx( int v, int alfa)
	{
		return (v*(Math.cos(Math.toRadians(alfa)))*5); //mno�enie przez 8 u�yte w celu uzyskania 
														//wiarygodniejszej jakosci animacji
	}

	
}
