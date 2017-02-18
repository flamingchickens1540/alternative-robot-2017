
package org.team1540.robot2017;

import org.team1540.robot2017.commands.FireShooter;
import org.team1540.robot2017.commands.ResetGearSliderPosition;
import org.team1540.robot2017.commands.SpinupFlywheel;
import org.team1540.robot2017.commands.ToggleGearServos;
import org.team1540.robot2017.commands.TurnEverythingOff;
import org.team1540.robot2017.commands.TurnOnIntake;
import org.team1540.robot2017.subsystems.Belt;
import org.team1540.robot2017.subsystems.Climber;
import org.team1540.robot2017.subsystems.DriveTrain;
import org.team1540.robot2017.subsystems.Feeder;
import org.team1540.robot2017.subsystems.GearMechanism;
import org.team1540.robot2017.subsystems.Intake;
import org.team1540.robot2017.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    public static DriveTrain driveTrain;
    public static Climber climber;
    public static GearMechanism gearMechanism;
    public static Feeder feeder;
    public static Belt belt;
    public static Intake intake;
    public static Shooter shooter;
    public static Tuning tuning;
    public static OI oi;

    Command autonomousCommand;
    Command calibrateSlider;
    SendableChooser<Command> chooser = new SendableChooser<>();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        oi = new OI();
        tuning = new Tuning();
        driveTrain = new DriveTrain();
        climber = new Climber();
        gearMechanism = new GearMechanism();
        feeder = new Feeder();
        belt = new Belt();
        intake = new Intake();
        shooter = new Shooter();
    	
        calibrateSlider = new ResetGearSliderPosition();
        new JoystickButton(OI.copilot, 4).whenPressed(new ToggleGearServos());

        OI.buttonSpinup.whenPressed(new SpinupFlywheel());
        OI.buttonFire.whenPressed(new FireShooter());
        OI.buttonSpindown.whenPressed(new TurnEverythingOff());
        OI.buttonIntakeOn.whenPressed(new TurnOnIntake());
    }

    @Override
    public void robotPeriodic() {
        SmartDashboard.putBoolean("Right Limit", gearMechanism.getRightLimitSwitch());
        SmartDashboard.putBoolean("Left Limit", gearMechanism.getLeftLimitSwitch());
        SmartDashboard.putNumber("Slider Encoder", gearMechanism.getSliderEncoder());
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
        new TurnEverythingOff();
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Flywheel Speed", Robot.shooter.getSpeed());
        SmartDashboard.putNumber("Flywheel Setpoint", Robot.shooter.getSetpoint());
        SmartDashboard.putNumber("Flywheel Error", Robot.shooter.getClosedLoopError());
        SmartDashboard.putNumber("Flywheel Output", Robot.shooter.getMotorOutput());
        // SmartDashboard.putNumber("_Belt Period", tuning.getBeltPeriod());
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
        new TurnEverythingOff();
        autonomousCommand = chooser.getSelected();

        /*
         * String autoSelected = SmartDashboard.getString("Auto Selector",
         * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
         * = new MyAutoCommand(); break; case "Default Auto": default:
         * autonomousCommand = new ExampleCommand(); break; }
         */

        // schedule the autonomous command (example)
        if (autonomousCommand != null)
            autonomousCommand.start();
        if (calibrateSlider != null)
            calibrateSlider.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
            autonomousCommand.cancel();

        if (calibrateSlider != null)
            calibrateSlider.start();

        new TurnEverythingOff();
        shooter.setPID(tuning.getFlywheelP(), tuning.getFlywheelI(), tuning.getFlywheelD());
        shooter.setF(tuning.getFlywheelF());
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Flywheel Speed", Robot.shooter.getSpeed());
        // SmartDashboard.putNumber("Flywheel Setpoint",
        // Robot.shooter.getSetpoint());
        SmartDashboard.putNumber("Flywheel Error", Robot.shooter.getClosedLoopError());
        SmartDashboard.putNumber("Flywheel Output", Robot.shooter.getMotorOutput());
        SmartDashboard.putNumber("Flywheel PID", Robot.shooter.getPIDOutput());
        // SmartDashboard.putNumber("_Belt Period", tuning.getBeltPeriod());
        SmartDashboard.putNumber("Flywheel Current Left", Robot.shooter.getFlywheelCurrentL());
        SmartDashboard.putNumber("Flywheel Current Right", Robot.shooter.getFlywheelCurrentR());
        SmartDashboard.putNumber("Flywheel PID P", Robot.shooter.getP());
        SmartDashboard.putNumber("Flywheel PID I", Robot.shooter.getI());
        SmartDashboard.putNumber("Flywheel PID D", Robot.shooter.getD());
        SmartDashboard.putNumber("Flywheel PID F", Robot.shooter.getF());
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }
}
