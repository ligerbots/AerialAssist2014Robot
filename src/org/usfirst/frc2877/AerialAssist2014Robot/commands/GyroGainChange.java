/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.AerialAssist2014Robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.AerialAssist2014Robot.Robot;

/**
 *
 * @author Administrator
 */
public class GyroGainChange extends Command {
        private double m_amount;

        public GyroGainChange(double amount) 
        {
            // we're ignoring direction for now
            m_amount = amount;  // 0.5 or -0.5
         }
        
        protected void initialize() {
            Robot.AUTONOMOUS_DRIVE_GAIN += m_amount; 
            System.out.println("AUTONOMOUS_DRIVE_GAIN: " + Robot.AUTONOMOUS_DRIVE_GAIN);
        }

        // Called repeatedly when this Command is scheduled to run
        protected void execute() {
           
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
