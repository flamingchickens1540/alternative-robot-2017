package org.team1540.trajgen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;

public class CSVExporter {
	public static void exportTrajectory(String leftFilename, String rightFilename, Trajectory left, Trajectory right) throws IOException {
		StringBuilder l = new StringBuilder();
		StringBuilder r = new StringBuilder();
		for (int i=0; i<left.segments.length; ++i) {
			Segment s = left.segments[i];
			l.append(-s.position + "," + -s.velocity + "," + (s.dt*1000) + "\n");
		}
		
		for (int i=0; i<right.segments.length; ++i) {
			Segment s = right.segments[i];
			r.append(s.position + "," + s.velocity + "," + (s.dt*1000) + "\n");
		}
		
		Files.write(Paths.get(leftFilename), l.toString().getBytes());
		Files.write(Paths.get(rightFilename), r.toString().getBytes());
	}
}
