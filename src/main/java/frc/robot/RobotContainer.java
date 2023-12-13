// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
//import edu.wpi.first.wpilibj.PS4Controller.Button;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.*;

import frc.robot.subsystems.DriveSubsystem;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();

  // The driver's controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
SendableChooser<Command> m_chooser = new SendableChooser<>();

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_chooser.addOption("sDrive", new sDrive(m_robotDrive));

    SmartDashboard.putData("AutonMode", m_chooser);
    SmartDashboard.putData("rightSnap", new rightSnap(m_robotDrive));
    SmartDashboard.putData("Drive", m_robotDrive);
    //doing some branch testing
    //contin.
    
   
    
    // Configure default commands
    m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                DriveConstants.driveScheme, true), //drivescheme sets either field centric or not
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

    final Trigger controlSchemeButton = new JoystickButton(m_driverController, 1); //need button number!
    controlSchemeButton.onTrue(new controlScheme(m_robotDrive));

    final Trigger resetHeadingButton = new JoystickButton(m_driverController, 2); //need button number!
    resetHeadingButton.onTrue(new resetHeading(m_robotDrive));

    final POVButton snapFrontButton = new POVButton(m_driverController, 0, 1);
    snapFrontButton.onTrue(new rightSnap(m_robotDrive));

    //the implementation below is kind of weird, it calls a function directly from a subsystem, and 
    //the buttons don't seem to work, or aren't tuned properly.

  //  new JoystickButton(m_driverController, Button.kR1.value)
  //      .whileTrue(new RunCommand(
  //          () -> m_robotDrive.setX(),
  //          m_robotDrive));
  
    //here is an implementation as I see it should be
    final Trigger xPatternButton = new JoystickButton(m_driverController, 6);
    xPatternButton.whileTrue(new xPattern(m_robotDrive));

  }
  /* Example Button Binding from 2023 Main code
  starts by defining a trigger, can be replaced with other button types like POVButton, but only when necessary
  assigns the button to joystick and button # 
  calls button name, then .ontrue, .onfalse, etc. will run a command, from a file, not a function from a subsystem

    final Trigger buttonResetIAccum = new JoystickButton(driverJoystick, 8);
    buttonResetIAccum.onTrue(new ResetIAccum(m_drive));
   */

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
     return m_chooser.getSelected();
  }
}
