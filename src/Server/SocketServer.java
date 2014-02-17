package Server;

import java.awt.Frame;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

public class SocketServer extends Observable implements Runnable,Observer
{
	private ServerSocket serverSocket;


	@Override
	public void run() 
	{
		try 
		{
			serverSocket = new ServerSocket(5000);
			
			while(true)
			{
				Socket client = serverSocket.accept();
				SocketServerClient sensor = new SocketServerClient(client);
				sensor.addObserver(this);
		
				
				Thread clientConnectionThread = new Thread(sensor);
				clientConnectionThread.start();
			}
		} 
		
		catch (IOException e) 
		{
			JOptionPane.showMessageDialog(new Frame(), "server io fejl: ");
		}	
	}
	



	@Override
	public void update(Observable o, Object arg) 
	{
		setChanged();
		notifyObservers(arg);
	}
}
