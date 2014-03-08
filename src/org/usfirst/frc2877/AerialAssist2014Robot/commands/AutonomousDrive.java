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

    int time = 0;
    boolean done = false;
    int m_finish;
    double m_gyro;

    public AutonomousDrive(int finish) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.driveTrain);
        m_finish = finish;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Initialize Drive");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        time++;
        m_gyro = Robot.driveTrain.getCurrentAngle();
        Robot.driveTrain.drive(m_gyro/Robot.AUTONOMOUS_DRIVE_GAIN, -0.7);
        if (time > m_finish) {
            done = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
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
