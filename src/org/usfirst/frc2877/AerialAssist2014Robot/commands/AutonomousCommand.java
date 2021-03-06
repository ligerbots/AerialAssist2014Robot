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

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc2877.AerialAssist2014Robot.Robot;
import org.usfirst.frc2877.AerialAssist2014Robot.RobotMap;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand() {
        // We only have 10 seconds and we want to make sure the shooter is pressurized
        addParallel(new PressurizeShooter());
        // Drive straight
        addSequential(new AutonomousDrive(Robot.AUTONOMOUS_DRIVE_TICKS));
        // Wait for the motors to stop before attempting the turn
        addSequential(new Delay(0.5));
        // In case the robot turns while driving or while stopping,
        // recompute how far we need to turn to get to -90 degrees from the
        // start angle that was read just before this command group executes.
        System.out.println("About to Turn 90");
        addSequential(new Turn90(-90.0 - Robot.GYRO_START_ANGLE));
        // Open up the pickup and secondary arms in preparation for shooting
        System.out.println("About to Open Primary");
        addParallel(new TogglePickupArm());
        System.out.println("About to Open Secondary");
        addParallel(new ToggleSecondaryArm());
        // Wait until both arms are extended
        addSequential(new Delay(2.0));
        // By now, all conditions should be met for shooting, so shoot
        System.out.println("About to shoot in Autonomous");
        addSequential(new Shoot());
        System.out.println("Autonomous Done");
    }
}
