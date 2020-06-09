package pl.edu.pw.fizyka.pojava.Trela_Kucharski;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Actions implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String s = e.getActionCommand();
		{
			if(s == "Zapisz dane")
			{
				JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Zapisz plik");
                int result = chooser.showDialog(null, "Zapisz");
                if (result == JFileChooser.APPROVE_OPTION)
                { 
                	File outputFile = new File(chooser.getSelectedFile().getAbsolutePath());
                	JOptionPane.showMessageDialog(null, "Plik zapisany pomyœlnie jako " + outputFile);
                	try
                	{            
                     OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outputFile));
                     osw.write("K¹t nachylenia ³uku do ziemi: " + ControlPanel.angleValue + " °" + "\n");
                     osw.write("Prêdkoœæ strza³y: " + ControlPanel.speedValue + " m/s" + "\n");             
                     osw.write("Masa wybranej strza³y: " + ControlPanel.gravAcceleration + " kg" + "\n");
                     osw.write("Opór powietrza: " + ControlPanel.resistance + "\n");
                     
                     osw.write("Czas lotu strza³y: " + ControlPanel.textFlightTime.getText()+ "\n");
                     osw.write("Maksymalna wysokoœæ: " + ControlPanel.textMaxHeight.getText()+ "\n");
                     osw.write("Zasiêg strza³y: " + ControlPanel.textRange.getText()+ "\n");
                                               
                     osw.close();                                         
                	}
                	catch (FileNotFoundException ex)
                	{
                		ex.printStackTrace();
                	}
                	catch (IOException ep)
                	{
                		System.out.println(ep.getMessage());
                	}
                }
			}
			else if(s == "Wczytaj dane")
			{
				
				JFileChooser chooser = new JFileChooser();
	            chooser.setDialogTitle("Wybierz plik");
	            int result = chooser.showDialog(null, "Wczytaj");
	            if (result == JFileChooser.APPROVE_OPTION)
	            {
	                try 
	                {  							
        			FileInputStream fr = new FileInputStream(chooser.getSelectedFile().getAbsolutePath());
        			InputStreamReader isr = new InputStreamReader(fr);
        			BufferedReader reader = new BufferedReader(isr);
        			StringBuffer buffer = new StringBuffer();

        			String line = null;
        			while ((line = reader.readLine()) != null) 
        			{
        				buffer.append(line);
        				System.out.println(line);
        				ControlPanel.sliderSpeedValue.setValue(ControlPanel.speedValue);
        				ControlPanel.sliderAngleValue.setValue(ControlPanel.angleValue);
        				
        			}
        			reader.close();
       								
        			ControlPanel.AnimationPanel.Arrow.load(23,476);					
        			ControlPanel.AnimationPanel.repaint();
    			
        			} 
        			catch (Exception e1) 
        			{
        				e1.printStackTrace();
        			}
	            }
      						
			}
			else if(s == "Wyjœcie")
			{
				System.exit(0);
			}
			else if(s == "Twórcy")
			{
				JOptionPane.showMessageDialog(ControlPanel.itemAuthors, "Program zosta³ napisany przez \n "
						+ "Aleksandrê Trelê oraz Bartosza Kucharskiego.");
			}
			else if(s == "cGravAcceleration")
			{
				if(ControlPanel.comboboxGravAcceleration.getSelectedItem().equals(" zerowe "))				      			
	    			{	        				
						ControlPanel.gravAcceleration = 0;
						ControlPanel.labelGravAcceleration.setText("Przyspieszenie grawitacjne: " + "0" + " m/s^2");						
	    			}
					else if  (ControlPanel.comboboxGravAcceleration.getSelectedItem().equals("na Ziemi"))	     			
					{
						ControlPanel.gravAcceleration = ControlPanel.gravAcceleration1;
						ControlPanel.labelGravAcceleration.setText("Przyspieszenie grawitacjne: " + ControlPanel.gravAcceleration1 + " m/s^2");	
					}	
					else if (ControlPanel.comboboxGravAcceleration.getSelectedItem().equals("na Marsie"))	      			
	    			{
	    				ControlPanel.gravAcceleration = ControlPanel.gravAcceleration2;
	    				ControlPanel.labelGravAcceleration.setText("Przyspieszenie grawitacjne: " + ControlPanel.gravAcceleration2 + " m/s^2");	
	    			}
	    			else if (ControlPanel.comboboxGravAcceleration.getSelectedItem().equals("na Jowiszu"))	      			
	    			{
	    				ControlPanel.gravAcceleration = ControlPanel.gravAcceleration3;
	    				ControlPanel.labelGravAcceleration.setText("Przyspieszenie grawitacjne: " + ControlPanel.gravAcceleration3 + " m/s^2");	
	    			}
	    			
	    			
			}
			else if(s == "Losuj opór powietrza")
			{
				Random rand = new Random();
	    		ControlPanel.resistance = rand.nextInt(19)+1;	      		      			
	    		ControlPanel.textAirResistance.setText(String.valueOf(ControlPanel.resistance));
			}
			else if(s == "Start")
			{
				ControlPanel.flighttime = (2 *  ControlPanel.speedValue * Math.sin(Math.toRadians(ControlPanel.angleValue)) ) / ControlPanel.gravAcceleration; //wzory /
				ControlPanel.textFlightTime.setText(String.valueOf(String.format("%.02f", ControlPanel.flighttime) + " [s]")); //wyswietli  wartoœæ oporu
					        			       				        				
				ControlPanel.maxheight = Math.pow(ControlPanel.speedValue * Math.sin(Math.toRadians(ControlPanel.angleValue)), 2) / (2 * ControlPanel.gravAcceleration);
				ControlPanel.textMaxHeight.setText(String.valueOf(String.format("%.02f", ControlPanel.maxheight) + " [m]")); 
					        			
				ControlPanel.range = ( Math.pow(ControlPanel.speedValue, 2) * Math.sin( 2* Math.toRadians(ControlPanel.angleValue)) ) / ControlPanel.gravAcceleration;
				ControlPanel.textRange.setText(String.valueOf(String.format("%.02f", ControlPanel.range) + " [m]")); 			
					
				pl.edu.pw.fizyka.pojava.Trela_Kucharski.AnimationPanel.gamelooptimer.start();
						
			}
			else if(s == "Stop")
			{
				pl.edu.pw.fizyka.pojava.Trela_Kucharski.AnimationPanel.gamelooptimer.stop();			
			}
			else if(s == "Reset")
			{
					ControlPanel.AnimationPanel.Arrow.reset(23,476, Vx(ControlPanel.speedValue, ControlPanel.angleValue),Vy(ControlPanel.speedValue, ControlPanel.angleValue));					
	        		ControlPanel.AnimationPanel.repaint();
	        		pl.edu.pw.fizyka.pojava.Trela_Kucharski.AnimationPanel.gamelooptimer.stop();
				
	        		ControlPanel.textAirResistance.setText(""); 
	        		ControlPanel.textFlightTime.setText(" Czas lotu strza³y");
	        		ControlPanel.textMaxHeight.setText(" Max. wysokoœæ");
	        		ControlPanel.textRange.setText(" Zasiêg strza³y");
	        		ControlPanel.labelSpeedValue.setText(" Prêdkoœæ strza³y: 0 m/s");
	        		ControlPanel.labelAngleValue.setText(" K¹t nachylenia ³uku do ziemi: 0 °");
	        		ControlPanel.labelGravAcceleration.setText("Przyspieszenie grawitacjne: ");  
	        		ControlPanel.sliderAngleValue.setValue(0);
	        		ControlPanel.angleValue = 0;
	        		ControlPanel.sliderSpeedValue.setValue(0);
	        		ControlPanel.speedValue = 0;	        		
	        		ControlPanel.comboboxGravAcceleration.getSelectedItem().equals(" zerowe ");
			}
					
		}
	}
	public static double Vy(int alfa, int v)
	{
		return -(v*(Math.sin(Math.toRadians(alfa)))+7);  //7 u¿yta w celu uwiarygodnienia animacji
	}
	public static double Vx( int v, int alfa)
	{
		return (v*(Math.cos(Math.toRadians(alfa)))*2.3); //2.3 u¿yte w celu uwiarygodnienia animacji

	}
}
