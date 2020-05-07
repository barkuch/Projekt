package  pl.edu.pw.fizyka.pojava.Trela_Kucharski;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class controlPanel extends JFrame 
{
	    private static final int SLIDER_MIN1 = 0;  //ustawiam wartoœæ minimaln¹ suwaka 1 i 2
	    private static final int SLIDER_MAX1 = 90;  //ustawiam wartoœæ maksymaln¹ suwaka 1 i 2
	    private static final int SLIDER_INIT1 = 0;  //ustawiam wartoœæ pocz¹tkow¹ suwaka 1 i 2
	    private static final int SLIDER_MIN2 = 0;  
	    private static final int SLIDER_MAX2 = 30;  
	    private static final int SLIDER_INIT2 = 0;  

	  //  private static final int radius = 100;
	   
	    static final double g = 9.80665; //stale przyspieszenie ziemiskie do obliczen
	  	    
	    //variable values
	    private double resistance;	 
	    private int mass1 = 7; 
	    private double mass2 = 2.4;
	    private int mass3 = 13;
	    private int speedValue; 	//zmienne niezbêdne do ChangeListenera
	    private int angleValue;
	    private double mass;
	   
	    private double range;
	    private double maxheight;
	    private double flighttime;	   	 
	    	   
/*	    //menu
	    private JMenuBar menuBar;  //Tworzê pasek, w którym umieszczam 2 opcje: Menu oraz More
	    private JMenu menu;
	    private JMenu more;
	    private JMenuItem itemExit; //Tworzê elementy, które bêd¹ zawarte w opcjach Menu i More
	    private JMenuItem itemSave;
	   
	    private JMenuItem itemLoad;
	    private JMenuItem itemAuthors;
*/
	    //panels
	    JFrame frame;		//Tworzê 2 panele
		animationPanel animationPanel;
		JPanel buttonsPanel;
	    
		int intWidth = 1200; 
		int intHeight = 620; 
		
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
	     JLabel labelAirResistance;
	     JLabel labelAngleValue;
	     JLabel labelSpeedValue;
	     JLabel labelFlightTime;
	     JLabel labelMaxHeight;
	     JLabel labelRange;
	     JLabel labelMass;
	     JComboBox<String> comboboxMass;
	  
/*	    //Save file
	    private Scanner skaner;
	    private String file_speedValue, file_angleValue, file_mass, file_textAirResistance, file_textFlightTime, file_textMaxHeight, file_textRange;
		private static String inFile;
*/	    
	    public controlPanel() 
	    {
	    	frame = new JFrame("Archer");
			frame.setSize(intWidth, intHeight);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//frame.setResizable(false);
			frame.setVisible(true);
			
/*	      //Menu
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
        							//file_textAirResistance = ("");
        							//file_textRange = ("");
        								
        							file_speedValue = labelSpeedValue.getText().trim();
        							file_angleValue = labelAngleValue.getText().trim();
        							file_mass = labelMass.getText().trim();
        							file_textAirResistance = textAirResistance.getText().trim(); //zwraca jako string ??
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
	        		itemLoad = new JMenuItem("Wczytaj dane");
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
					itemAuthors = new JMenuItem("Twórcy"); //wyœwietlenie informacji na temat autorów programu
					itemAuthors.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent arg0) 
						{
							JOptionPane.showMessageDialog(itemAuthors, "Program zosta³ napisany przez \n Aleksandrê Trelê oraz Bartosza Kucharskiego.");
						}	
					});
				more.add(itemAuthors);	//dodaje elementy do More
	        menuBar.add(menu); //dodaje opcje do paska
	        menuBar.add(more);
	        this.setJMenuBar(menuBar);
*/	        
	        
			//PANEL ANIMACJI
			animationPanel = new animationPanel();
			animationPanel.setLayout(null);
			animationPanel.setBounds(0, 0, intWidth, intHeight-215);
			
			//PANEL STEROWANIA
			buttonsPanel = new JPanel();
			buttonsPanel.setLayout(null);
			buttonsPanel.setSize(intWidth, intHeight);
	 
			frame.add(animationPanel);
			frame.add(buttonsPanel);
	        
	        
	        //STEROWANIE
			/*
			JMenuBar menuBar = new JMenuBar();
			JMenu menu = new JMenu("Menu");
			menuBar.add(menu);
			frame.setJMenuBar(menuBar)
			*/
	        
			labelAngleValue = new JLabel("K¹t nachylenia ³uku do ziemi: 0 °"); //Dodaje etykietê nad suwakiem 1
	    	labelAngleValue.setBounds(10, 400, 200, 50); //ustawiam po³o¿enie etykiety
	    	buttonsPanel.add(labelAngleValue); 	
			
	    	sliderAngleValue = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN1, SLIDER_MAX1, SLIDER_INIT1);
	    	sliderAngleValue.setBounds(10, 435, 200, 50); //ustawiam po³o¿enie suwaka
	    	sliderAngleValue.setPreferredSize(new Dimension(200, 50));  //rozmiar suwaka
	    	sliderAngleValue.setMajorTickSpacing(30);  //wartoœci na podzia³ce co 30
	    	sliderAngleValue.setMinorTickSpacing(5);  //ka¿dy kolejny punkt na podzia³ce wiêkszy o 5
	    	sliderAngleValue.setPaintTicks(true);
	    	sliderAngleValue.setPaintLabels(true);
	    	sliderAngleValue.addChangeListener(new SliderChangeListener());	//dodaje ChangeListener
	    	buttonsPanel.add(sliderAngleValue);
	  	
	    	labelSpeedValue = new JLabel("Prêdkoœæ pocz¹tkowa strza³y: 0 m/s"); //Dodaje etykietê nad suwakiem 2
	    	labelSpeedValue.setBounds(10, 475, 250, 50); 
	    	buttonsPanel.add(labelSpeedValue);
	    	
	    	sliderSpeedValue = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN2, SLIDER_MAX2, SLIDER_INIT2);
	    	sliderSpeedValue.setBounds(10, 510, 200, 50);
	    	sliderSpeedValue.setPreferredSize(new Dimension(200, 50)); 
	    	sliderSpeedValue.setMajorTickSpacing(10);  //wartoœci na podzia³ce co 10
	    	sliderSpeedValue.setMinorTickSpacing(2);  //ka¿dy kolejny punkt na podzia³ce wiêkszy o 2
	    	sliderSpeedValue.setPaintTicks(true);
	    	sliderSpeedValue.setPaintLabels(true);
	    	sliderSpeedValue.addChangeListener(new SliderChangeListener());	//dodaje ChangeListener        	         
	    	buttonsPanel.add(sliderSpeedValue);
	    	        		     		
	    	
	    	labelMass = new JLabel("Masa wybranej strza³y: ");  //etykieta z wyswietlana mas¹
	    	labelMass.setBounds(230, 400, 200, 50);
	    	buttonsPanel.add(labelMass);
	    	  
	    	comboboxMass = new JComboBox<String>(); //dodaje pole wyboru oraz tworzê mu opcje
	    	comboboxMass.setBounds(230, 435, 165, 20); //ustawiam po³o¿enie pola wyboru
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
	    	buttonsPanel.add(comboboxMass);
	 
	    	labelAirResistance = new JLabel("Opór powietrza");	       
	    	labelAirResistance.setBounds(420, 400, 100, 50);	
	    	buttonsPanel.add(labelAirResistance); 	 
	    	textAirResistance = new JTextField("");  //pole tekstowe, w którym wyœwietlaæ siê bêdzie opór powietrza
	    	textAirResistance.setBounds(420, 435, 100, 30);
	    	buttonsPanel.add(textAirResistance);
	   		buttonRandom = new JButton("Losuj");  //przycisk Losuj, który generuje liczbê z zakresu 1-100
	   		buttonRandom.setBounds(420, 465, 100, 30);	
	    	buttonRandom.addActionListener(new ActionListener() 
	    	{
	    		@Override
	    		public void actionPerformed(ActionEvent e) 
	    		{	        			
	    			Random rand = new Random();
	    		    resistance = rand.nextInt(100)+1;	        			      			
	    			textAirResistance.setText(String.valueOf(resistance)); //wyswietli wylosowan¹ wartoœæ oporu
	    		}	        				        		
	    	});
	    	buttonsPanel.add(buttonRandom);
	    
	    	labelFlightTime = new JLabel("Czas lotu strza³y");	        
	    	labelFlightTime.setBounds(550, 400, 200, 50);	
	    	buttonsPanel.add(labelFlightTime); 	        
	    	textFlightTime = new JTextField(); //pole tekstowe, w którym wyœwietlaæ siê bêdzie Czas lotu strza³y (korzystamy ze wzorów ze specyfikacji)
	    	textFlightTime.setBounds(550, 435, 150, 30);	
	    	buttonsPanel.add(textFlightTime);
	   
	    	labelMaxHeight = new JLabel("Maksymalna wysokoœæ");	
	    	labelMaxHeight.setBounds(720, 400, 200, 50);
	    	buttonsPanel.add(labelMaxHeight); 	       
	    	textMaxHeight = new JTextField(); //pole tekstowe, w którym wyœwietlaæ siê bêdzie Maksymalna wysokoœæ (korzystamy ze wzorów ze specyfikacji)
	    	textMaxHeight.setBounds(720, 435, 150, 30);
	    	buttonsPanel.add(textMaxHeight);
	    
	 		labelRange = new JLabel("Zasiêg strza³y");
	 		labelRange.setBounds(890, 400, 200, 50);
	 		buttonsPanel.add(labelRange); 
	    	textRange = new JTextField();  //pole tekstowe, w którym wyœwietlaæ siê bêdzie Zasiêg strza³y (korzystamy ze wzorów ze specyfikacji)
	    	textRange.setBounds(890, 435, 150, 30);
	    	buttonsPanel.add(textRange);
	    	      
	    	buttonStart = new JButton("Start");  //przycisk, który pozwoli uruchomiæ i wstrzymaæ grê
	    	buttonStart.setBounds(1060, 420, 115, 40);		
			buttonStart.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) //Math.sin(Math.toRadians(cos)) - wzor zeby z tego co mamy zrobic to co chcemy xd
				{					
					flighttime = (2 *  speedValue * Math.sin(Math.toRadians(angleValue)) ) / g; //wzory
					textFlightTime.setText(String.valueOf(String.format("%.02f", flighttime) + " [s]")); //wyswietli  wartoœæ oporu
					        			       				        				
					maxheight = Math.pow(speedValue * Math.sin(Math.toRadians(angleValue)), 2) / 2 * g;
					textMaxHeight.setText(String.valueOf(String.format("%.02f", maxheight) + " [m]")); 
					        			
					range = ( Math.pow(speedValue, 2) * Math.sin( 2* Math.toRadians(angleValue)) ) / g;
					textRange.setText(String.valueOf(String.format("%.02f", range) + " [m]")); 			
					
					pl.edu.pw.fizyka.pojava.Trela_Kucharski.animationPanel.gamelooptimer.start();			
				
				}
			});
			buttonsPanel.add(buttonStart); 
			
			buttonStop = new JButton("Stop");  //przycisk, który pozwoli uruchomiæ i wstrzymaæ grê
	    	buttonStop.setBounds(1060, 465, 115, 40);		
			buttonStop.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{					
					pl.edu.pw.fizyka.pojava.Trela_Kucharski.animationPanel.gamelooptimer.stop();			
				}
			});
			buttonsPanel.add(buttonStop); 
			
			buttonRestart = new JButton("Reset");  //przycisk, który pozwoli uruchomiæ i wstrzymaæ grê
	    	buttonRestart.setBounds(1060, 510, 115, 40);		
			buttonRestart.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{					
				//	animationPanel.object.Reset(25,360);			

				}
			});
			buttonsPanel.add(buttonRestart); 	       	      	   
	    }
	    
	    
	    
	private class SliderChangeListener implements ChangeListener  //klasa implementacyjna suwaka
    {
		@Override
		public void stateChanged(ChangeEvent e) 
		{
			speedValue = sliderSpeedValue.getValue();//?
			labelSpeedValue.setText("Prêdkoœæ pocz¹tkowa strza³y: " + speedValue + " m/s");	
							
			angleValue = sliderAngleValue.getValue();//?
			labelAngleValue.setText("K¹t nachylenia ³uku do ziemi: " + angleValue + " °");												
		}	
	} 
	
		
	
}
