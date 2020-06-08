package pl.edu.pw.fizyka.pojava.Trela_Kucharski;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
				ControlPanel.file_speedValue = ControlPanel.labelSpeedValue.getText();
				ControlPanel.file_angleValue = ControlPanel.labelAngleValue.getText();
				ControlPanel.file_mass = ControlPanel.labelMass.getText();
				ControlPanel.file_textAirResistance = ControlPanel.textAirResistance.getText(); //zwraca jako string 
				ControlPanel.file_textFlightTime = ControlPanel.textFlightTime.getText();
				ControlPanel.file_textMaxHeight = ControlPanel.textMaxHeight.getText();
				ControlPanel.file_textRange = ControlPanel.textRange.getText(); 
					
				ControlPanel.inFile = ControlPanel.file_angleValue + "\n" + ControlPanel.file_speedValue + "\n" + ControlPanel.file_mass + "\n"
								+ "Opór powietrza: " + ControlPanel.file_textAirResistance + "\n"
								+ "Czas lotu strza³y: " + ControlPanel.file_textFlightTime + "\n"
								+ "Maksymalna wysokoœæ: " + ControlPanel.file_textMaxHeight + "\n"
								+ "Zasiêg strza³y: " + ControlPanel.file_textRange + "\n" + "----------------------------------" + "\n";	
					String Data = ControlPanel.inFile;
					try 
					{
						BufferedWriter reader = new BufferedWriter(new FileWriter(new File("C:\\Users\\barti\\git\\Arher\\pl.edu.pw.fizyka.pojava.Archer\\src\\dane\\dane2.txt"), true));
						reader.write(Data);
						reader.newLine();
						reader.close();
						
						JOptionPane.showMessageDialog(ControlPanel.itemSave, "Dane dodane do pliku");
						
					}
					catch (IOException e1)
					{
						System.out.println("Error is " + e1);
					}
			}
			else if(s == "Wczytaj dane")
			{
				
				File file = new File("C:\\Users\\barti\\git\\Arher\\pl.edu.pw.fizyka.pojava.Archer\\src\\dane\\dane2.txt");
        		try 
        		{  							
        			FileInputStream fr = new FileInputStream(file);
        			InputStreamReader isr = new InputStreamReader(fr);
        			BufferedReader reader = new BufferedReader(isr);
        			StringBuffer buffer = new StringBuffer();

        			String line = null;
        			while ((line = reader.readLine()) != null) 
        			{
        				buffer.append(line);
        			}
        			reader.close();
       				ControlPanel.labelSpeedValue.setText(ControlPanel.file_speedValue);        					
       				ControlPanel.labelAngleValue.setText(ControlPanel.file_angleValue);
      				ControlPanel.labelMass.setText(ControlPanel.file_mass);
   					ControlPanel.textAirResistance.setText(ControlPanel.file_textAirResistance);
  					ControlPanel.textFlightTime.setText(ControlPanel.file_textFlightTime);
        			ControlPanel.textMaxHeight.setText(ControlPanel.file_textMaxHeight);
        			ControlPanel.textRange.setText(ControlPanel.file_textRange);
        								
        			ControlPanel.AnimationPanel.Arrow.load(25,357);					

        			} 
        			catch (Exception e1) 
        			{
        				e1.printStackTrace();
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
			else if(s == "cMass")
			{
				if (ControlPanel.comboboxMass.getSelectedItem().equals("stalowa"))	      			
	    			{	        				
					ControlPanel.mass = ControlPanel.mass1;
					ControlPanel.labelMass.setText("Masa wybranej strza³y: " + ControlPanel.mass1 + " kg");	//7 kg
	    			}
	    			else if (ControlPanel.comboboxMass.getSelectedItem().equals("aluminiowa"))	      			
	    			{
	    				ControlPanel.mass = ControlPanel.mass2;
	    				ControlPanel.labelMass.setText("Masa wybranej strza³y: " + ControlPanel.mass2 + " kg");	//2.4 kg
	    			}
	    			else if (ControlPanel.comboboxMass.getSelectedItem().equals("tytanowa"))	      			
	    			{
	    				ControlPanel.mass = ControlPanel.mass3;
	    				ControlPanel.labelMass.setText("Masa wybranej strza³y: " + ControlPanel.mass3 + " kg");	//13 kg
	    			}
			}
			else if(s == "Losuj opór powietrza")
			{
				Random rand = new Random();
	    		ControlPanel.resistance = rand.nextInt(100)+1;	      		      			
	    		ControlPanel.textAirResistance.setText(String.valueOf(ControlPanel.resistance));
			}
			else if(s == "Start")
			{
				ControlPanel.flighttime = (2 *  ControlPanel.speedValue * Math.sin(Math.toRadians(ControlPanel.angleValue)) ) / ControlPanel.g; //wzory /
				ControlPanel.textFlightTime.setText(String.valueOf(String.format("%.02f", ControlPanel.flighttime) + " [s]")); //wyswietli  wartoœæ oporu
					        			       				        				
				ControlPanel.maxheight = Math.pow(ControlPanel.speedValue * Math.sin(Math.toRadians(ControlPanel.angleValue)), 2) / (2 * ControlPanel.g);
				ControlPanel.textMaxHeight.setText(String.valueOf(String.format("%.02f", ControlPanel.maxheight) + " [m]")); 
					        			
				ControlPanel.range = ( Math.pow(ControlPanel.speedValue, 2) * Math.sin( 2* Math.toRadians(ControlPanel.angleValue)) ) / ControlPanel.g;
				ControlPanel.textRange.setText(String.valueOf(String.format("%.02f", ControlPanel.range) + " [m]")); 			
					
				pl.edu.pw.fizyka.pojava.Trela_Kucharski.AnimationPanel.gamelooptimer.start();
			
			
			}
			else if(s == "Stop")
			{
				pl.edu.pw.fizyka.pojava.Trela_Kucharski.AnimationPanel.gamelooptimer.stop();			
			}
			else if(s == "Reset")
			{
					ControlPanel.AnimationPanel.Arrow.reset(25,357, ControlPanel.Vx(ControlPanel.speedValue, ControlPanel.angleValue, ControlPanel.mass, ControlPanel.resistance),ControlPanel.Vy(ControlPanel.speedValue, ControlPanel.angleValue, ControlPanel.mass, ControlPanel.resistance));					
	        		ControlPanel.AnimationPanel.repaint();
	        		pl.edu.pw.fizyka.pojava.Trela_Kucharski.AnimationPanel.gamelooptimer.stop();
				
	        		ControlPanel.textAirResistance.setText(""); 
	        		ControlPanel.textFlightTime.setText("Czas lotu strza³y");
	        		ControlPanel.textMaxHeight.setText("Max. wysokoœæ");
	        		ControlPanel.textRange.setText("Zasiêg strza³y");
	        		ControlPanel.labelSpeedValue.setText("Prêdkoœæ strza³y: 0 m/s");
	        		ControlPanel.labelAngleValue.setText("K¹t nachylenia ³uku do ziemi: 0 °");
	        		ControlPanel.labelMass.setText("Masa wybranej strza³y: ");  
	        		ControlPanel.sliderAngleValue.setValue(0);
	        		ControlPanel.sliderSpeedValue.setValue(0);
			}
			
		
		}
	}
}
