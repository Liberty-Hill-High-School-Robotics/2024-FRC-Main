// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
//import edu.wpi.first.wpilibj.PS4Controller.Button;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OIConstants;


//subsystem and command imports
import frc.robot.subsystems.*;
import frc.robot.commands.DriveAutonCommands.*;
import frc.robot.commands.ShooterCommands.*;
import frc.robot.commands.StorageCommands.StorageRollersFeed;
import frc.robot.commands.StorageCommands.*;

//leave these imports here, we will need them later...
//import frc.robot.commands.ElevatorCommands.*;
//import frc.robot.commands.IntakeCommands.*;
//import frc.robot.commands.PivotCommmands.*;
//import frc.robot.commands.BarCommands.*;

//limelight imports
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */


public class RobotContainer {

// The robot's subsystems
    public final Bar m_bar = new Bar();
    public final DriveSubsystem m_drivesubsystem = new DriveSubsystem();
    public final Elevator m_elevator = new Elevator();
    public final Intake m_intake = new Intake();
    public final Limelight m_limelight = new Limelight();
    public final Pivot m_pivot = new Pivot();
    public final Shooter m_shooter = new Shooter();
    public final Storage m_storage = new Storage();   


  // The robot's subsystems
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final SendableChooser<Command> autoChooser;
  
  // The driver's controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);



  //The container for the robot. Contains subsystems, OI devices, and commands.
SendableChooser<Command> m_chooser = new SendableChooser<>();

  public RobotContainer() {
    configureButtonBindings();

    //Pathplanner auto chooser
    autoChooser = AutoBuilder.buildAutoChooser();

    //Autons
    SmartDashboard.putData("tAuto", new PathPlannerAuto("tAuto"));
    SmartDashboard.putData("4NoteAuto", new PathPlannerAuto("4NoteAuto"));
    SmartDashboard.putData("testAuto", new PathPlannerAuto("CommandTest"));


    //
    m_chooser.addOption("sDrive", new sDrive(m_robotDrive));
    SmartDashboard.putData("AutonMode", m_chooser);
    SmartDashboard.putData("rightSnap", new rightSnap(m_robotDrive));
    SmartDashboard.putData("Drive", m_robotDrive);
    SmartDashboard.putBoolean("DriveState", true);

    SmartDashboard.putData("shooterOut", new ShooterFeed(m_shooter));
    SmartDashboard.putData("shooterIn", new ShooterBackFeed(m_shooter));
    SmartDashboard.putData("shooterStop", new ShooterStop(m_shooter));

    SmartDashboard.putData("storageRollersFeed", new StorageRollersFeed(m_storage));
    SmartDashboard.putData("storageRollersBackFeed", new StorageRollersBackFeed(m_storage));
    SmartDashboard.putData("storageRollersStop", new StorageRollersStop(m_storage));

    
    //double shooterSetSetPoint = SmartDashboard.getNumber("shooterSetSetPoint", getTa());
    //SmartDashboard.putData("revShooter", new revShooter(m_shooter));



   
    
    // Configure default commands
    m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                true, true), //drivescheme sets either field centric or not
            m_robotDrive));

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling
   * passing it to a
   * {@link JoystickButton}.
   */
  private void configureButtonBindings() {


    /* 
    final Trigger resetHeadingButton = new JoystickButton(m_driverController, 5);
    resetHeadingButton.onTrue(new resetHeading(m_robotDrive));

    final POVButton snapFrontButton = new POVButton(m_driverController, 0, 1);
    snapFrontButton.onTrue(new rightSnap(m_robotDrive));
    */

    //the implementation below is kind of weird, it calls a function directly from a subsystem, and 
    //the buttons don't seem to work, or aren't tuned properly.

  //  new JoystickButton(m_driverController, Button.kR1.value)
  //      .whileTrue(new RunCommand(
  //          () -> m_robotDrive.setX(),
  //          m_robotDrive));
  
    //here is an implementation as I see it should be
    final Trigger xPatternButton = new JoystickButton(m_driverController, 3);
    xPatternButton.whileTrue(new xPattern(m_robotDrive));

  }
  /* Example Button Binding from 2023 Main code
  starts by defining a trigger, can be replaced with other button types like POVButton, but only when necessary
  assigns the button to joystick and button # 
  calls button name, then .ontrue, .onfalse, etc. will run a command, from a file, not a function from a subsystem

    final Trigger buttonResetIAccum = new JoystickButton(driverJoystick, 8);
    buttonResetIAccum.onTrue(new ResetIAccum(m_drive));
   */

   static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

   public static double getTx() {
   NetworkTableEntry tx = table.getEntry("tx");
   return tx.getDouble(0.0);
   }

   public static double getTy() {
   NetworkTableEntry ty = table.getEntry("ty");
   return ty.getDouble(0.0);
   }

   public static double getTa() {
   NetworkTableEntry ta = table.getEntry("ta");
   return ta.getDouble(0.0);
   }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new PathPlannerAuto("tAuto");
  }
}
