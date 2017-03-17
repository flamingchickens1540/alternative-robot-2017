package org.team1540.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.fmarot.swing.SingleComponentAspectRatioKeeperLayout;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamException;
import com.github.sarxos.webcam.WebcamImageTransformer;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

public class WebcamUI {

	public String URL = "http://localhost:1181/stream.mjpg";
	//http://1540kangaroo.frc-robot.local:8080/?action=stream
	//http://89.203.137.209/axis-cgi/mjpg/video.cgi
	//http://webcam1.lpl.org/axis-cgi/mjpg/video.cgi
	public Dimension windowSize;
	public int dpi = 113;
	private Color boxColor = new Color(255,255,255); 
	private boolean isRotated = false;
	private boolean isFlipped = false;

	//indents show hierarchy, bottom to top
		private JTextField urlBar;
				private BoxPanel boxPanel = new BoxPanel();
				private Webcam currentWebcam;
			private WebcamPanel webcamView;
		private JPanel webcamContainer = new JPanel();
			private JButton flipWebcam = new JButton("Flip webcam");
			private JButton rotateWebcam = new JButton("Rotate webcam");
			private JPanel colorViewer = new JPanel();
			private JButton pickColor = new JButton("Pick color");
			private JButton writeToFile = new JButton("Write coordinates to file");
			private JButton readFromFile = new JButton("Read coordinates from file");
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

	@SuppressWarnings("static-access")
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
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		boxPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		rotateWebcam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isRotated = !isRotated;
				webcamView.revalidate();
				webcamView.repaint();
			}
		});
		rotateWebcam.setAlignmentX(rotateWebcam.CENTER_ALIGNMENT);
		rightSide.add(rotateWebcam);
		
		flipWebcam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isFlipped = !isFlipped;
				webcamView.revalidate();
				webcamView.repaint();
			}
		
		});
		flipWebcam.setAlignmentX(rotateWebcam.CENTER_ALIGNMENT);
		rightSide.add(flipWebcam);
		
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
		
		writeToFile.addActionListener(new ActionListener() {
			JFileChooser selectFile = new JFileChooser();

			@Override
			public void actionPerformed(ActionEvent e) {
				selectFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
				selectFile.showSaveDialog(writeToFile);
				if (selectFile.getSelectedFile() != null) {
					writeCoordinatesToFile(selectFile.getSelectedFile());
					selectFile.setSelectedFile(selectFile.getSelectedFile());
				} else {
					System.err.println("writeToFile: No file was chosen");
				}
			}
		});
		writeToFile.setAlignmentX(writeToFile.CENTER_ALIGNMENT);
		rightSide.add(writeToFile);
		
		readFromFile.addActionListener(new ActionListener() {
			JFileChooser selectFile = new JFileChooser();
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
				selectFile.showOpenDialog(writeToFile);
				if (selectFile.getSelectedFile() != null) {
					readCoordinatesFromFile(selectFile.getSelectedFile());
					selectFile.setSelectedFile(selectFile.getSelectedFile());
				} else {
					System.err.println("readFromFile: No file was chosen");
				}
			}
		});
		readFromFile.setAlignmentX(writeToFile.CENTER_ALIGNMENT);
		rightSide.add(readFromFile);
		
		mainWindow.add(rightSide, BorderLayout.EAST);
		mainWindow.add(logWindow, BorderLayout.SOUTH);

		mainWindow.setVisible(true);
		
		Thread reconnectTimeout = new Thread(() -> {
			while (true) {
				if (currentWebcam != null && !currentWebcam.isOpen()) {
					updateWebcam();
				}
				
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e1) {
				}
			}
		});
		
		reconnectTimeout.setDaemon(true);
		reconnectTimeout.start();
	}

	private synchronized void updateWebcam() {
		if (URL.equals(urlBar.getText()) && webcamView != null) {
			return;
		}
		URL = urlBar.getText();
		try {
			if (!IpCamDeviceRegistry.isRegistered(URL)) {
				IpCamDeviceRegistry.register(URL, URL, IpCamMode.PUSH);
				if (currentWebcam != null) {
					currentWebcam.close();
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		}
		if (Webcam.getWebcams().size() > 1) {
			IpCamDeviceRegistry.unregister(Webcam.getWebcams().get(0).getName());
		}
		if (webcamView != null) {
			webcamContainer.remove(webcamView);
			//mainWindow.remove(webcamContainer);
		}
		currentWebcam = Webcam.getWebcams().get(0);

		currentWebcam.setImageTransformer(new WebcamImageTransformer() {

			@Override
			public BufferedImage transform(BufferedImage image) {
				if (isRotated) {
					int w = image.getWidth();
					int h = image.getHeight();			          
	
					BufferedImage bi = new BufferedImage(
							w, h, BufferedImage.TYPE_INT_BGR);
					Graphics2D g2 = bi.createGraphics();
					g2.rotate(Math.PI, w/2, h/2);
					g2.drawImage(image, 0, 0, null);
					g2.dispose();
					bi.flush();
	
					image = bi;
				}
				if (isFlipped) {
					int w = image.getWidth();
					int h = image.getHeight();			          
	
					BufferedImage bi = new BufferedImage(
							w, h, BufferedImage.TYPE_INT_BGR);
					Graphics2D g2 = bi.createGraphics();
					g2.drawImage(image, w, 0, -w, h, null);
					g2.dispose();
					bi.flush();
					
					image = bi;
				}
				
				return image;
				
			}
		});
		
		try {
			webcamView = new WebcamPanel(currentWebcam);
		} catch (WebcamException e) {
			System.err.println(e.getMessage());
			return;
		}
		webcamView.setLayout(new BorderLayout());

		webcamView.add(boxPanel, BorderLayout.CENTER);
		webcamView.start();

		webcamView.setPreferredSize(currentWebcam.getViewSize());

		webcamContainer.setLayout(new SingleComponentAspectRatioKeeperLayout());
		//webcamContainer.setLayout(new BorderLayout());
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

		/**
		 * Deeefffaaauuulllttt
		 */
		private static final long serialVersionUID = 1L;

		////////////
		// 0,0 | 1,0
		// 0,1 | 1,1
		private final Point[][] points = {{new Point(100,100), new Point(100,200)},
				{new Point(200,100), new Point(200,200)}};

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
			for (int y = 0; y<=1 ; y++) {
				for (int x = 0; x<=1 ; x++) {
					Point pointX = points[x][y];
					g.setColor(new Color(255 - boxColor.getRed(),
							255 - boxColor.getGreen(), 255 - boxColor.getBlue()));
					int thickness = (int) (dpi*.075);
					g.fillOval((int) (pointX.getX() - thickness/2),
							(int) (pointX.getY() - thickness/2), thickness, thickness);
					g.setColor(boxColor);
					g.drawString(x+","+y, (int) pointX.getX(), (int) pointX.getY());
				}
			}
		}

		private class BoxPanelMouseListener implements MouseListener,
		MouseMotionListener {

			private Point pointIsOn = null;

			@Override
			public void mouseDragged(MouseEvent e) {
				if (pointIsOn != null) {
					pointIsOn.setLocation(e.getPoint());
				}
				boxPanel.revalidate();
				boxPanel.repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				//Nothing
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				//Nothing
			}

			@Override
			public void mousePressed(MouseEvent e) {
				for (Point[] yPoints : points) {
					for (Point xPoint : yPoints) {
						if (xPoint.getLocation().distance(e.getPoint()) < dpi*.25) {
							pointIsOn = xPoint;
						}
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				pointIsOn = null;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				//Nothing
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pointIsOn = null;
			}

		}

	}

	/**
	 * Writes the coordinates to the given file in the form
	 * [0,0].x,[0,0].y\t[1,0].x.[1,0].y
	 * [0,1].x,[0,1].y\t[1,1].x.[1,1].y
	 * @param toWriteTo File to write to
	 */
	public void writeCoordinatesToFile(File toWriteTo) {
		if (toWriteTo.isDirectory()) {
			System.err.println("writeToFile: Is directory");
			return;
		}
		try (BufferedWriter writer = Files.newBufferedWriter(
				Paths.get(toWriteTo.getAbsolutePath()),
				Charset.forName("UTF-8"))) {

			Dimension realSize = Webcam.getWebcams().get(0).getViewSize();
			Dimension viewSize = boxPanel.getSize();

			//surely there's a better way to do this, but this should be fine.
			for (int y = 0; y<=1 ; y++) {
				for (int x = 0; x<=1 ; x++) {
					Point point = boxPanel.points[x][y];
					writer.write((point.getX()/viewSize.getWidth())*realSize.getWidth()+
							","+(point.getY()/viewSize.getHeight())*realSize.getHeight()
							+"\t");
				}
				writer.write("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("writeToFile: Wrote to " + toWriteTo.getAbsolutePath());
	}

	/**
	 * Reads the coordinates from the given file in the form
	 * [0,0].x,[0,0].y\t[1,0].x.[1,0].y
	 * [0,1].x,[0,1].y\t[1,1].x.[1,1].y
	 * @param toWriteTo File to read to
	 */
	public void readCoordinatesFromFile(File toReadFrom) {
		if (toReadFrom.isDirectory()) {
			System.err.println("readFromFile: Is directory");
			return;
		}
		try {
			BufferedReader reader = Files.newBufferedReader(Paths.get(
					toReadFrom.getAbsolutePath()),Charset.forName("UTF-8"));
			
			Dimension realSize = Webcam.getWebcams().get(0).getViewSize();
			Dimension viewSize = boxPanel.getSize();
			
			String line;
			int currentLine = 0;
			while ((line = reader.readLine()) != null) {
				
				String[] coordinates = line.split("\t");
				String[] x0Coords = coordinates[0].split(",");
				String[] x1Coords = coordinates[1].split(",");

				boxPanel.points[0][currentLine].setLocation(
						Double.parseDouble(x0Coords[0])/
							realSize.getWidth()*viewSize.getWidth()
						,Double.parseDouble(x0Coords[1])/
							realSize.getHeight()*viewSize.getHeight());
				boxPanel.points[1][currentLine].setLocation(
						Double.parseDouble(x1Coords[0])/
							realSize.getWidth()*viewSize.getWidth()
						,Double.parseDouble(x1Coords[1])/
							realSize.getHeight()*viewSize.getHeight());
				
				currentLine++;
			}
			
			boxPanel.revalidate();
			boxPanel.repaint();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("readFromFile: Read from " + toReadFrom.getAbsolutePath());
	}

}
