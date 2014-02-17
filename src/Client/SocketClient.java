package Client;
import java.io.*;
import java.net.*;

public class SocketClient
{
	private Socket socket;
	OutputStream outputStream;
	InputStream inputStream;
	
	public SocketClient(String ipAddr, int port) throws UnknownHostException, IOException
	{
		socket = new Socket(ipAddr,port);
		socket.setKeepAlive(true);
		outputStream = socket.getOutputStream(); 
		inputStream = socket.getInputStream();
	}

	public void send(byte data) throws IOException
	{
		byte[] b = {data};
		outputStream.write(b);
		outputStream.flush();
	}

	
		

}
