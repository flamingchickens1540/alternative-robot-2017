package org.team1540.robot2017.motion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVMotionProfile {
    public final double[][] points;
    public final int kNumPoints;
    
    public CSVMotionProfile(String filename) throws IOException {
        Stream<String> csvFile = Files.lines(Paths.get(filename));
        List<double[]> dataList = csvFile.map(line -> {
            String[] elements = line.split(",");
            if (elements.length != 3) {
                return null;
            } else {
                double pos = Double.parseDouble(elements[0]);
                double vel = Double.parseDouble(elements[1]);
                double dt = Double.parseDouble(elements[2]);
                return new double[] { pos, vel, dt };
            }
        }).collect(Collectors.toList());
        csvFile.close();
        
        points = new double[dataList.size()][3];
        for (int i=0; i<points.length; ++i) {
            double[] elem = dataList.get(i);
            points[i][0] = elem[0];
            points[i][1] = elem[1];
            points[i][2] = elem[2];
        }
        
        kNumPoints = points.length;
    }
}
