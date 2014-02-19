/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.AerialAssist2014Robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc2877.AerialAssist2014Robot.Robot;
import org.usfirst.frc2877.AerialAssist2014Robot.RobotMap;

/**
 *
 * @author ligerbot
 */
public class SecondaryArmSubsys extends Subsystem {

    DoubleSolenoid secondarySolenoid = RobotMap.pickupSecondarySolenoid;

    protected void initDefaultCommand() {
    }

    //Open the catch - brings out the secondary arm
    public void openSecondary() {
        secondarySolenoid.set(DoubleSolenoid.Value.kForward);
        Robot.currentMoles -= Robot.molesOfAir(Robot.SECONDARY_VOLUME);
        Robot.secondaryIsOpen = true;
        SmartDashboard.putString("Catch", "Open");
    }

    //Close the catch - brings in the secondary arm
    public void closeSecondary() {
        secondarySolenoid.set(DoubleSolenoid.Value.kReverse);
        Robot.currentMoles -= Robot.molesOfAir(Robot.SECONDARY_VOLUME);
        Robot.secondaryIsOpen = false;
        SmartDashboard.putString("Catch", "Close");
    }

    public void offSecondary() {
        secondarySolenoid.set(DoubleSolenoid.Value.kOff);
    }
}
