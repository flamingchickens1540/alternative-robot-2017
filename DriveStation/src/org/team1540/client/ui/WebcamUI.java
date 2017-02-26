package org.team1540.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
	public int dpi = 92;
	private Color boxColor; 
	
	//indents show hierarchy, bottom to top
		private JTextField urlBar;
				private BoxPanel boxPanel = new BoxPanel();
			private WebcamPanel webcamView;
		private JPanel webcamContainer = new JPanel();
			private JPanel colorViewer = new JPanel();
			private JButton pickColor = new JButton("Pick color");
		private JPanel rightSide = new JPanel();
			private JTextArea log;
		private JScrollPane logWindow;
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
		log = new JTextArea((int) (windowSize.getHeight()/dpi*6*.1),
				(int) (windowSize.getWidth()/dpi*6*1));
		logWindow = new JScrollPane(log);
		log.setEditable(false);
		PrintStream printOut = new PrintStream(new LogOutput());
		System.setOut(printOut);
		System.setErr(printOut);
		
		mainWindow.setResizable(true);
		mainWindow.setLayout(new BorderLayout());
		mainWindow.setSize((int) windowSize.getWidth(), (int) windowSize.getHeight());
		
		rightSide.setLayout(new BoxLayout(rightSide,BoxLayout.Y_AXIS));
		
		urlBar = new JTextField(URL);
		urlBar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateWebcam();
			}
		});
		mainWindow.add(urlBar, BorderLayout.NORTH);
		
		boxPanel.setOpaque(false);
		Webcam.setDriver(new IpCamDriver());
		updateWebcam();
		boxPanel.setAlignmentX(boxPanel.CENTER_ALIGNMENT);
		boxPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		colorViewer.setBackground(boxColor);
		colorViewer.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(10, 10, 10, 10, 
						mainWindow.getBackground()),
						BorderFactory.createLineBorder(Color.BLACK)));
		rightSide.add(colorViewer);
		pickColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boxColor = JColorChooser.showDialog(null, "Choose a color", boxColor);
				colorViewer.setBackground(boxColor);
				boxPanel.revalidate();
				boxPanel.repaint();
			}
		});
		pickColor.setAlignmentX(pickColor.CENTER_ALIGNMENT);
		rightSide.add(pickColor);
		
		mainWindow.add(rightSide, BorderLayout.EAST);
		mainWindow.add(logWindow, BorderLayout.SOUTH);
		
		mainWindow.setVisible(true);
	}
	
	private void updateWebcam() {
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
		Webcam currentWebcam = Webcam.getWebcams().get(0);	
		
		webcamView = new WebcamPanel(currentWebcam);
		webcamView.setLayout(new BorderLayout());
		
		webcamView.add(boxPanel, BorderLayout.CENTER);
		webcamView.start();
		
		webcamView.setPreferredSize(currentWebcam.getViewSize());
		
		//TODO make this not stupid so it scales while maintaining aspect ratio
		webcamContainer.setLayout(new GridBagLayout());
		webcamContainer.add(webcamView);
		
		mainWindow.add(webcamContainer, BorderLayout.CENTER);
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
	
	private class BoxPanel extends JPanel {
		
		////////////
		// 0,0 | 1,0
		// 0,1 | 1,1
		private Point[][] points = {{new Point(0,0), new Point(0,50)},
				{new Point(50,0), new Point(50,50)}};
		
		private BoxPanelMouseListener polygonListener = 
				new BoxPanelMouseListener();
		
		public BoxPanel() {
			this.addMouseListener(polygonListener);
			this.addMouseMotionListener(polygonListener);
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(boxColor);
			//works clockwise from the top left
			int[] xPoints = {(int) points[0][0].getX(), (int) points[1][0].getX(),
					(int) points[1][1].getX(), (int) points[0][1].getX()};
			int[] yPoints = {(int) points[0][0].getY(), (int) points[1][0].getY(),
					(int) points[1][1].getY(), (int) points[0][1].getY()};
			g.drawPolygon(new Polygon(xPoints,yPoints,4));
		}
		
		private class BoxPanelMouseListener implements MouseListener,
		MouseMotionListener {
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
		
	}
}
