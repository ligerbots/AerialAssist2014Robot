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
public class Turn90Right extends Command {

    public Turn90Right() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

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
        double gyroDrivePass = (gyroAngle - 90.0) / 90.0;
        double minConstant = 0.4;
        if (Math.abs(gyroDrivePass) > 1.0) {
            if (gyroDrivePass > 1.0) {
                gyroDrivePass = 1.0;
            } else if (gyroDrivePass < -1.0) {
                gyroDrivePass = -1.0;
            }
        }
        if (Math.abs(gyroDrivePass) < minConstant) {
            if (gyroAngle > -90.0) {
                gyroDrivePass = -minConstant;
            } else if (gyroAngle < -90.0) {
                gyroDrivePass = minConstant;
            }
           // I could have made minConstant negative, but then I'd also have to
            // do Math.abs(gyroDrivePass) < -minConstant. This seemed simpler.
        }
        Robot.driveTrain.drive(gyroDrivePass, 0);
       // As far as we can tell, using PID to do these 90 turns is unnecessary.
        // However, if we can figure out how to do that eventually, it might be
        // smoother.
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double gyroAngle = Robot.driveTrain.getCurrentAngle();
        boolean isFinished = (gyroAngle < -89.0 && gyroAngle > -91.0);
        if (isFinished) {
            System.out.println("Turn90Right Finished");
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
