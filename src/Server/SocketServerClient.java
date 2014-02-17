package Server;

import java.awt.Frame;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Observable;
import javax.swing.JOptionPane;

public class SocketServerClient extends Observable implements Runnable
{
	private Socket socket;
	private boolean keepRunning = true;
	private int CONNECTION_TIMEOUT = 2000; 
	private static int sensorNumberCount = 1;
	private int sensorNumber = 1;
	private byte[] commandToSensor = new byte[1];
	private DataInputStream in;
	private boolean streaming = false;

	public SocketServerClient(Socket socket)
	{
		this.socket = socket;
		sensorNumber = sensorNumberCount;
		sensorNumberCount++;
		
		try 
		{
			in = new DataInputStream(socket.getInputStream());
		} 
		
		catch (IOException e) 
		{
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	public boolean isStillRunning()
	{
		return keepRunning;
	}
	
	public int getSensorNumber()
	{
		return sensorNumber;
	}
	
	public void sendData(int data)
	{
		commandToSensor[0] = (byte)data;
		
		try 
		{
			OutputStream outputStream = socket.getOutputStream(); 
			outputStream.write(commandToSensor);
			streaming = !streaming;
		} 
		
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void run()
	{		
		setChanged();
		notifyObservers("New sensor connected: " + socket.getRemoteSocketAddress());
		
		try 
		{
			this.socket.setSoTimeout(CONNECTION_TIMEOUT);
			while (keepRunning) 
			{
				byte data = in.readByte();

				setChanged();
				notifyObservers(data);
			}
		} 
		
		catch (SocketTimeoutException e)
		{
				keepRunning = false;
				setChanged();
				notifyObservers("Sensor disconnected");
		
		}
		
		catch (IOException e) 
		{
			keepRunning = false;
			setChanged();
			notifyObservers("Sensor connection lost");
			
			try 
			{
				socket.close();
			} 
			
			catch (IOException e1) 
			
			{
				keepRunning = false;
				JOptionPane.showMessageDialog(new Frame(), "Server socket close error: " + e.getMessage());
			}
		}
	}
}
