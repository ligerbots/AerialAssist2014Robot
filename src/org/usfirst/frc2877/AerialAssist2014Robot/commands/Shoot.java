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
import org.usfirst.frc2877.AerialAssist2014Robot.subsystems.ShooterSubsys;

/**
 *
 */
public class Shoot extends Command {

    ShooterSubsys theSubst = Robot.shooter;
    //DoubleSolenoid theOtherPiston = RobotMap.shooterRightSolenoidShoot;
    int theTimer, maxTime = 25;
    boolean done;
    boolean blocked;

    public Shoot() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shooter);
        System.out.println("SHOOT constructor");
        setInterruptible(false);
        maxTime = 25;
        theTimer = 0;
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //MOVE OUTTA THE WAY IM LOBBIN DIS BALL HERE
        System.out.println("pickupIsOpen: " + Robot.pickupIsOpen + " secondaryIsOpen: " + Robot.secondaryIsOpen
                + " shooterIsPressurized: " + Robot.shooterIsPressurized);
        done = !(Robot.pickupIsOpen
                && Robot.secondaryIsOpen
                && Robot.shooterIsPressurized);
        if (!done) {
            System.out.println("Kick extending");
            theSubst.kickExtend();
            blocked = false;
            theTimer = 0;
        } else {
            blocked = true;
            System.out.println("Shooting blocked");            
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        theTimer++;
        if (theTimer > maxTime) {
            System.out.println("Shoot done.");
            done = true;
        }
    }

    //Ends the command when the timer has reached the end of its time
    protected boolean isFinished() {
        return done;
    }

    protected void end() {
        Robot.shooterIsPressurized = false;
        System.out.println("Kick retracting");
        if (!blocked) {
            theSubst.kickRetract();
            theSubst.shootRetract();
        }

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
