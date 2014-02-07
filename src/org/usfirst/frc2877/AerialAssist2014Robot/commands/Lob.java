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
import org.usfirst.frc2877.AerialAssist2014Robot.subsystems.Shooter;

/**
 *
 */
public class  Lob extends Command {

    Shooter theSubst = Robot.shooter;
    Pickup thePickup = Robot.pickup;
    //DoubleSolenoid theOtherPiston = RobotMap.shooterRightSolenoidShoot;
    int theTimer = 0,someValue=10;
    public Lob() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shooter);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //MOVE OUTTA THE WAY IM LOBBIN DIS BALL HERE
        thePickup.openWide();
        //TODO need to wait until arms are open before shooting
        theSubst.leftDeactivate();
        theSubst.KickActivate();
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //Use 1 piston
        theTimer++;
        if(theTimer > someValue){
           theSubst.unKick();
           
        }
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        
        //this is true when we're done, just like in our execute().
        return theTimer > someValue+1;
    }

    // Called once after isFinished returns true
    protected void end() {
        theSubst.unKick();
        theSubst.dualDeactivate();
        theSubst.dualActivate();
        
        //ok, thanks for moving so I could shoot. NOW GET BACK IN MY ROBOT
        thePickup.shutUp();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
