package Client;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Window 
{
	private JFrame frmSensorClient;
	private JLabel labelTemperature;
	private Controller controller;
	private boolean running = false;
	private String CELCIUS  = "\u00b0";
	private JTextField textServerAddr;
	private JButton buttonStart;
	private JLabel lblSensor;

	public Window(Controller controller) 
	{
		initialize();
		this.controller = controller;
	}
	
	public void setVisible()
	{
		frmSensorClient.setVisible(true);
	}
	
	public void setTemperature(float temperature)
	{
		float convertedTemperature = temperature;
		labelTemperature.setText("" + convertedTemperature + CELCIUS);
	}
	
	public String getServerAddr()
	{
		return textServerAddr.getText();
	}
	
	public void toggleButton()
	{
		if (!running)
		{
			buttonStart.setText("Disconnect");
			running = true;
			controller.StartSensor();
		}
		
		else
		{
			running = false;
			buttonStart.setText("Connect");
			controller.stopSensor();
			labelTemperature.setText("-");
		}
	}

	private void initialize()
	{
		frmSensorClient = new JFrame();
		frmSensorClient.setResizable(false);
		frmSensorClient.setFont(new Font("Dialog", Font.PLAIN, 10));
		frmSensorClient.setTitle("Sensor");
		frmSensorClient.setBounds(100, 100, 197, 260);
		frmSensorClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSensorClient.getContentPane().setLayout(null);
		
		buttonStart = new JButton("Connect");
		buttonStart.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				toggleButton();
			}
		});
		
		buttonStart.setBounds(10, 123, 171, 37);
		frmSensorClient.getContentPane().add(buttonStart);
		
		labelTemperature = new JLabel("0" + CELCIUS);
		labelTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		labelTemperature.setFont(new Font("Tahoma", Font.PLAIN, 60));
		labelTemperature.setBounds(10, 45, 171, 73);
		frmSensorClient.getContentPane().add(labelTemperature);
		
		textServerAddr = new JTextField();
		textServerAddr.setFont(new Font("Tahoma", Font.PLAIN, 37));
		textServerAddr.setHorizontalAlignment(SwingConstants.CENTER);
		textServerAddr.setText("127.0.0.1");
		textServerAddr.setBounds(10, 171, 171, 38);
		frmSensorClient.getContentPane().add(textServerAddr);
		textServerAddr.setColumns(10);
		
		lblSensor = new JLabel("Sensor");
		lblSensor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSensor.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblSensor.setBounds(10, 0, 176, 49);
		frmSensorClient.getContentPane().add(lblSensor);
	}
}
