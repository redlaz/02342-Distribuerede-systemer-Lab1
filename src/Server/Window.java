package Server;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class Window 
{
	private JFrame frmServer;
	private JLabel labelTemperature;
	private String CELCIUS  = "\u00b0";
	private JTextArea textLog;

	public Window(Controller controller) 
	{
		initialize();
	}
	
	public void setVisible()
	{
		frmServer.setVisible(true);
	}
	
	public void setTemperature(float temperature)
	{
		String temperatureText = (String.format("%.02f", temperature));
		labelTemperature.setText(temperatureText + CELCIUS);
	}
	
	public void addToLog(String text)
	{
		textLog.setText(text + "\r\n" + textLog.getText() );
	}


	private void initialize() 
	{
		frmServer = new JFrame();
		frmServer.setResizable(false);
		frmServer.setTitle("Server");
		frmServer.setBounds(100, 100, 234, 262);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);
		
		labelTemperature = new JLabel("0" + CELCIUS);
		labelTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		labelTemperature.setFont(new Font("Tahoma", Font.PLAIN, 60));
		labelTemperature.setBounds(10, 57, 208, 56);
		frmServer.getContentPane().add(labelTemperature);
		
		JLabel lblServer = new JLabel("Server");
		lblServer.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblServer.setBounds(26, 11, 176, 49);
		frmServer.getContentPane().add(lblServer);
		
		textLog = new JTextArea();
		textLog.setFont(new Font("Tahoma", Font.PLAIN, 9));
		textLog.setBounds(10, 124, 208, 99);
		frmServer.getContentPane().add(textLog);
	}
}
