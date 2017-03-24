package org.team1540.trajgen;

import java.io.IOException;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class GenSimpleAuto {
	
	public static int RED = 0;
	public static int BLUE = 1;
	
	public static int BOILER = 0;
	public static int MIDDLE = 1;
	public static int LOADING = 2;
	
	public static double wheelbaseWidth = 0.0;
	
	public static void main(String[] args) throws IOException {
		writeTrajectorytoFiles(genTrajectory(RED,MIDDLE),"/Users/Jonathan/gen_left.csv","/Users/Jonathan/gen_right.csv");
	}
	
	/**
	 * Generates a trajectory given a side an a position
	 * @param alliance 0 for RED, 1 for BLUE
	 * @param position 0 for BOILER, 1 for MIDDLE, 2 for LOADING 
	 * @return The generated trajectory
	 */
	public static Trajectory genTrajectory(int alliance, int position) {
		
		/*
		 * Coordinates are as in the field diagram in the manual: red alliance on the left
		 * with the boiler side on the bottom. Coordinates are based around where the 
		 * theoretical intersection between the boiler side wall and red alliance wall
		 * (that location is 0,0). Units are in feet.
		 */
		
		if (alliance < 0 || alliance > 1) {
			throw new IllegalArgumentException("Alliance " + alliance + " invalid (must"
					+ " be between 0 and 1 inclusive");
		}
		if (position < 0 || position > 2) {
			throw new IllegalArgumentException("Position "+ position + " invalid (must"
					+ " be between 0 and 2 inclusive");
		}
		
		Waypoint[] points;
		
		double driverStation = 27;
		double fieldLength = 54+4/12;
		double distanceToAirship = 9.525;
		double airshipHeight = 5.875;
		double airshipSideLength = airshipHeight*Math.tan(Math.PI/6);
		double gearPegOffset = 0.875/Math.sqrt(2);
		double boilerOffset = 3.5/Math.sqrt(2);
		double retrivalZoneOffset = (3.5*(13+9.5/12))/(6+(3.5/12));
		
		//mmm hard coding. doesn't matter since there are so few combos
		if (alliance == 0) {
			if (position == 0) {
				//start at the boiler facing ahead, drive until in front of the peg,
				//then go straight on
				points = new Waypoint[] {
						new Waypoint(0, boilerOffset, 0),
						new Waypoint(distanceToAirship+airshipHeight/4-gearPegOffset, 
								driverStation/2-airshipSideLength-gearPegOffset,
								Math.PI/4),
						new Waypoint(distanceToAirship+airshipHeight/4,
								driverStation/2-airshipSideLength,
								Math.PI/4)
				};
			} else if (position == 1) {
				//start in the middle and drive dead ahead
				points = new Waypoint[] {
						new Waypoint(0, driverStation/2, 0),
						new Waypoint(distanceToAirship, driverStation/2, 0)
				};
			} else {
				//start at the edge of the loading zone facing ahead, drive until
				//in front of the peg, then go straight on
				points = new Waypoint[] {
						new Waypoint(0, driverStation-retrivalZoneOffset, 0),
						new Waypoint(distanceToAirship+airshipHeight/4-gearPegOffset, 
								driverStation/2+airshipSideLength+gearPegOffset,
								Math.PI/4),
						new Waypoint(distanceToAirship+airshipHeight/4, 
								driverStation/2+airshipSideLength,
								Math.PI/4)
				};
			}
		} else {
			if (position == 0) {
				//start at the boiler facing ahead, drive until in front of the peg,
				//then go straight on
				points = new Waypoint[] {
						new Waypoint(fieldLength, boilerOffset, Math.PI),
						new Waypoint(fieldLength-(distanceToAirship+airshipHeight/4-gearPegOffset), 
								driverStation/2-airshipSideLength-gearPegOffset,
								Math.PI-Math.PI/4),
						new Waypoint(fieldLength-(distanceToAirship+airshipHeight/4),
								driverStation/2-airshipSideLength,
								Math.PI-Math.PI/4)
				};
			} else if (position == 1) {
				//start in the middle and drive dead ahead
				points = new Waypoint[] {
						new Waypoint(fieldLength, driverStation/2, Math.PI),
						new Waypoint(fieldLength-distanceToAirship, driverStation/2, Math.PI)
				};
			} else {
				//start at the edge of the loading zone facing ahead, drive until
				//in front of the peg, then go straight on
				points = new Waypoint[] {
						new Waypoint(fieldLength, driverStation-retrivalZoneOffset, Math.PI),
						new Waypoint(fieldLength-(distanceToAirship+airshipHeight/4-gearPegOffset), 
								driverStation/2+airshipSideLength+gearPegOffset,
								Math.PI-Math.PI/4),
						new Waypoint(fieldLength-(distanceToAirship+airshipHeight/4), 
								driverStation/2+airshipSideLength,
								Math.PI-Math.PI/4)
				};
			}
		}
		
		// Create the Trajectory Configuration
		//
		// Arguments:
		// Fit Method:          HERMITE_CUBIC or HERMITE_QUINTIC
		// Sample Count:        SAMPLES_HIGH (100 000)
		//		                SAMPLES_LOW  (10 000)
		//		                SAMPLES_FAST (1 000)
		// Time Step:           0.05 Seconds
		// Max Velocity:        1.7 m/s
		// Max Acceleration:    2.0 m/s/s
		// Max Jerk:            60.0 m/s/s/s
		Trajectory.Config config = new Trajectory.Config(
				Trajectory.FitMethod.HERMITE_CUBIC, 
				Trajectory.Config.SAMPLES_HIGH, 
				0.02, 
				250, 
				100, 
				2000);
		
		// Generate the trajectory
		return Pathfinder.generate(points, config);
	}
	
	public static void writeTrajectorytoFiles(Trajectory path, String left, String right) throws IOException {
		TankModifier modifier = new TankModifier(path);
		modifier.modify(wheelbaseWidth);
		CSVExporter.exportTrajectory(left, right, modifier.getLeftTrajectory(), modifier.getRightTrajectory());
		System.out.println("Successfully wrote to files " + left + " and " + right);
	}
}
