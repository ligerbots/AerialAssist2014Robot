/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc2877.AerialAssist2014Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.AerialAssist2014Robot.Robot;
import org.usfirst.frc2877.AerialAssist2014Robot.subsystems.Pickup;

/**
 *
 * @author ligerbot
 */
public class ClosePickup extends Command {
    Pickup pickupSubst = Robot.pickup;
    //In execute(), each time it runs theTimer goes up by 1 until it reaches 
    //maxTime, at which point done becomes true and this command ends.
    int theTimer = 0, maxTime = 100;
    boolean done = false;

    public ClosePickup() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.pickup);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    //The 1 and -1 may need to be swapped in the future. 
     if ((theTimer / 5) % 2 == 0) {
      pickupSubst.closePickup();
      } else {
          pickupSubst.offPickup();
        }
        //Increment the timer, and check that its with the bounds 
        //of maximum time
        theTimer++;
    }

    protected boolean isFinished() {
        return theTimer > 2 * maxTime;
    }

    protected void end() {
        pickupSubst.offPickup();
            pickupSubst.runRoller(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
    
}
