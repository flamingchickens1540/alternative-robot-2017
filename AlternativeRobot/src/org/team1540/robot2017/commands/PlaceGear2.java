package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

public class PlaceGear2 extends PlaceGear {
    
    public PlaceGear2() {
        setTimeout(Robot.tuning.getAutoGearPlacementSecs());
    }
    
}
