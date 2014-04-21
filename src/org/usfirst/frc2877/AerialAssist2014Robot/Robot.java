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


import com.sun.squawk.microedition.io.FileConnection;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.io.Connector;
import org.usfirst.frc2877.AerialAssist2014Robot.commands.*;
import org.usfirst.frc2877.AerialAssist2014Robot.subsystems.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static ShooterSubsys shooter;
    public static PickupArmSubsys pickup;
    public static RollerSubsys rollerSubsys;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Drive drive;
    public static SecondaryArmSubsys secondary;
    public static Roller roller;
    public static SmashboardCommand smashboardCommand;
    public static boolean pickupIsOpen = false;
    public static boolean secondaryIsOpen = false;
    public static boolean shooterIsPressurized = false;
    // Shooter cylinder is 1.5" bore with 12" throw.
    public static final double SHOOTER_CYLINDER_VOLUME = 21.2;
    public static final double SHOOTER_TANKS_VOLUME = 15;
    // Trigger cylinder is 1.5" bore with 2" throw.
    public static final double TRIGGER_VOLUME = 3.53;
    // Secondary Arm cylinder is 0.75" bore with 6" throw.
    public static final double SECONDARY_VOLUME = 2.65;
    // Pickup Arm cylinder is 0.75" bore with 8" throw.
    public static final double PICKUP_VOLUME = 3.53;
    public static final int NUMBER_TANKS = 11;
    public static final int TANK_VOLUME = 44;
    public static final int PRESSURE_MAX = 120;
    public static final int MAX_VOLUME = NUMBER_TANKS * TANK_VOLUME;
    public static final double MAX_MOLES = molesOfAir(MAX_VOLUME);
    public static double currentMoles;
    public static double currentPressure;
    
    // This is the section for adjustable, persisted parameters
    public static final String OVERSHOOT_FILE = "file:///2014adjustments.bin";
    public static double OVERSHOOT_ANGLE_NEGATIVE = 5.0;
    public static double OVERSHOOT_ANGLE_POSITIVE = 5.0;
    public static double AUTONOMOUS_DRIVE_GAIN = 4.0;
    public static int AUTONOMOUS_DRIVE_TICKS = 55;
    public static double VOLTS_PER_TICK = 0.20;
    public static boolean StickControlSingle = true;
    
    // Need this to be able to provide the starting gyro angle to the
    // autonomous command. Note that it should be zero at startup, but in
    // case we go back and forth between Autonomous and Teleop for practice,
    // we need to check it each time.
    public static double GYRO_START_ANGLE = 0.0;
    
    public int ticks = 0;
    public static double interruptPri = 0.02;
    // This is tricky. We have a static to hold a pointer to the presumed
    // single instance of the robot class:
    public static Robot robot;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        RobotMap.init();
        robot = this;
        readOvershoot();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        shooter = new ShooterSubsys();
        pickup = new PickupArmSubsys();
        rollerSubsys = new RollerSubsys();
        drive = new Drive();
        secondary = new SecondaryArmSubsys();
        roller = new Roller();
        smashboardCommand = new SmashboardCommand();
        RobotMap.driveTrainGyro.reset();

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        currentMoles = MAX_MOLES;
//        pneumaticCompressorTemporary = new PneumaticCompressorTemporary(20);
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autonomousCommand = new AutonomousCommand();
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }

    public static double molesOfAir(double volume) {
        double atm = 60.0 / 14.6959488;
        double liters = volume * 0.0163871;
        double moles = (atm * volume) / (0.0821 * 298);
        return moles;
    }
    
    public static double getPressure() {
        // returns current pressure in PSI
        double pressure = Robot.PRESSURE_MAX
                * Robot.currentMoles / Robot.MAX_MOLES;
        return pressure;
    }
    
    public static double compressorVolumeSinceLastTick() {
        // From the data sheet, the linear approximation to the air
        // volume in cubic inches per second as a function of current
        // pressure in PSI is
        // airFlow = 22.765-0.171*psi
        final double airFlowIntercept = 22.765;
        final double airFlowSlope = -0.171;
        double airFlow = airFlowIntercept + airFlowSlope * getPressure();
        double cv = airFlow * interruptPri;
        return cv;
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) {
            // Issue arm closing commands regardless of initial state
            // so our code KNOWS it's closed.
            RobotMap.pneumaticPusherPushCompressor.stop();
            pickup.closePickup();
            secondary.closeSecondary();
            // Read the current gyro angle and save it in the static variable
            // so it can be used by the autonomousCommand
            GYRO_START_ANGLE = driveTrain.getCurrentAngle();
            autonomousCommand.start();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
        // if the compressor is running, add air
        if (!RobotMap.pneumaticPusherPushCompressor.getPressureSwitchValue()) {
            Robot.currentMoles += Robot.molesOfAir(Robot.compressorVolumeSinceLastTick());
        }
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        System.out.println("teleopInit");
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
        drive.start();
        smashboardCommand.start();
        roller.start();
        
        RobotMap.pneumaticPusherPushCompressor.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
        // if the compressor is running, add air
        if (!RobotMap.pneumaticPusherPushCompressor.getPressureSwitchValue()) {
            Robot.currentMoles += Robot.molesOfAir(Robot.compressorVolumeSinceLastTick());
        }
    }

    /**
     * This function called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    public boolean readOvershoot() {
        try {
            // Persist our overshoot numbers to a file
            DataInputStream file;
            FileConnection fc;

            fc = (FileConnection)Connector.open(OVERSHOOT_FILE, Connector.READ);
            if (fc.exists()) {
                file = fc.openDataInputStream();
                System.out.print("Loading " + OVERSHOOT_FILE + ", +angle: ");
                OVERSHOOT_ANGLE_POSITIVE = file.readDouble(); // override the default + angle with this angle
                OVERSHOOT_ANGLE_NEGATIVE = file.readDouble(); // override the default - angle with this angle
                AUTONOMOUS_DRIVE_GAIN = file.readDouble();    // override the gyro Drive gain
                AUTONOMOUS_DRIVE_TICKS = file.readInt();      // override the autonomous drive tick time
                StickControlSingle = file.readBoolean();      // override the joystick drive control
                VOLTS_PER_TICK = file.readDouble();
                System.out.println(OVERSHOOT_ANGLE_POSITIVE + " -angle: " + OVERSHOOT_ANGLE_NEGATIVE);
                System.out.println("AUTONOMOUS_DRIVE_GAIN: " + AUTONOMOUS_DRIVE_GAIN);
                System.out.println("Stick_Control: " + StickControlSingle);
                System.out.println("VOLTS_PER_TICK: " + VOLTS_PER_TICK);
                file.close();
                fc.close();
            }
            else { System.out.println("No " + OVERSHOOT_FILE); }
        }
        catch (Exception ex) {
            System.out.println("File output error: " + ex.getMessage());
        }


        return true;
    }
    
    public void writeOvershoot() {
            
        try {
            // Persist our overshoot numbers to a file
            DataOutputStream file;
            FileConnection fc;
            fc = (FileConnection)Connector.open(Robot.OVERSHOOT_FILE, Connector.WRITE);
            System.out.println("Saving " + Robot.OVERSHOOT_FILE);
            fc.create();
            file = fc.openDataOutputStream();
            file.writeDouble(Robot.OVERSHOOT_ANGLE_POSITIVE);
            System.out.println("OVERSHOOT_ANGLE_POSITIVE: " + Robot.OVERSHOOT_ANGLE_POSITIVE);
            file.writeDouble(Robot.OVERSHOOT_ANGLE_NEGATIVE);
            System.out.println("OVERSHOOT_ANGLE_NEGATIVE: " + Robot.OVERSHOOT_ANGLE_NEGATIVE);
            file.writeDouble(Robot.AUTONOMOUS_DRIVE_GAIN);
            System.out.println("AUTONOMOUS_DRIVE_GAIN: " + Robot.AUTONOMOUS_DRIVE_GAIN);
            file.writeInt(Robot.AUTONOMOUS_DRIVE_TICKS);
            System.out.println("AUTONOMOUS_DRIVE_GAIN: " + Robot.AUTONOMOUS_DRIVE_TICKS);
            file.writeBoolean(Robot.StickControlSingle);
            System.out.println("Stick_Control: " + Robot.StickControlSingle);
            file.writeDouble(Robot.VOLTS_PER_TICK);
            System.out.println("VOLTS_PER_TICK: " + Robot.VOLTS_PER_TICK);
            
            
            file.flush();
            file.close();
            fc.close();
        }
        catch (Exception ex) {
            System.out.println("File output error: " + ex.getMessage());
        }
    }
    
    public void updateDashboard()
    {
        if ((ticks++)%5==0)
        {
            RobotMap.jags.UpdateDashboard();
            SmartDashboard.putNumber("Gyro Angle", driveTrain.getCurrentAngle());
            SmartDashboard.putNumber("OVERSHOOT_ANGLE_POSITIVE", OVERSHOOT_ANGLE_POSITIVE);
            SmartDashboard.putNumber("OVERSHOOT_ANGLE_NEGATIVE", OVERSHOOT_ANGLE_NEGATIVE);
            SmartDashboard.putNumber("AUTONOMOUS_DRIVE_GAIN", AUTONOMOUS_DRIVE_GAIN);
            SmartDashboard.putNumber("AUTONOMOUS_DRIVE_TICKS", AUTONOMOUS_DRIVE_TICKS);
            SmartDashboard.putNumber("VOLTS_PER_TICK", VOLTS_PER_TICK);
            SmartDashboard.putBoolean("Stick Control Single", Robot.StickControlSingle);
            SmartDashboard.putBoolean("Compressor State", RobotMap.pneumaticPusherPushCompressor.enabled());
        }

    }


}
