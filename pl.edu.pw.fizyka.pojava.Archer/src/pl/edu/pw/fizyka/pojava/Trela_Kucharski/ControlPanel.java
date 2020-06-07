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

		private static final int SLIDER_MIN1 = 1;  //ustawiam wartoœæ minimaln¹ suwaka 1 i 2
	    private static final int SLIDER_MAX1 = 90;  //ustawiam wartoœæ maksymaln¹ suwaka 1 i 2
	    private static final int SLIDER_INIT1 = 1;  //ustawiam wartoœæ pocz¹tkow¹ suwaka 1 i 2
	    private static final int SLIDER_MIN2 = 1;  
	    private static final int SLIDER_MAX2 = 30;  
	    private static final int SLIDER_INIT2 = 1;  
	   
	    static final double g = 9.80665; //stale przyspieszenie ziemiskie do obliczen
	   
	    
	    //variable values
	    static int resistance;	 
	    int mass1 = 7; 
	    int mass2 = 2;
	    int mass3 = 13;
	    static int speedValue; 	//zmienne niezbêdne do ChangeListenera
	    static int angleValue;
	    static double mass;
	   
	    double range;
	    double maxheight;
	    double flighttime;	   	 
	    	   
	    //menu
	    JMenuBar menuBar;  //Tworzê pasek, w którym umieszczam 2 opcje: Menu oraz More
	    JMenu menu;
	    JMenu more;
	    JMenuItem itemExit; //Tworzê elementy, które bêd¹ zawarte w opcjach Menu i More
	    JMenuItem itemSave;	   
	    JMenuItem itemLoad;
	    JMenuItem itemAuthors;

	    //panels
	    JFrame frame;			//Tworzê 2 panele
		AnimationPanel AnimationPanel;
		JPanel buttonsPanelLine_End, buttonsPanelPage_End;
	    
		public int intWidth = 1227; 
		public int intHeight = 500; 
		
		//bottom panel
	     JSlider sliderAngleValue;  //Tworzê 2 suwaki 
	     JSlider sliderSpeedValue;  	  	   
	     JTextField textAirResistance; //Tworzê pola niezbêdne pola tekstowe, guziki, etykiety oraz pole wyboru
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
        						 JOptionPane.showMessageDialog(null, "Plik zapisany pomyœlnie jako " + file);
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
        										+ "Opór powietrza: " + file_textAirResistance + "; "
        										+ "Czas lotu strza³y: " + file_textFlightTime + "; "
        										+ "Maksymalna wysokoœæ: " + file_textMaxHeight + "; "
        										+ "Zasiêg strza³y: " + file_textRange + "; ";	
        							      			//cos tu jszcze bêdzie		 
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
	        		itemLoad = new JMenuItem("Wczytaj dane"); 	// niestety tekst wyswietla siê tylko w jednym polu
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
	        		itemExit = new JMenuItem("Wyjœcie");
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
					itemAuthors = new JMenuItem("Twórcy"); 	//wyœwietlenie informacji na temat autorów programu
					itemAuthors.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent arg0) 
						{
							JOptionPane.showMessageDialog(itemAuthors, "Program zosta³ napisany przez \n "
																			+ "Aleksandrê Trelê oraz Bartosza Kucharskiego.");
						
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
	   	        
			labelAngleValue = new JLabel("K¹t nachylenia ³uku do ziemi: 1 °"); //Dodaje etykietê nad suwakiem 1	   
			buttonsPanelLine_End.add(labelAngleValue); 	
  	
	    	sliderAngleValue = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN1, SLIDER_MAX1, SLIDER_INIT1);	    
	    	sliderAngleValue.setPreferredSize(new Dimension(200, 50));  //rozmiar suwaka
	    	sliderAngleValue.setMajorTickSpacing(30);  //wartoœci na podzia³ce co 30
	    	sliderAngleValue.setMinorTickSpacing(5);  //ka¿dy kolejny punkt na podzia³ce wiêkszy o 5
	    //	sliderAngleValue.setPaintTicks(true);
	    	sliderAngleValue.setPaintLabels(true);
	    	sliderAngleValue.addChangeListener(new SliderChangeListener());	//dodaje ChangeListener
	    	buttonsPanelLine_End.add(sliderAngleValue);
	     	
	    	labelSpeedValue = new JLabel("Prêdkoœæ strza³y: 1 m/s"); //Dodaje etykietê nad suwakiem 2	
	    	buttonsPanelLine_End.add(labelSpeedValue);
	    	
	    	sliderSpeedValue = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN2, SLIDER_MAX2, SLIDER_INIT2);
	    	sliderSpeedValue.setPreferredSize(new Dimension(200, 50)); 
	    	sliderSpeedValue.setMajorTickSpacing(10);  //wartoœci na podzia³ce co 10
	    	sliderSpeedValue.setMinorTickSpacing(2);  //ka¿dy kolejny punkt na podzia³ce wiêkszy o 2
	  //  	sliderSpeedValue.setPaintTicks(true);
	    	sliderSpeedValue.setPaintLabels(true);
	    	sliderSpeedValue.addChangeListener(new SliderChangeListener());	//dodaje ChangeListener        	         
	    	buttonsPanelLine_End.add(sliderSpeedValue);
			    	
	    	labelMass = new JLabel("Masa wybranej strza³y: ");  //etykieta z wyswietlana mas¹
	    	buttonsPanelLine_End.add(labelMass); 
	    	
	    	comboboxMass = new JComboBox<String>(); //dodaje pole wyboru oraz tworzê mu opcje
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
	    				labelMass.setText("Masa wybranej strza³y: " + mass1 + " kg");	//7 kg
	    			}
	    			else if (comboboxMass.getSelectedItem().equals("aluminiowa"))	      			
	    			{
	    				mass = mass2;
	    				labelMass.setText("Masa wybranej strza³y: " + mass2 + " kg");	//2.4 kg
	    			}
	    			else if (comboboxMass.getSelectedItem().equals("tytanowa"))	      			
	    			{
	    				mass = mass3;
	    				labelMass.setText("Masa wybranej strza³y: " + mass3 + " kg");	//13 kg
	    			}
	    		}
	    	});
	    	buttonsPanelLine_End.add(comboboxMass);
	    	    	
	    	textAirResistance = new JTextField("");  //pole tekstowe, w którym wyœwietlaæ siê bêdzie opór powietrza
	    	textAirResistance.setEditable(false);
	    	buttonsPanelLine_End.add(textAirResistance);
	    	
	   		buttonRandom = new JButton("Losuj opór powietrza");  //przycisk Losuj, który generuje liczbê z zakresu 1-100	
	    	buttonRandom.addActionListener(new ActionListener() 
	    	{
	    		@Override
	    		public void actionPerformed(ActionEvent e) 
	    		{	        			
	    			Random rand = new Random();
	    		    resistance = rand.nextInt((99-1)+1)+1;	       //losuje od 1 do 99 			      			
	    			textAirResistance.setText(String.valueOf(resistance)); //wyswietli wylosowan¹ wartoœæ oporu
	    		}	        				        		
	    	});
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
			buttonStart.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{					
					flighttime = (2 *  speedValue * Math.sin(Math.toRadians(angleValue)) ) / g; //wzory /
					textFlightTime.setText(String.valueOf(String.format("%.02f", flighttime) + " [s]")); //wyswietli  wartoœæ oporu
					        			       				        				
					maxheight = Math.pow(speedValue * Math.sin(Math.toRadians(angleValue)), 2) / (2 * g);
					textMaxHeight.setText(String.valueOf(String.format("%.02f", maxheight) + " [m]")); 
					        			
					range = ( Math.pow(speedValue, 2) * Math.sin( 2* Math.toRadians(angleValue)) ) / g;
					textRange.setText(String.valueOf(String.format("%.02f", range) + " [m]")); 			
					
					pl.edu.pw.fizyka.pojava.Trela_Kucharski.AnimationPanel.gamelooptimer.start();							
				}
			});
			buttonsPanelPage_End.add(buttonStart); 
			
			buttonStop = new JButton("Stop");  //przycisk, który pozwoli uruchomiæ i wstrzymaæ grê	
			buttonStop.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{					
					pl.edu.pw.fizyka.pojava.Trela_Kucharski.AnimationPanel.gamelooptimer.stop();			
				}
			});
			buttonsPanelPage_End.add(buttonStop); 
			
			buttonRestart = new JButton("Reset");  //przycisk wyzerowania wszystkich komponentów oraz resetuj¹cy po³o¿enie strza³y		
			buttonRestart.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{					
				AnimationPanel.Arrow.reset(25,357, Vx(speedValue, angleValue),Vy(speedValue, angleValue));					
				AnimationPanel.repaint();
				pl.edu.pw.fizyka.pojava.Trela_Kucharski.AnimationPanel.gamelooptimer.stop();
				
				textAirResistance.setText(""); 
			    textFlightTime.setText("Czas lotu strza³y");
			    textMaxHeight.setText("Max. wysokoœæ");
			    textRange.setText("Zasiêg strza³y");
			    labelSpeedValue.setText("Prêdkoœæ strza³y: 1 m/s");
			    labelAngleValue.setText("K¹t nachylenia ³uku do ziemi: 1 °");
			    labelMass.setText("Masa wybranej strza³y: ");  
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
			labelSpeedValue.setText("Prêdkoœæ strza³y: " + speedValue + " m/s");	
							
			angleValue = sliderAngleValue.getValue();//?
			labelAngleValue.setText("K¹t nachylenia ³uku do ziemi: " + angleValue + " °");												
				
			AnimationPanel.Arrow.Vx = Vx(speedValue, angleValue);
			AnimationPanel.Arrow.Vy = Vy(speedValue, angleValue);
		}	
	}

	
	public double Vy(int alfa, int v)
	{
		return -(v*(Math.sin(Math.toRadians(alfa)))*1.5); //"zdefiniowanie Vx jako 	speedValue * sin(angleValue)"
	}															//dodanie 12 u¿yte w celu uzyskania 
																//wiarygodniejszej jakosci animacji
	public double Vx( int v, int alfa)
	{
		return (v*(Math.cos(Math.toRadians(alfa)))*5); //mno¿enie przez 8 u¿yte w celu uzyskania 
														//wiarygodniejszej jakosci animacji
	}

	
}
