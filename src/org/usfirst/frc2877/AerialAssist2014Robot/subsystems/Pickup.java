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
import edu.wpi.first.wpilibj.can.*;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Pickup extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    CANJaguar rollerJag = RobotMap.pickupRollerJag;
    DoubleSolenoid pickupSolenoid = RobotMap.pickupPickupSolenoid;
    DoubleSolenoid catchSolenoid = RobotMap.pickupCatchSolenoid;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void runRoller(int state) {
        try {
            rollerJag.setX(state);
        } catch (edu.wpi.first.wpilibj.can.CANTimeoutException ex) {
            System.out.println("Timeout Exception on ");
        }
        
    }
}

