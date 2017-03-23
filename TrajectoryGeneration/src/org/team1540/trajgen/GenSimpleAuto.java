package org.team1540.trajgen;

import java.io.IOException;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class GenSimpleAuto {
	public static void main(String[] args) throws IOException {
		// 3 Waypoints
		Waypoint[] points = new Waypoint[] {
		    new Waypoint(0, 0, 0)
		};

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
		Trajectory trajectory = Pathfinder.generate(points, config);
		
		// The distance between the left and right sides of the wheelbase is 0.6m
		double wheelbase_width = 5.0;

		// Create the Modifier Object
		TankModifier modifier = new TankModifier(trajectory);

		// Generate the Left and Right trajectories using the original trajectory
		// as the centre
		modifier.modify(wheelbase_width);
		
		CSVExporter.exportTrajectory("/Users/jake/left2.csv", "/Users/jake/right2.csv", modifier.getLeftTrajectory(), modifier.getRightTrajectory());
		CSVExporter.exportPath("/Users/jake/path.csv", modifier.getLeftTrajectory());
		System.out.println("Successfully wrote to files " + "left2.csv" + " and " + "right2.csv");
	}
}
