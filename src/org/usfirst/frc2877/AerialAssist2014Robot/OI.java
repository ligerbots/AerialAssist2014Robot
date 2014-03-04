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

import org.usfirst.frc2877.AerialAssist2014Robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton shootButton;
    public JoystickButton shootPressureButton;
    public JoystickButton togglePickupButton;
    public JoystickButton toggleSecondaryButton;
    public JoystickButton rollerButton;
    public JoystickButton turnRightButton;
    public JoystickButton turnLeftButton;
    public JoystickButton toggleDriveShooterMode;    
    public Joystick joystick;
    public Joystick joystick2;  // for changing parameters only
    public TogglePickupArm togglePickup = new TogglePickupArm();
    public ToggleSecondaryArm toggleSecondary = new ToggleSecondaryArm();
    public Turn90 TurnRight = new Turn90(90);
    public Turn90 TurnLeft = new Turn90(-90);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        joystick = new Joystick(1);
 
        //toggleDriveShooterMode = new JoystickButton(joystick, 2);
        //toggleDriveShooterMode.whenPressed(new DriveShooterModeToggle());
        togglePickupButton = new JoystickButton(joystick, 6);
        togglePickupButton.whenPressed(togglePickup);
        toggleSecondaryButton = new JoystickButton(joystick, 5);
        toggleSecondaryButton.whenPressed(toggleSecondary);
        turnRightButton = new JoystickButton(joystick, 3);    // XBox "B" button
        turnRightButton.whenPressed(TurnRight);
        turnLeftButton = new JoystickButton(joystick, 2);    // XBox "X" button
        turnLeftButton.whenPressed(TurnLeft);
        
        shootButton = new JoystickButton(joystick, 1);
        shootButton.whenPressed(new Shoot());
        shootPressureButton = new JoystickButton(joystick, 8);
        shootPressureButton.whenPressed(new PressurizeShooter());
        
        //turn90RightButton = new JoystickButton(joystick, 14);
        //turn90RightButton.whenPressed(new Turn90Right());
        //turn90LeftButton = new JoystickButton(joystick, 13);
        //turn90LeftButton.whenPressed(new Turn90Left());
        joystick2 = new Joystick(2);
        JoystickButton overShootUpPlus = new JoystickButton(joystick2, 11);
        JoystickButton overShootUpMinus = new JoystickButton(joystick2, 10);
        
        overShootUpPlus.whenPressed(new OvershootChange(1.0,1.0));
        overShootUpMinus.whenPressed(new OvershootChange(1.0,-1.0));

        // SmartDashboard Buttons
//        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
//        SmartDashboard.putData("Drive", new Drive());
//        SmartDashboard.putData("Turn90Left", new Turn90Left());
//        SmartDashboard.putData("Turn90Right", new Turn90Right());
//        SmartDashboard.putData("Shoot", new Shoot());
//        SmartDashboard.putData("Lob", new Shoot());
//        SmartDashboard.putData("CameraTurn", new DriveShooterModeToggle());
//        SmartDashboard.putData("Turn1080DegreesLulz", new Turn1080DegreesLulz());
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getJoystick() {
        return joystick;
    }

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}
