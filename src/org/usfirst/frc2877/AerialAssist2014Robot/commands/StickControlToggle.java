/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.AerialAssist2014Robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc2877.AerialAssist2014Robot.Robot;

/**
 *
 * @author Administrator
 */
public class StickControlToggle extends Command 
{

        public StickControlToggle() 
        {
        }
        
        protected void initialize() 
        {
            Robot.StickControlSingle = !Robot.StickControlSingle;
            Robot.robot.writeOvershoot();
            SmartDashboard.putBoolean("Stick Control Single", Robot.StickControlSingle);
        }

        // Called repeatedly when this Command is scheduled to run
        protected void execute() 
        {

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
            end();
        }
        
    }

