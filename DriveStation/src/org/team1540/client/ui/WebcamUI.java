package org.team1540.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
	private Color boxColor; 
	
	//indents show hierarchy, bottom to top
		private JTextField urlBar;
		private WebcamPanel webcamView;
			private JButton pickColor = new JButton("Pick color");
		private JPanel rightSide = new JPanel(new BorderLayout());
			private JTextArea log = new JTextArea(10,100);
		private JScrollPane logWindow = new JScrollPane(log);
	private JFrame mainWindow = new JFrame("Webcam Stream");	
	
	/**
	 * Initializes a WebcamUI with the default URL and the window size set to the 
	 * screen size.
	 */
	public WebcamUI() {
		windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		windowSize.setSize(windowSize.getWidth(), windowSize.getHeight());
		
		initialize();
	}
	
	/**
	 * Initializes a WebcamUI with the given URL and the given window size.
	 * @param url URL to use with the webcam
	 * @param windowSize Window size to use.
	 */
	public WebcamUI(String url, Dimension windowSize) {
		URL = url;
		this.windowSize = windowSize;
		
		initialize();
	}
	
	private void initialize() {
		log.setEditable(false);
		PrintStream printOut = new PrintStream(new LogOutput());
		System.setOut(printOut);
		System.setErr(printOut);
		
		mainWindow.setResizable(true);
		mainWindow.setLayout(new BorderLayout());
		mainWindow.setSize((int) windowSize.getWidth(), (int) windowSize.getHeight());
		
		urlBar = new JTextField(URL);
		urlBar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		mainWindow.add(urlBar, BorderLayout.NORTH);
		
		Webcam.setDriver(new IpCamDriver());
		update();
		
		pickColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boxColor = JColorChooser.showDialog(null, "Choose a color", boxColor);	
			}
		});
		rightSide.add(pickColor, BorderLayout.NORTH);
		
		mainWindow.add(rightSide, BorderLayout.EAST);
		mainWindow.add(logWindow, BorderLayout.SOUTH);
		
		mainWindow.setVisible(true);
	}
	
	private void update() {
		if (URL.equals(urlBar.getText()) && webcamView != null) {
			return;
		}
		URL = urlBar.getText();
		try {
			IpCamDeviceRegistry.register(URL, URL, IpCamMode.PUSH);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		}
		if (Webcam.getWebcams().size() > 1) {
			IpCamDeviceRegistry.unregister(Webcam.getWebcams().get(0).getName());	
		}
		if (webcamView != null) {
			mainWindow.remove(webcamView);
		}
		webcamView = new WebcamPanel(Webcam.getWebcams().get(0));
		webcamView.start();
		mainWindow.add(webcamView, BorderLayout.CENTER);
		mainWindow.setTitle(URL);
		mainWindow.revalidate();
		mainWindow.repaint();
	}
	
	private class LogOutput extends OutputStream {
		
		public void write(int b) throws IOException {
			log.append(String.valueOf((char)b));
			log.setCaretPosition(log.getDocument().getLength());
		}
		
	}
}
