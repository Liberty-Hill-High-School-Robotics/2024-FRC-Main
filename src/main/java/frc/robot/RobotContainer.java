// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
//import edu.wpi.first.wpilibj.PS4Controller.Button;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
//import com.pathplanner.lib.commands.PathPlannerAuto;
/*
import com.pathplanner.lib.path.PathPlannerTrajectory;
import com.pathplanner.lib.path.EventMarker;
import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.commands.FollowPathWithEvents;
*/

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
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OIConstants;
//import frc.robot.Constants.ShooterConstants;
//subsystem imports
//import frc.robot.commands.AutoAimCommands.*;
import frc.robot.commands.BarCommands.*;
import frc.robot.commands.DriveAutonCommands.*;
import frc.robot.commands.ElevatorCommands.ElevatorDown;
import frc.robot.commands.ElevatorCommands.ElevatorStop;
import frc.robot.commands.ElevatorCommands.ElevatorUp;
//import frc.robot.commands.ElevatorCommands.*;
import frc.robot.commands.IntakeCommands.IntakePivot.*;
import frc.robot.commands.IntakeCommands.IntakeRoller.*;
import frc.robot.commands.LEDCommands.Animations.*;
import frc.robot.commands.LEDCommands.Colors.*;
import frc.robot.commands.PivotCommmands.Pivot.*;
import frc.robot.commands.PivotCommmands.TRollers.*;
import frc.robot.commands.ShooterCommands.*;
import frc.robot.commands.StorageCommands.*;
//special imports
import frc.robot.commands.AutoIntake;
import frc.robot.commands.AimSub;
import frc.robot.commands.AmpBack;
import frc.robot.commands.AmpPrep;

import frc.robot.commands.AutoAim;
import frc.robot.commands.AutoShoot;
//import frc.robot.commands.LEDCommands.CandleRainbow;
import frc.robot.commands.LEDCommands.CandleOff;
import frc.robot.commands.IntakeCommands.*;

//subsystem and command imports
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
    

  public static int increment = 0;


  private final DriveSubsystem m_drivesubsystem = new DriveSubsystem();
  public final SendableChooser<Command> autoChooser;
  
  // The driver's controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  // The operator's controller
  XboxController m_operatorController = new XboxController(OIConstants.kOperatorControllerPort);


  //The container for the robot. Contains subsystems, OI devices, and commands.
SendableChooser<Command> m_chooser = new SendableChooser<>();

  public RobotContainer() {
    //named command stuff
    NamedCommands.registerCommand("AutoIntake", new AutoIntake(m_intake, m_storage, m_pivot, m_shooter));
    NamedCommands.registerCommand("AutoShoot", new AutoShoot(m_shooter, m_pivot, m_drivesubsystem, m_storage));
    NamedCommands.registerCommand("CandleBlue", new CandleBlue(m_leds));
    NamedCommands.registerCommand("CandleRed", new CandleRed(m_leds));
    NamedCommands.registerCommand("CandleOff", new CandleOff(m_leds));



    configureButtonBindings();



    //Pathplanner auto chooser
    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Mode", autoChooser); 

        
    //Autons
    //SmartDashboard.putData("BlueNoteClear", new PathPlannerAuto("BlueNoteClear"));
    //SmartDashboard.putData("4NoteAuto", new PathPlannerAuto("4NoteAuto"));
    //SmartDashboard.putData("testAuto", new PathPlannerAuto("CommandTest"));

    SmartDashboard.putData("AutonMode", m_chooser);
    SmartDashboard.putData("RightSnap", new rightSnap(m_drivesubsystem));
    SmartDashboard.putData("Drive", m_drivesubsystem);
    SmartDashboard.putBoolean("DriveState", true);

    SmartDashboard.putData("ShooterOut", new ShooterFeed(m_shooter));
    SmartDashboard.putData("ShooterIn", new ShooterBackFeed(m_shooter));
    SmartDashboard.putData("ShooterStop", new ShooterStop(m_shooter));
    
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
    

    SmartDashboard.putData("IntakePivotUp", new IntakePivotUp(m_intake));
    SmartDashboard.putData("IntakePivotDown", new IntakePivotDown(m_intake));
    SmartDashboard.putData("IntakePivotStop", new IntakePivotStop(m_intake));

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

    SmartDashboard.putData("AutoIntake", new AutoIntake(m_intake, m_storage, m_pivot, m_shooter));
    
    SmartDashboard.putData("BarRotateForward", new BarRotateForward(m_bar));
    SmartDashboard.putData("BarRotateBackward", new BarRotateBackward(m_bar));
    SmartDashboard.putData("BarRotateStop", new BarRotateStop(m_bar));
    SmartDashboard.putData("AngleBarRotatorPivot", new AngleBarRotatorPivot(m_bar, 10));

    SmartDashboard.putData("AnglePivot", new AnglePivot(m_pivot)); //m_pivot.calculateAngle()
    SmartDashboard.putData("RevShooter", new RevShooter(m_shooter));//m_shooter.calculateSpeed()
    //SmartDashboard.putData("RevShooter", new RevShooter(m_shooter, .1));
    SmartDashboard.putData("AmpPrep", new AmpPrep(m_bar, m_shooter, m_pivot));
     SmartDashboard.putData("AmpBack", new AmpBack(m_bar, m_shooter, m_pivot, m_storage));
     

    SmartDashboard.putData("PivotSetpoint", new PivotSetpoint(m_pivot, 35)); //m_pivot.calculateAngle()
    SmartDashboard.putData("shooterSetpoint", new shooterSetpoint(m_shooter, .65));//m_shooter.calculateSpeed()

    
    m_chooser.addOption("sDrive", new sDrive(m_drivesubsystem));

    SmartDashboard.putNumber("increment", increment);
    SmartDashboard.putData("turnwhileaim", new AimWhileMoving(m_drivesubsystem));

    SmartDashboard.putData("1/3speed", new DriveSlowmode(m_drivesubsystem));
    SmartDashboard.putData("normalspeed", new DriveNormal(m_drivesubsystem));


    SmartDashboard.putNumber("Pigeon2Heading", m_drivesubsystem.getHeading());
    SmartDashboard.putNumber("Pigeon2Heading", m_drivesubsystem.getHeading());





    
    //double shooterSetSetPoint = SmartDashboard.getNumber("shooterSetSetPoint", getTa());
    //SmartDashboard.putData("revShooter", new revShooter(m_shooter));



   
    
    // Configure default commands
    m_drivesubsystem.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_drivesubsystem.drive(
                -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                true, true), //drivescheme sets either field centric or not
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

    final Trigger AutoAim = new JoystickButton(m_driverController, 1);
    AutoAim.toggleOnTrue(new AutoAim(m_shooter, m_pivot, m_drivesubsystem));

    final Trigger SetX = new JoystickButton(m_driverController, 3); 
    SetX.whileTrue(new xPattern(m_drivesubsystem));
    
    final Trigger resetHeading = new JoystickButton(m_driverController, 4);
    resetHeading.onTrue(new resetHeading(m_drivesubsystem));

    /*Makes chassis top speed lower
    final Trigger SlowMode = new JoystickButton(m_driverController, 5);
    SlowMode.whileTrue(new SlowMode(m_drivesubsystem));*/

    /*makes chassis top speed higher 
    final Trigger BoostMode = new JoystickButton(m_driverController, 6);
    BoostMode.whileTrue(new BoostMode());*/


    // Operator Controller button

    //Deploys Intake, Runs the Intake rollers, Stops rollers when game piece is detected in through beam sensor, and retracts
    final Trigger AutoIntake = new JoystickButton(m_operatorController, 3);
    AutoIntake.whileTrue(new AutoIntake(m_intake, m_storage, m_pivot, m_shooter));

    final Trigger Fire = new JoystickButton(m_operatorController, 6);
    Fire.whileTrue(new StorageRollersShooter(m_storage));
    Fire.whileFalse(new StorageRollersStop(m_storage));

    final Trigger SubwooferOverride = new JoystickButton(m_operatorController, 2);
    SubwooferOverride.whileTrue(new AimSub(m_shooter, m_pivot, m_drivesubsystem));

    final Trigger Amp = new JoystickButton(m_operatorController, 5);
    Amp.toggleOnTrue(new AmpPrep(m_bar, m_shooter, m_pivot));
    Amp.toggleOnFalse(new AmpBack(m_bar, m_shooter, m_pivot, m_storage));
    

    final Trigger EleUp = new JoystickButton(m_operatorController, 4);
    EleUp.whileTrue(new ElevatorUp(m_elevator));

    final Trigger EleDown = new JoystickButton(m_operatorController, 1);
    EleDown.whileTrue(new ElevatorDown(m_elevator));

    
  
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
    return autoChooser.getSelected();  
  }
}
