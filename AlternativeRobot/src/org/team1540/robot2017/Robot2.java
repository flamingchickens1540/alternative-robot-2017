package org.team1540.robot2017;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot2 extends IterativeRobot {

    private CANTalon[] talons = new CANTalon[16];

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        for (int i = 0; i < talons.length; ++i) {
            talons[i] = new CANTalon(i + 1);
        }
    }

    @Override
    public void robotPeriodic() {
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        int TO_TEST = 13;

        if (OI.driver.getRawButton(1)) {
            talons[TO_TEST - 1].changeControlMode(TalonControlMode.PercentVbus);
            talons[TO_TEST - 1].set(0.7);
            SmartDashboard.putBoolean("running", true);
        } else {
            talons[TO_TEST - 1].changeControlMode(TalonControlMode.PercentVbus);
            talons[TO_TEST - 1].set(0.0);
            SmartDashboard.putBoolean("running", false);
        }
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
    }
}
