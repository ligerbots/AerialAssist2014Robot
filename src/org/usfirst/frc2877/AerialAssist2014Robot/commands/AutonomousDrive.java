/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.AerialAssist2014Robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.AerialAssist2014Robot.Robot;
import org.usfirst.frc2877.AerialAssist2014Robot.RobotMap;

/**
 *
 * @author 1675015
 */
public class AutonomousDrive extends Command {

    int m_time = 0;
    boolean m_done = false;
    int m_finish;
    double m_gyro, m_gyro_start;

    public AutonomousDrive(int ticks) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.driveTrain);
        setInterruptible(false);
        double voltage = DriverStation.getInstance().getBatteryVoltage();
        double adjust = (12.0 - voltage)/(Robot.VOLTS_PER_TICK);
        int newticks = ticks + (int)adjust;
        m_finish = newticks;
    }

    // Called just before this Command runs the first m_time
    protected void initialize() {
        System.out.println("Initialize Drive");
        m_gyro_start = Robot.driveTrain.getCurrentAngle();
        m_done = false;
        m_time = 0;
        Delay A; 
        A = new Delay(0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        m_time++;
        m_gyro = Robot.driveTrain.getCurrentAngle() - m_gyro_start;
        Robot.driveTrain.drive((-m_gyro*Robot.AUTONOMOUS_DRIVE_GAIN)/100.0, 0.7);
        if (m_time > m_finish) {
            m_done = true;
        }
        if (m_time % 3 == 0)
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
