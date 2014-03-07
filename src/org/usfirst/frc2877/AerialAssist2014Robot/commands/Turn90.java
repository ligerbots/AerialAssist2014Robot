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
    static double lowSpeed = 0.7;
    static double fullSpeed = 0.8;
    static int maxTime = 60;

    public Turn90(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        m_targetAngle = angle;
        System.out.println("Turn started for " + m_targetAngle + " degrees.");
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        m_leftTime = 0;
        RobotMap.driveTrainGyro.reset();
        System.out.println("Turn started for " + m_targetAngle + " degrees.");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double gyroAngle = Robot.driveTrain.getCurrentAngle();
        double gyroDriveSpeed;
        
        if (m_targetAngle > 0)
        {
            gyroDriveSpeed = (gyroAngle < coarseTurn) ? fullSpeed : lowSpeed;
        }
        else
        {
            // e.g. gryo is -60, we're full speed until gyro is -75
            gyroDriveSpeed = (gyroAngle > -coarseTurn) ? -fullSpeed : -lowSpeed;
        }
        
        Robot.driveTrain.drive(gyroDriveSpeed, 0);
        m_leftTime++;
        if (m_leftTime % 5 == 0)
        {
            System.out.println("Gyro angle:" + gyroAngle + "Turnspeed" + gyroDriveSpeed);
        }
    }


       // As far as we can tell, using PID to do these 90 turns is unnecessary.
    // However, if we can figure out how to do that eventually, it might be
    // smoother.


// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double gyroAngle = Robot.driveTrain.getCurrentAngle();
        boolean isFinished;
        if (m_targetAngle > 0)
        {
          isFinished = gyroAngle + Robot.OVERSHOOT_ANGLE_POSITIVE > m_targetAngle;
        }
        else
        {
            // e.g. if (-60 - 15) > -90 we keep turning
            isFinished = gyroAngle - Robot.OVERSHOOT_ANGLE_NEGATIVE > m_targetAngle;
        }
        
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
        Robot.drive.start();        // must restart driving!!
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
