/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc2877.AerialAssist2014Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.AerialAssist2014Robot.Robot;
import org.usfirst.frc2877.AerialAssist2014Robot.subsystems.ShooterSubsys;

/**
 *
 * @author ligerbot
 */
public class PressurizeShooter extends Command {

    ShooterSubsys shootSubsys = Robot.shooter;
    int theTimer;
    static int maxTime = 50;
    
    public PressurizeShooter() {
        requires(Robot.shooter);
    }
    
    protected void initialize() {
        Robot.shooterIsPressurized = false;
        theTimer  = 0;
        shootSubsys.shootExtend();
    }

    protected void execute() {

        theTimer++;
        if (theTimer > maxTime) {
            Robot.shooterIsPressurized = true;
            System.out.println("Pressurized done.");
        }
    }

    protected boolean isFinished() {
        return Robot.shooterIsPressurized;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
