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

        public OvershootChange(double direction, double value) 
        {
            // we're ignoring direction for now
            m_direction = direction;
            m_value = value;
         }
        
        protected void initialize() {
            Robot.OVERSHOOT_ANGLE += m_value; 

            try {
                // Persist our overshoot numbers to a file
                DataOutputStream file;
                FileConnection fc;
                fc = (FileConnection)Connector.open(Robot.OVERSHOOT_FILE, Connector.WRITE);
                System.out.println("Saving " + Robot.OVERSHOOT_FILE);
                fc.create();
                file = fc.openDataOutputStream();
                file.writeDouble(Robot.OVERSHOOT_ANGLE);
                System.out.println(Robot.OVERSHOOT_ANGLE);
                file.flush();
                file.close();
                fc.close();
            }
            catch (Exception ex) {
                System.out.println("File output error: " + ex.getMessage());
            }
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

