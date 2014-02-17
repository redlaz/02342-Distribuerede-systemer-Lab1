package Server;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer
{
	private Window window;
	private SocketServer server;
	private static float mean;
	private static int numOfSamples = 0;
	
	public void startServer() 
	{
		window = new Window(this);
		window.setVisible();
		
		server = new SocketServer();
		server.addObserver(this);
		Thread thread = new Thread(server);
		thread.start();
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if (arg1 instanceof String)
		{
			window.addToLog((String)arg1);
		}

		else
		{
			byte data = (byte)arg1;
			float temperature = (float)((int)data & 0xFF);
			temperature = temperature/10;
			numOfSamples++;
			mean += temperature;
			
			window.setTemperature(mean / numOfSamples);
			window.addToLog("Sensor reported temperature: " + temperature);	
		}
	}
}
