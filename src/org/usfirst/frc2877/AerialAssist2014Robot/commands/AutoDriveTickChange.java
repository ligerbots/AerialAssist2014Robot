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
public class AutoDriveTickChange extends Command {
        private int m_ticks;

        public AutoDriveTickChange(int ticks) 
        {
            // we're ignoring direction for now
            m_ticks = ticks;  // 0.5 or -0.5
         }
        
        protected void initialize() {
            Robot.AUTONOMOUS_DRIVE_TICKS += m_ticks;
            System.out.println("AUTONOMOUS_DRIVE_TICKS: " + Robot.AUTONOMOUS_DRIVE_TICKS);
            Robot.robot.writeOvershoot();
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

