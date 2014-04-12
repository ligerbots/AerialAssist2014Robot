/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc2877.AerialAssist2014Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.AerialAssist2014Robot.RobotMap;

/**
 *
 * @author cbf
 */
public class ToggleCompressor extends Command {
    
    boolean compressorstate;
    public ToggleCompressor() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
       
    }

    // Called just before this Command runs the first time
    protected void initialize() {

       
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
        compressorstate = RobotMap.pneumaticPusherPushCompressor.enabled();
        if (compressorstate) RobotMap.pneumaticPusherPushCompressor.stop();
        else RobotMap.pneumaticPusherPushCompressor.start();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
