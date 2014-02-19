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
import org.usfirst.frc2877.AerialAssist2014Robot.subsystems.PickupArmSubsys;

/**
 *
 */
public class TogglePickupArm extends Command {

    static boolean toggleValue = false;
    PickupArmSubsys pickupSubst = Robot.pickup;
    //In execute(), each time it runs theTimer goes up by 1 until it reaches 
    //maxTime, at which point done becomes true and this command ends.
    int theTimer = 0, maxTime = 125;
    boolean done = false;

    public TogglePickupArm() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.pickup);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        toggleValue = !toggleValue;
        if (toggleValue) {
            pickupSubst.openPickup();
        } else if (!toggleValue) {
            pickupSubst.closePickup();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //Increment the timer, and check that its with the bounds 
        //of maximum time
        theTimer++;
        if (theTimer > maxTime) {
            done = true;
        }
    }

    protected boolean isFinished() {
        return done;
    }

    protected void end() {
        //pickupSubst.offPickup();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}