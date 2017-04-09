package org.team1540.robot2017;

public class RobotUtil {
    
    public static double deadzone(double input, double range) {
        if (Math.abs(input) < range) {
            return 0.0;
        }
        return input;
    }
    
    public static double betterDeadzone(double input, double range, double exponent) {
        if (Math.abs(input) > range) {
            if (input > 0) {
                return Math.pow((input - range) / (1 - range), exponent);
            }
            else {
                return -Math.pow((-input - range) / (1 - range), exponent);
            }
        }
        else {
            return 0;
        }
    }

    public static double limit(double input, double max, double min) {
        if (input > max) {
            return max;
        } else if (input < min) {
            return min;
        }
        return input;
    }
    
    public static double joystickApproximateForward(double x, double y, double precision) {
        // returns +1.0 for full forward, -1.0 for full backward
        // if the joystick is pressed full forward but slightly to
        // the side, it still registers full forward; this function
        // is continuous
        // y = magnitude * clamp(precision * sin(angle), 1, -1)
        double angle = Math.atan2(y, x);
        double magnitude = Math.sqrt(x*x + y*y);
        return magnitude * limit(precision * Math.sin(angle), 1, -1);
//        return y;
    }
}
