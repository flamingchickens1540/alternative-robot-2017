package org.team1540.robot2017;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot2 extends IterativeRobot {

    private CANTalon[] talons = new CANTalon[18];
    
    SendableChooser<Integer> talonChooser;
    int talon;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        talonChooser = new SendableChooser<Integer>();
        talonChooser.addObject(RobotMap.climberTop + ": Climber Top", RobotMap.climberTop);
        talonChooser.addObject(RobotMap.climberBottom + ": Climber Bottom", RobotMap.climberBottom);
        talonChooser.addObject(RobotMap.climberNew + ": Climber New", RobotMap.climberNew);
        talonChooser.addObject(RobotMap.intakeRollers + ": Intake", RobotMap.intakeRollers);
        talonChooser.addObject(RobotMap.feederFunnelingRollerTop + ": Feeder Top", RobotMap.feederFunnelingRollerTop);
        talonChooser.addObject(RobotMap.feederFunnelingRollerRight + ": Feeder Right", RobotMap.feederFunnelingRollerRight);
        talonChooser.addObject(RobotMap.feederFunnelingRollerLeft + ": Feeder Left", RobotMap.feederFunnelingRollerLeft);
        talonChooser.addObject(RobotMap.feederBelt + ": Feeder Belt", RobotMap.feederBelt);
        talonChooser.addObject(RobotMap.shooterLeftFlywheel + ": Shooter Flywheel", RobotMap.shooterLeftFlywheel);
        talonChooser.addObject(RobotMap.driveTalonRightA + ": Drive Right Front", RobotMap.driveTalonRightA);
        talonChooser.addObject(RobotMap.driveTalonRightB + ": Drive Right Middle", RobotMap.driveTalonRightB);
        talonChooser.addObject(RobotMap.driveTalonRightC + ": Drive Right Back", RobotMap.driveTalonRightC);
        talonChooser.addObject(RobotMap.driveTalonLeftA + ": Drive Left Front", RobotMap.driveTalonLeftA);
        talonChooser.addObject(RobotMap.driveTalonLeftB + ": Drive Left Middle", RobotMap.driveTalonLeftB);
        talonChooser.addObject(RobotMap.driveTalonLeftC + ": Drive Left Back", RobotMap.driveTalonLeftC);
        talonChooser.addObject(RobotMap.gearRoller + ": Gear Roller", RobotMap.gearRoller);
        talonChooser.addObject(RobotMap.gearWrist + ": Gear Wrist", RobotMap.gearWrist);
        SmartDashboard.putData("Motor Chooser", talonChooser);
        
        OI.buttonSpinup.whenPressed(new Command() {
            @Override
            protected void initialize() {
                System.out.println(talonChooser.getSelected());
                
                if (talons[talonChooser.getSelected()] == null){
                    talons[talonChooser.getSelected()] = new CANTalon(talonChooser.getSelected());
                }
                talons[talonChooser.getSelected()].changeControlMode(TalonControlMode.PercentVbus);
                talons[talonChooser.getSelected()].set(-0.5);
            }
            
            @Override
            protected void execute() {
                SmartDashboard.putNumber("Output", talons[talonChooser.getSelected()].getOutputVoltage() / talons[talonChooser.getSelected()].getBusVoltage());
            }
            
            @Override
            protected void end() {
                talons[talonChooser.getSelected()].set(0);
            }
            
            @Override
            protected boolean isFinished() {
                return !OI.buttonSpinup.get();
            }
        });
    }

    @Override
    public void robotPeriodic() {
        Scheduler.getInstance().run();
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
        Scheduler.getInstance().run();
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
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
    }
}
