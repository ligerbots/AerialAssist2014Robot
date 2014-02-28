// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc2877.AerialAssist2014Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.AerialAssist2014Robot.Robot;
import org.usfirst.frc2877.AerialAssist2014Robot.RobotMap;


/**
 *
 */
public class Turn90 extends Command {
    int m_leftTime = 0; //150 = 50 (ticks/second) * 3 (seconds)
    double m_targetAngle = 90; //gets overridden
    static double coarseTurn = 75.0;
    static double lowSpeed = 0.4;
    static double fullSpeed = 1.0;
    static int maxTime = 150;

    public Turn90(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        m_leftTime = 0;
        m_targetAngle = angle;
        System.out.println("Turn started for " + m_targetAngle + " degrees.");
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        RobotMap.driveTrainGyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double gyroAngle = Robot.driveTrain.getCurrentAngle();
        double gyroDriveSpeed;
        
        int sign = m_targetAngle<0?-1:1;
        
        if (Math.abs(m_targetAngle - gyroAngle) > coarseTurn) {
                gyroDriveSpeed = fullSpeed*sign;
        } else {
            gyroDriveSpeed = lowSpeed*sign;
        }
        Robot.driveTrain.drive(0,gyroDriveSpeed);
        m_leftTime++;
    }


       // As far as we can tell, using PID to do these 90 turns is unnecessary.
    // However, if we can figure out how to do that eventually, it might be
    // smoother.


// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double gyroAngle = Robot.driveTrain.getCurrentAngle();
        boolean isFinished = (Math.abs(gyroAngle) + Robot.OVERSHOOT_ANGLE) > Math.abs(m_targetAngle);
        if (isFinished) {
            System.out.println("Turn90 Finished");
        } 
        else if (m_leftTime >= maxTime)
        {
            isFinished = true;
            System.out.println("************** Turn90 TIMED OUT ************");
        }
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
