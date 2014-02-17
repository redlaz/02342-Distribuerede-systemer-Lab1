package Client;

import java.awt.Frame;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

public class Controller implements Observer
{
	private Window window;
	private Sensor sensor;
	private Thread sensorThread;
	private SocketClient socketClient;
	

	public void startProgram() 
	{
		window = new Window(this);
		window.setVisible();
	}
	
	public void StartSensor()
	{
		try 
		{
			String serverAddr = window.getServerAddr();
			socketClient = new SocketClient(serverAddr, 5000);
			
			
			this.sensor = new Sensor();
			sensor.addObserver(this);
			sensorThread = new Thread(sensor);
	        sensorThread.start();
		} 
		
		catch (UnknownHostException e) 
		{
			JOptionPane.showMessageDialog(new Frame(), "Client: Unknow Host: " + e.getMessage());
		} 
		
		catch (IOException e) 
		{
			JOptionPane.showMessageDialog(new Frame(), "IO error: " + e.getMessage());
		}
	}
	
	public void stopSensor() 
	{
		sensor.stop();
		sensor = null;
		socketClient = null;
	}
	
	public void update(Observable o, Object arg) 
	{
			
		
			float temp = (float) arg;
			float tempTimesTen = temp * 10;
	        window.setTemperature(temp);
	        
	        byte temperatureAsByte = (byte)(tempTimesTen);

	        try 
	        {
	        	if (socketClient != null)
	        		socketClient.send(temperatureAsByte);
			} 
	        
	        catch (IOException e) 
			{
	        	JOptionPane.showMessageDialog(new Frame(), "Server disconnected");
				sensor.stop();
				sensor = null;
				socketClient = null;
			}

	}
}
