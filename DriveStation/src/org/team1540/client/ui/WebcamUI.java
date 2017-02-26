package org.team1540.client.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

public class WebcamUI {

	public String URL = "http://89.203.137.209/axis-cgi/mjpg/video.cgi";
	//http://89.203.137.209/axis-cgi/mjpg/video.cgi
	//http://webcam1.lpl.org/axis-cgi/mjpg/video.cgi
	public Dimension windowSize;
	
	private JFrame mainWindow = new JFrame("Webcam Stream");
	private JTextField urlBar;
	private WebcamPanel webcamView;
	
	public WebcamUI() {
		windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		windowSize.setSize(windowSize.getWidth(), windowSize.getHeight());
		initialize();
	}
	
	public WebcamUI(String url, Dimension windowSize) {
		URL = url;
		this.windowSize = windowSize;
		
		initialize();
	}
	
	private void initialize() {
		mainWindow.setResizable(true);
		mainWindow.setLayout(new BorderLayout());
		mainWindow.setSize((int) windowSize.getWidth(), (int) windowSize.getHeight());
		
		urlBar = new JTextField(URL);
		urlBar.addActionListener(new URLUpdate());
		mainWindow.add(urlBar, BorderLayout.NORTH);
		
		Webcam.setDriver(new IpCamDriver());
		update();
		
		mainWindow.setVisible(true);
	}
	
	private class URLUpdate implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			update();
		}
		
	}
	
	private void update() {
		JTextField textBox = new JTextField(System.currentTimeMillis() + "");
		
		if (URL.equals(urlBar.getText()) && webcamView != null) {
			return;
		}
		URL = urlBar.getText();
		if (webcamView != null) {
			mainWindow.remove(webcamView);
			//mainWindow.remove(textBox);
			System.out.println("Removed");
		}
		try {
			IpCamDeviceRegistry.unregisterAll();
			IpCamDeviceRegistry.register(URL, URL, IpCamMode.PUSH);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println(Webcam.getWebcams());
		webcamView = new WebcamPanel(Webcam.getWebcams().get(0));
		webcamView.start();
		//mainWindow.add(new JTextField(System.currentTimeMillis() + ""));
		//mainWindow.add(textBox);
		mainWindow.add(webcamView, BorderLayout.CENTER);
		mainWindow.setTitle(URL);
		mainWindow.revalidate();
		mainWindow.repaint();
		System.out.println("finished");
	}
	
}
