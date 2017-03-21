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
		    new Waypoint(-8, -2, Pathfinder.d2r(-45)),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
		    new Waypoint(-4, -4, 0),                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
		    new Waypoint(0, 0, 0)                           // Waypoint @ x=0, y=0,   exit angle=0 radians
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
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.02, 250, 250, 2000);
		
		// Generate the trajectory
		Trajectory trajectory = Pathfinder.generate(points, config);
		
		// The distance between the left and right sides of the wheelbase is 0.6m
		double wheelbase_width = 0.6;

		// Create the Modifier Object
		TankModifier modifier = new TankModifier(trajectory);

		// Generate the Left and Right trajectories using the original trajectory
		// as the centre
		modifier.modify(wheelbase_width);
		
		CSVExporter.exportTrajectory("/Users/jake/left2.csv", "/Users/jake/right2.csv", modifier.getLeftTrajectory(), modifier.getRightTrajectory());
	}
}
