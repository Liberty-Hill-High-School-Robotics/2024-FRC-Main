// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

//auton imports
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.VecBuilder;
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
import frc.robot.commands.BarCommands.AngleBarRotatorPivot;
import frc.robot.commands.BarCommands.BarRotateBackward;
import frc.robot.commands.BarCommands.BarRotateForward;
import frc.robot.commands.BarCommands.BarRotateRestRelativeEncoder;
import frc.robot.commands.BarCommands.BarRotateStop;
import frc.robot.commands.DriveAutonCommands.AimWhileMoving;
import frc.robot.commands.DriveAutonCommands.TriggerTurn;
import frc.robot.commands.DriveAutonCommands.resetHeading;
import frc.robot.commands.DriveAutonCommands.rightSnap;
import frc.robot.commands.DriveAutonCommands.sDrive;
import frc.robot.commands.DriveAutonCommands.xPattern;
import frc.robot.commands.ElevatorCommands.ElevatorConstant;
import frc.robot.commands.ElevatorCommands.ElevatorDown;
import frc.robot.commands.ElevatorCommands.ElevatorStop;
import frc.robot.commands.ElevatorCommands.ElevatorUp;
import frc.robot.commands.IntakeCommands.IntakeTogether;
import frc.robot.commands.IntakeCommands.IntakeRoller.IntakeRollerBackFeed;
import frc.robot.commands.IntakeCommands.IntakeRoller.IntakeRollerBackFeedTogeather;
import frc.robot.commands.IntakeCommands.IntakeRoller.IntakeRollerFeed;
import frc.robot.commands.IntakeCommands.IntakeRoller.IntakeRollerStop;
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
import frc.robot.commands.LEDCommands.Animations.CandleStrobeRedEndCond;
import frc.robot.commands.LEDCommands.Animations.CandleTwinkle;
import frc.robot.commands.LEDCommands.Colors.CandleBlue;
import frc.robot.commands.LEDCommands.Colors.CandleDimPurple;
import frc.robot.commands.LEDCommands.Colors.CandleGold;
import frc.robot.commands.LEDCommands.Colors.CandleGreen;
import frc.robot.commands.LEDCommands.Colors.CandleOrange;
import frc.robot.commands.LEDCommands.Colors.CandlePurple;
import frc.robot.commands.LEDCommands.Colors.CandleRed;
import frc.robot.commands.PivotCommmands.Pivot.AngleAndFeed;
import frc.robot.commands.PivotCommmands.Pivot.AnglePivot;
import frc.robot.commands.PivotCommmands.Pivot.AngleSub;
import frc.robot.commands.PivotCommmands.Pivot.PivotDown;
import frc.robot.commands.PivotCommmands.Pivot.PivotSetpoint;
import frc.robot.commands.PivotCommmands.Pivot.PivotStop;
import frc.robot.commands.PivotCommmands.Pivot.PivotUp;
import frc.robot.commands.PivotCommmands.Pivot.PivotWithBar;
import frc.robot.commands.PivotCommmands.TRollers.TRollerBackFeed;
import frc.robot.commands.PivotCommmands.TRollers.TRollerFeed;
import frc.robot.commands.PivotCommmands.TRollers.TRollerStop;
import frc.robot.commands.SemiAutonomousCommands.AimSub;
import frc.robot.commands.SemiAutonomousCommands.AimSub2;
import frc.robot.commands.SemiAutonomousCommands.AmpBack;
import frc.robot.commands.SemiAutonomousCommands.AmpPrep;
import frc.robot.commands.SemiAutonomousCommands.AutoAim;
import frc.robot.commands.SemiAutonomousCommands.AutoIntake;
import frc.robot.commands.SemiAutonomousCommands.AutoIntakeTimeout;
import frc.robot.commands.SemiAutonomousCommands.AutoRev;
import frc.robot.commands.SemiAutonomousCommands.IntakeOut;
import frc.robot.commands.ShooterCommands.RevShooter;
import frc.robot.commands.ShooterCommands.RevShooterSub;
import frc.robot.commands.ShooterCommands.ShooterBackFeed;
import frc.robot.commands.ShooterCommands.ShooterFeed;
import frc.robot.commands.ShooterCommands.ShooterStop;
import frc.robot.commands.ShooterCommands.shooterSetpoint;
import frc.robot.commands.StorageCommands.AutoFeedNote;
import frc.robot.commands.StorageCommands.StorageRollersBackFeed;
import frc.robot.commands.StorageCommands.StorageRollersFeed;
import frc.robot.commands.StorageCommands.StorageRollersShooter;
import frc.robot.commands.StorageCommands.StorageRollersStop;
//subsystem imports
import frc.robot.subsystems.Bar;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LEDs;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;


/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {
  //Define the robot's subsystems
  public final Bar m_bar = new Bar();
  public final Elevator m_elevator = new Elevator();
  public final Intake m_intake = new Intake();
  public final Limelight m_limelight = new Limelight();
  public final Storage m_storage = new Storage();
  public final Pivot m_pivot = new Pivot();
  public final Shooter m_shooter = new Shooter();
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
    NamedCommands.registerCommand("AutoIntake", new AutoIntakeTimeout(m_intake, m_storage, m_pivot, m_leds));
    NamedCommands.registerCommand("AutoRev", new AutoRev(m_shooter, m_pivot, m_leds));
    NamedCommands.registerCommand("FeedNote", new AutoFeedNote(m_storage));
    NamedCommands.registerCommand("CandleBlue", new CandleBlue(m_leds));
    NamedCommands.registerCommand("CandleRed", new CandleRed(m_leds));
    NamedCommands.registerCommand("CandleOff", new CandleOff(m_leds));
    NamedCommands.registerCommand("AutoShoot", new CandleRed(m_leds));
    NamedCommands.registerCommand("IntakeEnd", new CandleStrobeRedEndCond(m_leds));
    NamedCommands.registerCommand("subshot2", new AimSub2(m_shooter, m_pivot));

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
    //Bar Exports
    SmartDashboard.putData("AngleBarRotatorPivot", new AngleBarRotatorPivot(m_bar, 10)); //Needs Setpoint (Default 10)
    SmartDashboard.putData("BarRotateBackward", new BarRotateBackward(m_bar));
    SmartDashboard.putData("BarRotateForward", new BarRotateForward(m_bar));
    SmartDashboard.putData("BarRotateRestRelativeEncoder", new BarRotateRestRelativeEncoder(m_bar));
    SmartDashboard.putData("BarRotateStop", new BarRotateStop(m_bar));

    //DriveSubsytem Exports
    SmartDashboard.putData("AimWhileMoving", new AimWhileMoving(m_drivesubsystem));
    SmartDashboard.putData("ResetHeading", new resetHeading(m_drivesubsystem));
    SmartDashboard.putData("RightSnap", new rightSnap(m_drivesubsystem));
    SmartDashboard.putData("sDrive", new sDrive(m_drivesubsystem));
    SmartDashboard.putData("TriggerTurn", new TriggerTurn(m_drivesubsystem));
    SmartDashboard.putData("xPattern", new xPattern(m_drivesubsystem));

    //Elevator Exports
    SmartDashboard.putData("ElevatorConstant", new ElevatorConstant(m_elevator));
    SmartDashboard.putData("ElevatorDown", new ElevatorDown(m_elevator));
    SmartDashboard.putData("ElevatorStop", new ElevatorStop(m_elevator));
    SmartDashboard.putData("ElevatorUp", new ElevatorUp(m_elevator));

    //Intake Exports
    SmartDashboard.putData("IntakeRollerBackFeed", new IntakeRollerBackFeed(m_intake));
    SmartDashboard.putData("IntakeRollerBackFeedTogether", new IntakeRollerBackFeedTogeather(m_intake));
    SmartDashboard.putData("IntakeRollerFeed", new IntakeRollerFeed(m_intake));
    SmartDashboard.putData("IntakeRollerStop", new IntakeRollerStop(m_intake));
    SmartDashboard.putData("IntakeTogether", new IntakeTogether(m_intake));

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
    SmartDashboard.putData("CandleStrobeRedEndCondition", new CandleStrobeRedEndCond(m_leds));
    SmartDashboard.putData("CandleTwinkle", new CandleTwinkle(m_leds));

    //Pivot Exports
    SmartDashboard.putData("AngleAndFeed", new AngleAndFeed(m_pivot));
    SmartDashboard.putData("AnglePivot", new AnglePivot(m_pivot));
    SmartDashboard.putData("AngleSub", new AngleSub(m_pivot));
    SmartDashboard.putData("PivotDown", new PivotDown(m_pivot));
    SmartDashboard.putData("PivotSetpoint", new PivotSetpoint(m_pivot, 15)); //Needs Setpoint (No Default)
    SmartDashboard.putData("PivotStop", new PivotStop(m_pivot));
    SmartDashboard.putData("PivotUp", new PivotUp(m_pivot));
    SmartDashboard.putData("PivotWithBar", new PivotWithBar(m_pivot, 39));   //Needs Setpoint (Default 39)
    SmartDashboard.putData("TRollerBackFeed", new TRollerBackFeed(m_pivot));
    SmartDashboard.putData("TRollerFeed", new TRollerFeed(m_pivot));
    SmartDashboard.putData("TRollerStop", new TRollerStop(m_pivot));

    //Shooter Exports
    SmartDashboard.putData("RevShooter", new RevShooter(m_shooter));
    SmartDashboard.putData("RevShooterSub", new RevShooterSub(m_shooter));
    SmartDashboard.putData("ShooterBackFeed", new ShooterBackFeed(m_shooter));
    SmartDashboard.putData("ShooterFeed", new ShooterFeed(m_shooter));
    SmartDashboard.putData("ShooterSetpoint", new shooterSetpoint(m_shooter, .6)); //Needs Setpoint (Default .6)
    SmartDashboard.putData("ShooterStop", new ShooterStop(m_shooter));

    //Storage Exports
    SmartDashboard.putData("AutoFeedNote", new AutoFeedNote(m_storage));
    SmartDashboard.putData("StorageRollersBackFeed", new StorageRollersBackFeed(m_storage));
    SmartDashboard.putData("StorageRollersFeed", new StorageRollersFeed(m_storage));
    SmartDashboard.putData("StorageRollersShooter", new StorageRollersShooter(m_storage));
    SmartDashboard.putData("StorageRollersStop", new StorageRollersStop(m_storage));

    //------------------------------------- Other Exports ------------------------------------
    //SemiAuto Commands
    SmartDashboard.putData("AimSub", new AimSub(m_shooter, m_pivot, m_drivesubsystem));
    SmartDashboard.putData("AimSub2", new AimSub2(m_shooter, m_pivot));
    SmartDashboard.putData("AmpBack", new AmpBack(m_bar, m_shooter, m_pivot, m_storage));
    SmartDashboard.putData("AmpPrep", new AmpPrep(m_bar, m_shooter, m_pivot));
    SmartDashboard.putData("AutoAim", new AutoAim(m_shooter, m_pivot, m_drivesubsystem, m_leds));
    SmartDashboard.putData("AutoIntake", new AutoIntake(m_intake, m_storage, m_pivot, m_leds));
    SmartDashboard.putData("AutoIntakeTimeout", new AutoIntakeTimeout(m_intake, m_storage, m_pivot, m_leds));
    SmartDashboard.putData("AutoRev", new AutoRev(m_shooter, m_pivot, m_leds));
    SmartDashboard.putData("IntakeOut", new IntakeOut(m_intake, m_storage, m_pivot));

    //Other
    SmartDashboard.putNumber("DriverLeftJoystickXValue", m_driverController.getLeftX());
    SmartDashboard.putNumber("Pigeon2Heading", m_drivesubsystem.getHeading());

    //------------------------------------------------------------------------------------------
    //------------------------------ End of SmartDashboard Exports------------------------------
    //------------------------------------------------------------------------------------------
    
    //Localization stuff (NERDY WARNING)
    LimelightHelpers.SetRobotOrientation("limelight", m_poseEstimator.getEstimatedPosition().getRotation().getDegrees(), 0, 0, 0, 0, 0);
      LimelightHelpers.PoseEstimate mt2 = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2("limelight");
      if(Math.abs(m_drivesubsystem.getGyroRotRate()) > 720) // if our angular velocity is greater than 720 degrees per second, ignore vision updates
      {
        doRejectUpdate = true;
      }
      if(!doRejectUpdate)
      {
        m_poseEstimator.setVisionMeasurementStdDevs(VecBuilder.fill(.7,.7,9999999));
        m_poseEstimator.addVisionMeasurement(
            mt2.pose,
            mt2.timestampSeconds);
      }

    
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
    final Trigger AutoAim = m_driverController.a();
    AutoAim.toggleOnTrue(new AutoAim(m_shooter, m_pivot, m_drivesubsystem, m_leds));

    final Trigger PivotUp = m_driverController.b();
    PivotUp.whileTrue(new AngleSub(m_pivot));

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

    //Deploys Intake, Runs the Intake rollers, Stops rollers when game piece is detected in through beam sensor
    final Trigger AutoIntake = m_operatorController.x();
    AutoIntake.whileTrue(new AutoIntake(m_intake, m_storage, m_pivot, m_leds));
    AutoIntake.onFalse(new PivotDown(m_pivot));

    final Trigger Fire = m_operatorController.rightBumper();
    Fire.whileTrue(new StorageRollersShooter(m_storage));
    Fire.whileFalse(new StorageRollersStop(m_storage));

    final Trigger SubwooferOverride = m_operatorController.b();
    SubwooferOverride.whileTrue(new AimSub(m_shooter, m_pivot, m_drivesubsystem));

    final Trigger Amp = m_operatorController.leftBumper();
    Amp.toggleOnTrue(new AmpPrep(m_bar, m_shooter, m_pivot));
    Amp.toggleOnFalse(new AmpBack(m_bar, m_shooter, m_pivot, m_storage));
    
    final Trigger EleUp = m_operatorController.y();
    EleUp.whileTrue(new ElevatorUp(m_elevator));

    final Trigger EleDown = m_operatorController.a();
    EleDown.whileTrue(new ElevatorDown(m_elevator));

    final Trigger PivotDown = m_operatorController.start();
    PivotDown.whileTrue(new PivotSetpoint(m_pivot, 0));

    final Trigger IntakeOut = m_operatorController.back();
    IntakeOut.whileTrue(new IntakeOut(m_intake, m_storage, m_pivot));

    final Trigger IntakeBackFeed = m_operatorController.leftStick();
    IntakeBackFeed.whileTrue(new IntakeRollerBackFeedTogeather(m_intake));
  
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
