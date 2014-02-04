// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package org.usfirst.frc2877.AerialAssist2014Robot;
    
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.*;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import java.util.Vector;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static CANJaguar driveTrainLeftFrontJag;
    public static CANJaguar driveTrainLeftRearJag;
    public static CANJaguar driveTrainRightFrontJag;
    public static CANJaguar driveTrainRightRearJag;
    public static RobotDrive driveTrainRobotDrive41;
    public static Gyro driveTrainGyro;
    public static DoubleSolenoid shooterLeftSolenoidShoot;
    public static DoubleSolenoid shooterRightSolenoidShoot;
    public static DoubleSolenoid shooterKickerSolenoid;
    public static CANJaguar pickupRollerJag;
    public static DoubleSolenoid pickupPickupSolenoid;
    public static DoubleSolenoid pickupCatchSolenoid;
    public static Servo servoSubstServo;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Compressor pneumaticPusherPushCompressor;
    
    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        try { 
            driveTrainLeftFrontJag = new CANJaguar(2);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
	
        
        try { 
            driveTrainLeftRearJag = new CANJaguar(3);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
	
        
        try { 
            driveTrainRightFrontJag = new CANJaguar(5);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
	
        
        try { 
            driveTrainRightRearJag = new CANJaguar(6);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
	
        
        driveTrainRobotDrive41 = new RobotDrive(driveTrainLeftFrontJag, driveTrainLeftRearJag,
              driveTrainRightFrontJag, driveTrainRightRearJag);
	
        driveTrainRobotDrive41.setSafetyEnabled(false);
        driveTrainRobotDrive41.setExpiration(0.1);
        driveTrainRobotDrive41.setSensitivity(0.5);
        driveTrainRobotDrive41.setMaxOutput(1.0);

        driveTrainRobotDrive41.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        driveTrainRobotDrive41.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        driveTrainGyro = new Gyro(1, 1);
	LiveWindow.addSensor("DriveTrain", "Gyro", driveTrainGyro);
        driveTrainGyro.setSensitivity(0.007);
        shooterLeftSolenoidShoot = new DoubleSolenoid(1, 1, 2);      
	
        
        shooterRightSolenoidShoot = new DoubleSolenoid(1, 3, 4);      
	
        
        shooterKickerSolenoid = new DoubleSolenoid(1, 5, 6);      
	
        
        try { 
            pickupRollerJag = new CANJaguar(4);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
	
        
        pickupPickupSolenoid = new DoubleSolenoid(1, 7, 8);      
	
        
        pickupCatchSolenoid = new DoubleSolenoid(2, 1, 2);      
	
        
        servoSubstServo = new Servo(1, 2);
        pneumaticPusherPushCompressor = new Compressor(1, 1, 1, 1);
        pneumaticPusherPushCompressor.start();
	LiveWindow.addActuator("ServoSubst", "Servo", servoSubstServo);
        
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}