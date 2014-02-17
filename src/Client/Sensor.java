package Client;

import java.awt.Frame;
import java.util.Observable;

import javax.swing.JOptionPane;


public class Sensor extends Observable implements Runnable
{
	private int max = 24*10;
	private int min = 14*10;
	private boolean stopped = false;
	
	

	public void stop()
	{
		this.stopped = true;
	}
	


	@Override
	public void run() 
	{
		
		while (!stopped) 
		{
			try 
			{
				float temperature = min + (int) (Math.random() * ((max - min) + 1));
				Thread.sleep(1000);
	
					setChanged();
					notifyObservers(temperature / 10);
	
				
			}

			catch (InterruptedException e)
			{
				JOptionPane.showMessageDialog(new Frame(), "Fejl i tråd: " + e.getMessage());
			}
		}		
	}
	
}
