/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.AerialAssist2014Robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc2877.AerialAssist2014Robot.Robot;

/**
 *
 * @author Shiv
 */
public class PressureMonitorCommand extends Command {
    
    public PressureMonitorCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        int maxVolume = Robot.NUMBER_TANKS * Robot.TANK_VOLUME;
        int pressure = Robot.pressureMax * Robot.totalVolume / maxVolume;
        SmartDashboard.putNumber("PSIMonit", pressure);
        SmartDashboard.putNumber("PSIMonitNum", pressure);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
