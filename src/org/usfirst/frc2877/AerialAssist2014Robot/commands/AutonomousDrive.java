/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.AerialAssist2014Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.AerialAssist2014Robot.Robot;

/**
 *
 * @author 1675015
 */
public class AutonomousDrive extends Command {

    int m_time = 0;
    boolean m_done = false;
    int m_finish;
    double m_gyro;

    public AutonomousDrive(int finish) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.driveTrain);
        m_finish = finish;
    }

    // Called just before this Command runs the first m_time
    protected void initialize() {
        System.out.println("Initialize Drive");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        m_time++;
        m_gyro = Robot.driveTrain.getCurrentAngle();
        Robot.driveTrain.drive((-m_gyro*Robot.AUTONOMOUS_DRIVE_GAIN)/100.0, -0.7);
        if (m_time > m_finish) {
            m_done = true;
        }
        if (m_gyro > 7.5 || m_gyro < -7.5)
        {
            m_done = true;
            System.out.println("Gyro angle: " + m_gyro + " ***** STOP DUE LIMIT EXCEEDED ***");
        }
        if (m_time % 5 == 0)
        {
            System.out.println("Gyro angle: " + m_gyro);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return m_done;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
