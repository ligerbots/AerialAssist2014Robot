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
        addSequential(new AutonomousDrive(100));
        addSequential(new Turn90Left());
        addSequential(new Shoot());
        
    }

}
