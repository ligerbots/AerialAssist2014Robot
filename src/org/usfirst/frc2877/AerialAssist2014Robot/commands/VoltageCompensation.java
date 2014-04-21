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
public class VoltageCompensation extends Command {
        private double m_voltage_factor;

        public VoltageCompensation(double voltage_factor) 
        {
            m_voltage_factor = voltage_factor;  // expected 0.1
         }
        
        protected void initialize() {
            Robot.VOLTS_PER_TICK += m_voltage_factor;
            System.out.println("VOLTS_PER_TICK: " + Robot.VOLTS_PER_TICK);
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

