package org.team1540.robot2017;

public class RobotUtil {
	public static double deadzone(double input, double range) {
		if (Math.abs(input)<range) {
			return 0.0;
		}
		return input;
	}
	public static double limit(double input, double max, double min) {
		if (input>max) {
			return max;
		} else if(input<min) {
			return min;
		}
		return input;
	}
}
