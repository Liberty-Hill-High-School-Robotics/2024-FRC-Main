// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
//import edu.wpi.first.wpilibj.PS4Controller.Button;


import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;
//import com.pathplanner.lib.commands.PathPlannerAuto;
/*
import com.pathplanner.lib.path.PathPlannerTrajectory;
import com.pathplanner.lib.path.EventMarker;
import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.commands.FollowPathWithEvents;
*/
//import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.math.MathUtil;
//limelight imports
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.BarCommands.AngleBarRotatorPivot;
import frc.robot.commands.BarCommands.BarRotateBackward;
import frc.robot.commands.BarCommands.BarRotateForward;
import frc.robot.commands.BarCommands.BarRotateStop;
import frc.robot.commands.DriveAutonCommands.AimWhileMoving;
import frc.robot.commands.DriveAutonCommands.TriggerTurn;
import frc.robot.commands.DriveAutonCommands.resetHeading;
import frc.robot.commands.DriveAutonCommands.rightSnap;
import frc.robot.commands.DriveAutonCommands.xPattern;
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
import frc.robot.commands.LEDCommands.Animations.CandleStrobeRed;
import frc.robot.commands.LEDCommands.Animations.CandleStrobeRedEndCond;
import frc.robot.commands.LEDCommands.Animations.CandleTwinkle;
import frc.robot.commands.LEDCommands.Colors.CandleBlue;
import frc.robot.commands.LEDCommands.Colors.CandleGold;
import frc.robot.commands.LEDCommands.Colors.CandleGreen;
import frc.robot.commands.LEDCommands.Colors.CandleOrange;
import frc.robot.commands.LEDCommands.Colors.CandlePurple;
import frc.robot.commands.LEDCommands.Colors.CandleRed;
import frc.robot.commands.PivotCommmands.Pivot.AnglePivot;
import frc.robot.commands.PivotCommmands.Pivot.AngleSub;
import frc.robot.commands.PivotCommmands.Pivot.PivotDown;
import frc.robot.commands.PivotCommmands.Pivot.PivotSetpoint;
import frc.robot.commands.PivotCommmands.Pivot.PivotStop;
import frc.robot.commands.PivotCommmands.Pivot.PivotUp;
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
import frc.robot.commands.ShooterCommands.ShooterBackFeed;
import frc.robot.commands.ShooterCommands.ShooterFeed;
import frc.robot.commands.ShooterCommands.ShooterStop;
import frc.robot.commands.ShooterCommands.shooterSetpoint;
import frc.robot.commands.StorageCommands.AutoFeedNote;
import frc.robot.commands.StorageCommands.StorageRollersBackFeed;
import frc.robot.commands.StorageCommands.StorageRollersFeed;
import frc.robot.commands.StorageCommands.StorageRollersShooter;
import frc.robot.commands.StorageCommands.StorageRollersStop;
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

// The robot's subsystems
  public final Bar m_bar = new Bar();
  public final Elevator m_elevator = new Elevator();
  public final Intake m_intake = new Intake();
  public final Limelight m_limelight = new Limelight();
  public final Storage m_storage = new Storage();
  public final Pivot m_pivot = new Pivot();
  public final Shooter m_shooter = new Shooter();
  public final LEDs m_leds = new LEDs();
  /*  
  private ShuffleboardTab tab = Shuffleboard.getTab("testing");
   private GenericEntry entryshooterSetpoint =
      tab.add("entryshooterSetpoint", 0)
         .getEntry();
  
   private GenericEntry entrypivotSetpoint =
      tab.add("entrypivotSetpoint", 0)
         .getEntry();

  public static int increment = 0;
*/

  private final DriveSubsystem m_drivesubsystem = new DriveSubsystem();
  public final SendableChooser<Command> autoChooser;
  
  // The driver's controller
  CommandXboxController m_driverController = new CommandXboxController(OIConstants.kDriverControllerPort);
  // The operator's controller
  CommandXboxController m_operatorController = new CommandXboxController(OIConstants.kOperatorControllerPort);


  //The container for the robot. Contains subsystems, OI devices, and commands.
SendableChooser<Command> m_chooser2 = new SendableChooser<>();

  public RobotContainer() {

    //named command stuff
    NamedCommands.registerCommand("AutoIntake", new AutoIntakeTimeout(m_intake, m_storage, m_pivot));
    NamedCommands.registerCommand("AutoRev", new AutoRev(m_shooter, m_pivot));
    NamedCommands.registerCommand("FeedNote", new AutoFeedNote(m_storage));
    NamedCommands.registerCommand("CandleBlue", new CandleBlue(m_leds));
    NamedCommands.registerCommand("CandleRed", new CandleRed(m_leds));
    NamedCommands.registerCommand("CandleOff", new CandleOff(m_leds));
    NamedCommands.registerCommand("AutoShoot", new CandleRed(m_leds));
    NamedCommands.registerCommand("IntakeEnd", new CandleStrobeRedEndCond(m_leds));
    NamedCommands.registerCommand("subshot2", new AimSub2(m_shooter, m_pivot));

  

    configureButtonBindings();


    //Pathplanner auto chooser
    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Mode", autoChooser); 


        
    //Autons
    SmartDashboard.putData("AmpPlayoff", new PathPlannerAuto("AmpPlayoff"));
    SmartDashboard.putData("SourcePlayoff", new PathPlannerAuto("SourcePlayoff"));
    SmartDashboard.putData("MiddleQuals", new PathPlannerAuto("MiddleQuals"));
    SmartDashboard.putData("TwoNoteMiddle", new PathPlannerAuto("TwoNoteMiddle"));
    SmartDashboard.putData("OneNoteAmp", new PathPlannerAuto("OneNoteAmp"));
    SmartDashboard.putData("OneNoteSource", new PathPlannerAuto("OneNoteSource"));
    SmartDashboard.putData("4.5NoteMiddle", new PathPlannerAuto("4.5NoteMiddle"));
    SmartDashboard.putData("5NoteMiddle", new PathPlannerAuto("5NoteMiddle"));

    //SmartDashboard.putData("ThreeNoteMiddle", new PathPlannerAuto("ThreeNoteMiddle"));


    //SmartDashboard.putData("BlueNoteClear", new PathPlannerAuto("BlueNoteClear"));
    //SmartDashboard.putData("4NoteAuto", new PathPlannerAuto("4NoteAuto"));
    //SmartDashboard.putData("testAuto", new PathPlannerAuto("CommandTest"));

    SmartDashboard.putData("AutonMode", m_chooser2);
    SmartDashboard.putData("RightSnap", new rightSnap(m_drivesubsystem));
    SmartDashboard.putData("Drive", m_drivesubsystem);
    SmartDashboard.putBoolean("DriveState", true);
    SmartDashboard.putNumber("rightjoystickx", m_driverController.getLeftX());
  

    SmartDashboard.putData("AutoAim", new AutoAim(m_shooter, m_pivot, m_drivesubsystem));

    SmartDashboard.putData("ShooterOut", new ShooterFeed(m_shooter));
    SmartDashboard.putData("ShooterIn", new ShooterBackFeed(m_shooter));
    SmartDashboard.putData("ShooterStop", new ShooterStop(m_shooter));
    SmartDashboard.putData("RedStrobeLEDs", new CandleStrobeRed(m_leds));

    
    SmartDashboard.putData("ElevatorUp",new ElevatorUp(m_elevator));
    SmartDashboard.putData("ElevatorDown", new ElevatorDown(m_elevator));
    SmartDashboard.putData("ElevatorStop", new ElevatorStop(m_elevator));


    SmartDashboard.putData("StorageRollersFeed", new StorageRollersFeed(m_storage));
    SmartDashboard.putData("StorageRollersShooter", new StorageRollersShooter(m_storage));
    SmartDashboard.putData("StorageRollersBackFeed", new StorageRollersBackFeed(m_storage));
    SmartDashboard.putData("StorageRollersStop", new StorageRollersStop(m_storage));

    SmartDashboard.putData("TRollerFeed", new TRollerFeed(m_pivot));
    SmartDashboard.putData("TRollerBackFeed", new TRollerBackFeed(m_pivot));
    SmartDashboard.putData("TRollerStop", new TRollerStop(m_pivot));

    SmartDashboard.putData("PivotUp", new PivotUp(m_pivot));
    SmartDashboard.putData("PivotDown", new PivotDown(m_pivot));
    SmartDashboard.putData("PivotStop", new PivotStop(m_pivot));
    SmartDashboard.putData("autofeednote", new AutoFeedNote(m_storage));
    SmartDashboard.putData("autorev", new AutoRev(m_shooter, m_pivot));

    SmartDashboard.putData("IntakeRollerFeed", new IntakeRollerFeed(m_intake));
    SmartDashboard.putData("IntakeRollerBackFeed", new IntakeRollerBackFeed(m_intake));
    SmartDashboard.putData("IntakeRollerStop", new IntakeRollerStop(m_intake));

    SmartDashboard.putData("IntakeTogether",new IntakeTogether(m_intake));
    
    SmartDashboard.putData("LEDgold", new CandleGold(m_leds));
    SmartDashboard.putData("LEDorange", new CandleOrange(m_leds));
    SmartDashboard.putData("LEDbule", new CandleBlue(m_leds));
    SmartDashboard.putData("LEDred", new CandleRed(m_leds));
    SmartDashboard.putData("LEDpurple", new CandlePurple(m_leds));
    SmartDashboard.putData("LEDgrreeeeen", new CandleGreen(m_leds));
    SmartDashboard.putData("rainbowanimation", new CandleRainbow(m_leds));
    SmartDashboard.putData("ledstrobe", new CandleStrobe(m_leds));
    SmartDashboard.putData("ledSTOP", new CandleOff(m_leds));
    SmartDashboard.putData("ColorFlowanimation", new CandleColorFlow(m_leds));
    SmartDashboard.putData("fireanimation", new CandleFire(m_leds));
    SmartDashboard.putData("larsonanimation", new CandleLarson(m_leds));
    SmartDashboard.putData("rgbfade", new CandleRGBFade(m_leds));
    SmartDashboard.putData("singlefade", new CandleSingleFade(m_leds));
    SmartDashboard.putData("candletwinkle", new CandleTwinkle(m_leds));

    SmartDashboard.putData("AutoIntake", new AutoIntake(m_intake, m_storage, m_pivot));
    
    SmartDashboard.putData("BarRotateForward", new BarRotateForward(m_bar));
    SmartDashboard.putData("BarRotateBackward", new BarRotateBackward(m_bar));
    SmartDashboard.putData("BarRotateStop", new BarRotateStop(m_bar));
    SmartDashboard.putData("AngleBarRotatorPivot", new AngleBarRotatorPivot(m_bar, 10));

    SmartDashboard.putData("AnglePivot", new AnglePivot(m_pivot)); //m_pivot.calculateAngle()
    SmartDashboard.putData("RevShooter", new RevShooter(m_shooter));//m_shooter.calculateSpeed()
    //SmartDashboard.putData("RevShooter", new RevShooter(m_shooter, .1));
    SmartDashboard.putData("AmpPrep", new AmpPrep(m_bar, m_shooter, m_pivot));
     SmartDashboard.putData("AmpBack", new AmpBack(m_bar, m_shooter, m_pivot, m_storage));

    //double entryPivotSetpoint =  SmartDashboard.getNumber("entryPivotSetpoint",0);
    //double entryShooterSetpoint =  SmartDashboard.getNumber("entryShooterSetpoint",0);

    //SmartDashboard.putNumber("output", entryshooterSetpoint.getDouble(0));

    //SmartDashboard.putData("PivotSetpoint", new PivotSetpoint(m_pivot, entrypivotSetpoint.getDouble(0))); //m_pivot.calculateAngle()
    //SmartDashboard.putData("ShooterSetpoint", new shooterSetpoint(m_shooter, entryshooterSetpoint.getDouble(0)));//m_shooter.calculateSpeed()
    SmartDashboard.putData("PivotSetpoint", new PivotSetpoint(m_pivot, 15)); //m_pivot.calculateAngle()
    SmartDashboard.putData("ShooterSetpoint", new shooterSetpoint(m_shooter, .6));

    //SmartDashboard.putNumber("increment", increment);
    SmartDashboard.putData("turnwhileaim", new AimWhileMoving(m_drivesubsystem));

    SmartDashboard.putNumber("Pigeon2Heading", m_drivesubsystem.getHeading());

    SmartDashboard.putData("autointaketimeout", new AutoIntakeTimeout(m_intake, m_storage, m_pivot));
    
    //double shooterSetSetPoint = SmartDashboard.getNumber("shooterSetSetPoint", getTa());
    //SmartDashboard.putData("revShooter", new revShooter(m_shooter));



   
    
    // Configure default commands
    m_drivesubsystem.setDefaultCommand(
    new RunCommand(
        () -> {
          // Go full speed is "boost mode", go .8 speed in normal mode
          var boostRatio = m_driverController.getHID().getLeftBumper() ? 1 : .8;
          m_drivesubsystem.drive(
              -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband) * boostRatio,
              -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband) * boostRatio,
              -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
              true, true); // drivescheme sets either field centric or not
        },
        m_drivesubsystem));

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
    LeftBumper(5),
     
    RightBumper(6),
 
    LeftStick(9),
     
    RightStick(10),
    
    A(1),
    
    B(2),
    
    X(3),
    
    Y(4),
    
    Back(7),
    
    Start(8);
    */

    // Driver controller  buttons

    final Trigger AutoAim = m_driverController.a();
    AutoAim.toggleOnTrue(new AutoAim(m_shooter, m_pivot, m_drivesubsystem));

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


    // Operator Controller button

    //Deploys Intake, Runs the Intake rollers, Stops rollers when game piece is detected in through beam sensor, and retracts
    //
    final Trigger AutoIntake = m_operatorController.x();
    AutoIntake.whileTrue(new AutoIntake(m_intake, m_storage, m_pivot));

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

   public static boolean getTv() {
   NetworkTableEntry tv = table.getEntry("tv");
   return tv.getBoolean(false);
   }

   
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoChooser.getSelected();  
  }
}
