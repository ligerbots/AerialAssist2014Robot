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
import org.usfirst.frc2877.AerialAssist2014Robot.subsystems.Pickup;

/**
 *
 */
public class  ToggleRollerAndOpenArms extends Command {
//TODO Need to move toggleValue to Robot so it lives forever
    // This should really be a global armsOpen flag
    static boolean toggleValue = false;
     Pickup pickupSubst = Robot.pickup;
     
     //in execute(), each time it runs theTimer goes up by 1 until it reaches someValue,
     //at which point done becomes true and this command ends.
     int theTimer=0,someValue = 10;
     
     boolean done = false;
    public ToggleRollerAndOpenArms() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.pickup);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        toggleValue = !toggleValue;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       //this toggles it going forward or backward. Kind of obvious, but I'm commenting on it. In a comment.
        
       if(toggleValue){ //The 1 and -1 may need to be swapped in the future. 
           pickupSubst.runRoller(1);
           pickupSubst.openPickup();
        }else{
           pickupSubst.runRoller(0);
           pickupSubst.closePickup();
       }
        theTimer++;
        if(theTimer > someValue){
           done = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    
    
    
    
    
    // Called once after isFinished returns true
    protected void end() {
        pickupSubst.closePickup();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
