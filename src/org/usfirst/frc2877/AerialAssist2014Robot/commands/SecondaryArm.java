/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc2877.AerialAssist2014Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.AerialAssist2014Robot.Robot;
import org.usfirst.frc2877.AerialAssist2014Robot.subsystems.PickupArmSubsys;
import org.usfirst.frc2877.AerialAssist2014Robot.subsystems.SecondaryArmSubsys;

/**
 *
 * @author ligerbot
 */
public class SecondaryArm extends Command{
    
    // this class can do three different things, according to the value
    // of the enum below, as passed to the constructor
    static final public int OPEN = 1;
    static final public int CLOSE = 2;
    static final public int TOGGLE = 3;
    int m_action; // OPEN, CLOSE, or TOGGLE
    
    SecondaryArmSubsys secondarySubsys = Robot.secondary;
    //In execute(), each time it runs theTimer goes up by 1 until it reaches 
    //maxTime, at which point done becomes true and this command ends.
    int theTimer, maxTime;
    boolean done;

    public SecondaryArm(int action) {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.secondary);
        m_action = action;
        theTimer = 0;
        maxTime = 50;
        setInterruptible(false);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        done = false;
        switch(m_action)
        {
            case OPEN:
                secondarySubsys.openSecondary();
                break;
                
            case CLOSE:
                secondarySubsys.closeSecondary();
                break;
                
            case TOGGLE:
                if (Robot.secondaryIsOpen) secondarySubsys.closeSecondary();
                else secondarySubsys.openSecondary();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //Increment the timer, and check that its with the bounds 
        //of maximum time
        theTimer++;
        if (theTimer > maxTime) {
            done = true;
        }
    }

    protected boolean isFinished() {
        return done;
    }

    protected void end() {
        //pickupSubst.offSecondary();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
