package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.commands.TestLeds;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LedBar extends Subsystem {
    private Spark leds = new Spark(0);
    
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new TestLeds());
    }
    
    public void setBrightness(double value) {
        leds.set(value);
    }
}
