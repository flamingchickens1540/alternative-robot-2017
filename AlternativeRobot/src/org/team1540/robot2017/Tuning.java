package org.team1540.robot2017;

import edu.wpi.first.wpilibj.Preferences;

public class Tuning {

    private Preferences tuning;

    public Tuning() {
        tuning = Preferences.getInstance();
    }

    public double getShooterFlywheelSpeed() {
        return tuning.getDouble("Shooter Flywheel Target Speed", 20000); // constant
                                                                         // constant
                                                                         // that
                                                                         // you
                                                                         // could
                                                                         // change
    }

    public double getFeederTopPeriod() {
        return tuning.getDouble("Feeder Top Period", 250);
    }

    public double getFeederSideSwitchPeriod() {
        return tuning.getDouble("Feeder Side Switch Period", 250);
    }

    public double getFlywheelP() {
        return tuning.getDouble("Shooter Flywheel PID P", 0.14);
    }

    public double getFlywheelI() {
        return tuning.getDouble("Shooter Flywheel PID I", 0.0001);
    }

    public double getFlywheelD() {
        return tuning.getDouble("Shooter Flywheel PID D", 0.05);
    }

    public double getFlywheelF() {
        return tuning.getDouble("Shooter Flywheel PID F", 1 / 26274);
    }
    
    public double getFlywheelSpeedMarginOfError() {
        return tuning.getDouble("Flywheel Speed Margin of Error", 200);
    }

    public double getBeltSpeed() {
        return tuning.getDouble("Belt Target Speed", 5000);
    }

    public double getBeltP() {
        return tuning.getDouble("Belt PID P", 0.01);
    }

    public double getBeltI() {
        return tuning.getDouble("Belt PID I", 0.01);
    }

    public double getBeltD() {
        return tuning.getDouble("Belt PID D", 0.01);
    }

    public double getBeltF() {
        return tuning.getDouble("Belt PID F", 0.0001);
    }

    public double getIntakeRegularOutput() {
        return tuning.getDouble("Intake Regular Output", 1);
    }

    public double getIntakeShootingOutput() {
        return tuning.getDouble("Intake Shooting Output", 1);
    }

    public double getFeederTopOutput() {
        return tuning.getDouble("Feeder Top Output", 1);
    }

    public double getFeederSideOutput() {
        return tuning.getDouble("Feeder Side Output", 1);
    }

    public double getDriveRampRate() {
        return tuning.getDouble("Drive Ramp Rate", 0.025);
    }
}
