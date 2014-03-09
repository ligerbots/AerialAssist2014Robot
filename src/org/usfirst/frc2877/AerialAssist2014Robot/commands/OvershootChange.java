/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.AerialAssist2014Robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.AerialAssist2014Robot.Robot;
import java.io.DataOutputStream;
import com.sun.squawk.microedition.io.FileConnection;
import java.io.DataInputStream;
import javax.microedition.io.Connector;

/**
 *
 * @author Administrator
 */
public class OvershootChange extends Command {
        private double m_direction;
        private double m_value;
        private int m_sign;

        public OvershootChange(int sign, double direction, double value) 
        {
            // we're ignoring direction for now
            m_sign = sign;  // +1 or -1
            m_direction = direction;
            m_value = value;
         }
        
        protected void initialize() {
            if (m_sign> 0)
            {
                Robot.OVERSHOOT_ANGLE_POSITIVE += m_value; 
            }
            else
            {
                Robot.OVERSHOOT_ANGLE_NEGATIVE += m_value;
            }

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

