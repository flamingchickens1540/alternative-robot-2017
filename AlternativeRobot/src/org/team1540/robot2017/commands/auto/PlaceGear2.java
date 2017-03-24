package org.team1540.robot2017.commands.auto;

import org.team1540.robot2017.Robot;
import org.team1540.robot2017.commands.PlaceGear;

public class PlaceGear2 extends PlaceGear {
    
    public PlaceGear2() {
        setTimeout(Robot.tuning.getAutoGearPlacementSecs());
    }
    
}
