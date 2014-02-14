// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc2877.AerialAssist2014Robot.subsystems;

import org.usfirst.frc2877.AerialAssist2014Robot.RobotMap;
import org.usfirst.frc2877.AerialAssist2014Robot.commands.*;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc2877.AerialAssist2014Robot.Robot;

/**
 *
 */
public class Shooter extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    DoubleSolenoid shootSolenoid = RobotMap.shooterSolenoidShoot;
    DoubleSolenoid kickerSolenoid = RobotMap.shooterKickerSolenoid;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void rightActivate() {
        shootSolenoid.set(DoubleSolenoid.Value.kForward);
        Robot.currentMoles -= Robot.molesOfAir(Robot.SHOOTER_CYLINDER_VOLUME);
    }

    public void KickActivate() {
        kickerSolenoid.set(DoubleSolenoid.Value.kForward);
        Robot.currentMoles -= Robot.molesOfAir(Robot.TRIGGER_VOLUME);
    }

    public void rightDeactivate() {
        shootSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void unKick() {
        //kick back... take a seat...
        //Undoes kickActivate().
        kickerSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    //Shortcuts for functions.
}
