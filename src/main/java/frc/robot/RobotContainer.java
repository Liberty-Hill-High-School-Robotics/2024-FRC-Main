// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

//auton imports
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.math.MathUtil;
//buttons/controller/command imports
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
//misc imports
import frc.robot.Constants.OIConstants;
//command imports
import frc.robot.commands.DriveAutonCommands.AimWhileMoving;
import frc.robot.commands.DriveAutonCommands.TriggerTurn;
import frc.robot.commands.DriveAutonCommands.resetHeading;
import frc.robot.commands.DriveAutonCommands.rightSnap;
import frc.robot.commands.DriveAutonCommands.sDrive;
import frc.robot.commands.DriveAutonCommands.xPattern;
import frc.robot.commands.LEDCommands.CandleOff;
import frc.robot.commands.LEDCommands.Animations.CandleColorFlow;
import frc.robot.commands.LEDCommands.Animations.CandleFire;
import frc.robot.commands.LEDCommands.Animations.CandleLarson;
import frc.robot.commands.LEDCommands.Animations.CandleRGBFade;
import frc.robot.commands.LEDCommands.Animations.CandleRainbow;
import frc.robot.commands.LEDCommands.Animations.CandleSingleFade;
import frc.robot.commands.LEDCommands.Animations.CandleStrobe;
import frc.robot.commands.LEDCommands.Animations.CandleStrobeBlue;
import frc.robot.commands.LEDCommands.Animations.CandleStrobeGreen;
import frc.robot.commands.LEDCommands.Animations.CandleStrobeRed;
import frc.robot.commands.LEDCommands.Animations.CandleTwinkle;
import frc.robot.commands.LEDCommands.Colors.CandleBlue;
import frc.robot.commands.LEDCommands.Colors.CandleDimPurple;
import frc.robot.commands.LEDCommands.Colors.CandleGold;
import frc.robot.commands.LEDCommands.Colors.CandleGreen;
import frc.robot.commands.LEDCommands.Colors.CandleOrange;
import frc.robot.commands.LEDCommands.Colors.CandlePurple;
import frc.robot.commands.LEDCommands.Colors.CandleRed;
//subsystem imports
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LEDs;
import frc.robot.subsystems.Limelight;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {
  //Define the robot's subsystems
  public final Limelight m_limelight = new Limelight();
  public final LEDs m_leds = new LEDs();
  private final DriveSubsystem m_drivesubsystem = new DriveSubsystem();


  //create an autonomous chooser
  public final SendableChooser<Command> autoChooser;
  
  //Create the driver and operator controller. Please use CommandXboxController instead of XboxController
  CommandXboxController m_driverController = new CommandXboxController(OIConstants.kDriverControllerPort);
  CommandXboxController m_operatorController = new CommandXboxController(OIConstants.kOperatorControllerPort);


  //The container for the robot. Contains subsystems, OI devices, and commands.
  public RobotContainer() {
    //Create namedcommands for PathPlanner paths. Note which names you use, as they have to be exactly the same in PP
    //NamedCommands.registerCommand("AutoIntake", new AutoIntakeTimeout(m_intake, m_storage, m_pivot, m_leds));

    //Pathplanner auto chooser
    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Mode", autoChooser); 

    //Put the autons on the chooser and on SmartDashboard
    SmartDashboard.putData("AmpPlayoff", new PathPlannerAuto("AmpPlayoff"));
    SmartDashboard.putData("SourcePlayoff", new PathPlannerAuto("SourcePlayoff"));
    SmartDashboard.putData("MiddleQuals", new PathPlannerAuto("MiddleQuals"));
    SmartDashboard.putData("TwoNoteMiddle", new PathPlannerAuto("TwoNoteMiddle"));
    SmartDashboard.putData("OneNoteAmp", new PathPlannerAuto("OneNoteAmp"));
    SmartDashboard.putData("OneNoteSource", new PathPlannerAuto("OneNoteSource"));
    SmartDashboard.putData("4.5NoteMiddle", new PathPlannerAuto("4.5NoteMiddle"));
    SmartDashboard.putData("5NoteMiddle", new PathPlannerAuto("5NoteMiddle"));


    //Config for joysticks
    configureButtonBindings();


    //------------------------------------------------------------------------------------------
    //----------------------------- Start of SmartDashboard Exports-----------------------------
    //------------------------------------------------------------------------------------------

    //------------------------------------- Command Exports ------------------------------------

    //DriveSubsytem Exports
    SmartDashboard.putData("AimWhileMoving", new AimWhileMoving(m_drivesubsystem));
    SmartDashboard.putData("ResetHeading", new resetHeading(m_drivesubsystem));
    SmartDashboard.putData("RightSnap", new rightSnap(m_drivesubsystem));
    SmartDashboard.putData("sDrive", new sDrive(m_drivesubsystem));
    SmartDashboard.putData("TriggerTurn", new TriggerTurn(m_drivesubsystem));
    SmartDashboard.putData("xPattern", new xPattern(m_drivesubsystem));

    //LED Exports
    SmartDashboard.putData("CandleOff", new CandleOff(m_leds));
    //Color
    SmartDashboard.putData("CandleBlue", new CandleBlue(m_leds));
    SmartDashboard.putData("CandleDimPurple", new CandleDimPurple(m_leds));
    SmartDashboard.putData("CandleGold", new CandleGold(m_leds));
    SmartDashboard.putData("CandleGreen", new CandleGreen(m_leds));
    SmartDashboard.putData("CandleOrange", new CandleOrange(m_leds));
    SmartDashboard.putData("CandlePurple", new CandlePurple(m_leds));
    SmartDashboard.putData("CandleRed", new CandleRed(m_leds));
    //Animations
    SmartDashboard.putData("CandleColorFlow", new CandleColorFlow(m_leds));
    SmartDashboard.putData("CandleFire", new CandleFire(m_leds));
    SmartDashboard.putData("CandleLarson", new CandleLarson(m_leds));
    SmartDashboard.putData("CandleRainbow", new CandleRainbow(m_leds));
    SmartDashboard.putData("CandleRGBFade", new CandleRGBFade(m_leds));
    SmartDashboard.putData("CandleSingleFade", new CandleSingleFade(m_leds));
    SmartDashboard.putData("CandleStrobe", new CandleStrobe(m_leds));
    SmartDashboard.putData("CandleStrobeBlue", new CandleStrobeBlue(m_leds));
    SmartDashboard.putData("CandleStrobeGreen", new CandleStrobeGreen(m_leds));
    SmartDashboard.putData("CandleStrobeRed", new CandleStrobeRed(m_leds));
    SmartDashboard.putData("CandleTwinkle", new CandleTwinkle(m_leds));

    //Other
    SmartDashboard.putNumber("DriverLeftJoystickXValue", m_driverController.getLeftX());
    SmartDashboard.putNumber("Pigeon2Heading", m_drivesubsystem.getHeading());

    //------------------------------------------------------------------------------------------
    //------------------------------ End of SmartDashboard Exports------------------------------
    //------------------------------------------------------------------------------------------
    
    // Configure default commands
    m_drivesubsystem.setDefaultCommand(
    new RunCommand(() -> {
        // Go full speed is "boost mode", go .8 speed in normal mode
        var boostRatio = m_driverController.getHID().getLeftBumper() ? 1 : .8;
        m_drivesubsystem.drive(
          //inputs for drive command
          -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband) * boostRatio,
          -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband) * boostRatio,
          -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
          true, true);
        }, m_drivesubsystem));


  } //------------------------------------------------------------------------------------------


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
    //This is where you configure all buttons to commands for any controllers

    // Driver controller  buttons
    final Trigger SetX = m_driverController.x(); 
    SetX.whileTrue(new xPattern(m_drivesubsystem));
    
    final Trigger resetHeading = m_driverController.y();
    resetHeading.onTrue(new resetHeading(m_drivesubsystem));

    final Trigger triggerTurn2 = m_driverController.leftTrigger(.1);
    triggerTurn2.whileTrue(new TriggerTurn(m_drivesubsystem));

    final Trigger triggerTurn3 = m_driverController.rightTrigger(.1);
    triggerTurn3.whileTrue(new TriggerTurn(m_drivesubsystem));


    //------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------
    // Operator Controller

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  //More AutonSelector stuff
  public Command getAutonomousCommand() {
    return autoChooser.getSelected();  
  }
}
