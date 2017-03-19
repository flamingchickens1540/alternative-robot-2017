package org.team1540.client.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

public class UITest {

	public static void main(String[] args) {
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		windowSize.setSize(windowSize.getWidth()/2, windowSize.getHeight()/2);
		
		new WebcamUI("http://localhost:1181/stream.mjpg", windowSize);
	}

}
