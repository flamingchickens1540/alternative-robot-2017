package org.team1540.robot2017;

import edu.wpi.first.wpilibj.Preferences;

public class Tuning {

    private Preferences tuning;

    public Tuning() {
        tuning = Preferences.getInstance();
    }
    
    public double getDouble(String name, double defaultValue) {
        return tuning.getDouble(name, defaultValue);
    }

    ///////////////////////////////////////////////////////////////////////////////// shooter flywheel
    
    public double getShooterFlywheelSpeed() {
        return tuning.getDouble("Shooter Flywheel Target Speed", 20000);
    }
    
    public double getAutoFlywheelSpeed() {
        return tuning.getDouble("Auto Flywheel Target Speed", 20500);
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
        return tuning.getDouble("Flywheel Speed Margin of Error", 800);
    }
    
    public double getFlywheelSpeedChangeCoefficient() {
        return tuning.getDouble("Flywheel Speed Change Coefficient", 1000);
    }

    ///////////////////////////////////////////////////////////////////////////////// belt
    
    public double getBeltSpeed() {
        return tuning.getDouble("Belt Target Speed", 5000);
    }
    
    public double getAutoBeltSpeed() {
        return tuning.getDouble("Auto Belt Target Speed", 4000);
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

    ///////////////////////////////////////////////////////////////////////////////// intake, feeder, driving
    
    public double getIntakeRegularOutput() {
        return tuning.getDouble("Intake Regular Output", 1);
    }

    public double getIntakeShootingOutput() {
        return tuning.getDouble("Intake Shooting Output", 1);
    }
    
    public double getFeederTopPeriod() {
        return tuning.getDouble("Feeder Top Period", 250);
    }

    public double getFeederSideSwitchPeriod() {
        return tuning.getDouble("Feeder Side Switch Period", 250);
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
    
    public double getShooterRampRate() {
        return tuning.getDouble("Shooter Ramp Rate", 0.001);
    }
    
    ///////////////////////////////////////////////////////////////////////////////// auto dead-reckoning
    
    public double getAutoShootingSeconds() {
        return tuning.getDouble("Auto Shooting Seconds", 5);
    }
    
    public double getAutoLineDrive1Secs() {
        return tuning.getDouble("Auto Line Drive 1 Secs", 1);
    }
    
    public double getAutoLineTurnSecs() {
        return tuning.getDouble("Auto Line Turn Secs", 0.5);
    }
    
    public double getAutoLineDrive2Secs() {
        return tuning.getDouble("Auto Line Drive 2 Secs", 2);
    }
    
    public double getAutoLineDrive1Set() {
        return tuning.getDouble("Auto Line Drive 1 Set", 0.3);
    }
    
    public double getAutoLineTurnSet() {
        return tuning.getDouble("Auto Line Turn Set", 0.3);
    }
    
    public double getAutoLineDrive2Set() {
        return tuning.getDouble("Auto Line Drive 2 Set", 0.3);
    }
    
    ///////////////////////////////////////////////////////////////////////////////// auto more accurate
    
    public double getAutoLineDriveBackwardDist() {
        return tuning.getDouble("Auto Line Drive Backward Distance", 10);
    }
    
    public double getAutoLineDriveForwardDist() {
        return tuning.getDouble("Auto Line Drive Forward Distance", 10);
    }
    
    public double getAutoLineDegreesToTurn() {
        return tuning.getDouble("Auto Line Degrees To Turn", 0);
    }
    
    public double getAutoTurningP() {
        return tuning.getDouble("Auto Turning PID P", 0.01);
    }
    
    public double getAutoTurningI() {
        return tuning.getDouble("Auto Turning PID I", 1.0e-5);
    }
    
    public double getAutoTurningD() {
        return tuning.getDouble("Auto Turning PID D", 0);
    }
    
    public double getAutoTuningF() {
        return tuning.getDouble("Auto Turning PID F", 0);
    }
    
    public double getAutoDrivingLeftP() {
        return tuning.getDouble("Auto Driving Left PID P", 0.01);
    }
    
    public double getAutoDrivingLeftI() {
        return tuning.getDouble("Auto Driving Left PID I", 1.0e-5);
    }
    
    public double getAutoDrivingLeftD() {
        return tuning.getDouble("Auto Driving Left PID D", 0.01);
    }
    
    public double getAutoDrivingLeftF() {
        return tuning.getDouble("Auto Driving Left PID F", 0);
    }
    
    public double getAutoDrivingRightP() {
        return tuning.getDouble("Auto Driving Right PID P", 0.1);
    }
    
    public double getAutoDrivingRightI() {
        return tuning.getDouble("Auto Driving Right PID I", 1.0e-5);
    }
    
    public double getAutoDrivingRightD() {
        return tuning.getDouble("Auto Driving Right PID D", 0.01);
    }
    
    public double getAutoDrivingRightF() {
        return tuning.getDouble("Auto Driving Right PID F", 0);
    }
    
    public double getAutoTurningMarginOfError() {
        return tuning.getDouble("Auto Turning Margin Of Error", 10);
    }
    
    public double getAutoDrivingMarginOfError() {
        return tuning.getDouble("Auto Driving Margin Of Error", 10);
    }
    
    public double getAutoDrivingGearBackoffTime() {
        return tuning.getDouble("Auto Driving Gear Backoff Time", 1.0);
    }
    
    public double getAutoDrivingGearBackoffSet() {
        return tuning.getDouble("Auto Driving Gear Backoff Set", 0.3);
    }

    public double getRightDriveMultiplier() {
        return tuning.getDouble("Drive Right Multiplier", 1.0);
    }
    
    public double getLeftDriveMultiplier() {
        return tuning.getDouble("Drive Left Multiplier", 1.0);
    }
    
    ///////////////////////////////////////////////////////////////////////////////// gear mech
    
    public double getGearWristCurrentThreshold() {
        return tuning.getDouble("Gear Wrist Current Threshold", 8);
    }
    
    public double getGearRollerCurrentThreshold() {
        return tuning.getDouble("Gear Roller Current Threshold", 5);
    }
    
    public double getGearWristOutput() {
        return tuning.getDouble("Gear Wrist Output", 0.4);
    }
    
    public double getGearRollerOutput() {
        return tuning.getDouble("Gear Roller Output", 1.0);
    }
    
    public double getGearPlacementWristDownSecs() {
        return tuning.getDouble("Gear Placement Wrist Down Secs", 0.3);
    }
    
    public double getAutoGearPlacementSecs() {
        return tuning.getDouble("Auto Gear Placement Secs", 0.4);
    }
    
    public double getGearInitRollerSpinSecs() {
        return tuning.getDouble("Gear Init Roller Spin Secs", 0.5);
    }
    
}
