package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;
import org.team1540.robot2017.RobotUtil;
import org.team1540.robot2017.commands.LEDCycleRainbow;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LEDs extends Subsystem {
	private final Talon redChannel = new Talon(RobotMap.redPWM);
	private final Talon greenChannel = new Talon(RobotMap.greenPWM);
	private final Talon blueChannel = new Talon(RobotMap.bluePWM);
	
	private int red = 0;
	private int green = 0;
	private int blue = 0;

	public LEDs() {
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LEDCycleRainbow());
	}
	
	public void setRGB(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		redChannel.set(((double) Math.abs(red))/255);
		greenChannel.set(((double) Math.abs(green))/255);
		blueChannel.set(((double) Math.abs(blue))/255);
	}
	
	public int getRed() {
		return red;
	}
	public int getGreen() {
		return green;
	}
	public int getBlue() {
		return blue;
	}
}
