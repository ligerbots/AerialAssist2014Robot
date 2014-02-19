/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc2877.AerialAssist2014Robot.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc2877.AerialAssist2014Robot.RobotMap;

/**
 *
 * @author ligerbot
 */
public class RollerSubsys extends Subsystem {

    CANJaguar rollerJaguar = RobotMap.pickupRollerJag;
    
    protected void initDefaultCommand() {
    }
    
    public void runRoller(int x){
        try {
            rollerJaguar.setX(x);
        } catch (CANTimeoutException ex) {
            
        }       
    }
}
