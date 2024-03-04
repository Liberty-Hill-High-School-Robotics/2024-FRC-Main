package frc.robot;

import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */


public final class Constants {

  public static final class ColorConstants{
    public static int[] purple = new int[]{80, 3, 143}; 
    public static int[] gold   = new int[]{255, 145, 0}; 
    public static int[] orange = new int[]{255, 145, 0}; 
    public static int[] blue   = new int[]{0, 0, 255}; 
    public static int[] red    = new int[]{255, 0, 0}; 
    public static int[] green  = new int[]{0, 255, 0};

    public static int numLEDs = 28;
  }

  public static final class ShooterConstants{
    public static boolean canSee;
    public static boolean shooterAtSpeed;
    public static final double shooterError = .05;
    public static final double sP = 0.001;
    public static final double sI = 0.0;
    public static final double sD = 0;

    public static final double Slope = .05;

    public static final double ApTagHeight = 53.5; //height of the BOTTOM of the apriltags from the ground (speaker tags) in inches
    public static final double CamHeight = 21.00; //in inches
    public static final double CamAngle = 35.00; //in degrees

    public static final double sCalcC = .29546;
    public static final double sCalucP = .18749;
  }

  public static final class PivotConstants{
    public static final double pP = 0.02; //0075
    public static final double pI = 0;
    public static final double pD = 0;

    public static final double Slope = 0.05; //angle subtracted with each foot the robot is away from the sub

    public static final double pCalcC = 1740;
    public static final double pCalucP = -0.95;
    public static final double pCaluK = -0.8;

  }
  public static final class BarConstants{
    public static final double bP = 0.05;
    public static final double bI = 0.01;
    public static final double bD = 0;
    public final static float fLimit = 22;

  }
  public static final class IntakeConstants{
    public final static float fLimit = 5;

  }
  public static final class MotorSpeeds{
    //all speeds should be a double
    public static final double elevatorSpeed = .6;
    public static final double pivotIntakeSpeed = .25;
    public static final double groundRollerSpeed = 1;
    public static final double groundRollerBackFeedSpeed = .1;
    public static final double transferRollerSpeed = 1;
    public static final double pivotSpeed = .15;
    public static final double shooterSpeed = .2;
    public static final double storageRollerSpeed = .89;
    public static final double barRotatorSpeed = .2;

    
  }
  public static final class CanIDs{
    //Motor IDs
    public static final int elevatorMotorID = 10;
    public static final int elevatorMotor2ID = 11;
    public static final int pivotIntakeID = 12;
    public static final int groundRollerID = 13;
    public static final int transferRollerID = 14;
    public static final int pivotMotorID = 15;
    public static final int pivotMotor2ID = 16;
    public static final int shooterMotorID = 17;
    public static final int shooterMotor2ID = 18;
    public static final int storageRollerMotorID = 19;
    public static final int barRotatorID = 20;

    public static double limelightAdjust;


  }

  public static final class DriveConstants {

    public static boolean driveScheme = true;
    public static double driveRatio = 1;

    public static final double tP = 0.1;
    public static final double tI = 0;
    public static final double tD = 0;

    
    // Driving Parameters - Note that these are not the maximum capable speeds of
    // the robot, rather the allowed maximum speeds
    public static final double kMaxSpeedMetersPerSecond = 4.8;
    public static final double kMaxAngularSpeed = 2 * Math.PI; // radians per second

    public static final double kDirectionSlewRate = 1.2; // radians per second // 1.2 
    public static final double kMagnitudeSlewRate = 1.8; // percent per second (1 = 100%) 
    public static final double kRotationalSlewRate = 1.8; // percent per second (1 = 100%) //2

    // Chassis configuration
    public static final double kTrackWidth = Units.inchesToMeters(23);
    // Distance between centers of right and left wheels on robot
    public static final double kWheelBase = Units.inchesToMeters(23);
    // Distance between front and back wheels on robot
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
        new Translation2d(kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));

        //creates an array to be used by the swervedrivekinematics.class
    public static final Translation2d[] Module_Info = {
      new Translation2d(kWheelBase / 2, kTrackWidth / 2),
      new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
      new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
      new Translation2d(-kWheelBase / 2, -kTrackWidth / 2)
    };
    public static final SwerveDriveKinematics KINEMATICS = new SwerveDriveKinematics(Module_Info);
      //creates the location for the drivesubsystem to run a command to set drivestates

    // Angular offsets of the modules relative to the chassis in radians
    public static final double kFrontLeftChassisAngularOffset = -Math.PI / 2;
    public static final double kFrontRightChassisAngularOffset = 0;
    public static final double kBackLeftChassisAngularOffset = Math.PI;
    public static final double kBackRightChassisAngularOffset = Math.PI / 2;

    // SPARK MAX CAN IDs
    public static final int kFrontLeftDrivingCanId = 6;
    public static final int kRearLeftDrivingCanId = 8;
    public static final int kFrontRightDrivingCanId = 4;
    public static final int kRearRightDrivingCanId = 2;

    public static final int kFrontLeftTurningCanId = 5;
    public static final int kRearLeftTurningCanId = 7;
    public static final int kFrontRightTurningCanId = 3;
    public static final int kRearRightTurningCanId = 1;

    public static final boolean kGyroReversed = false;
  }

  public static final class ModuleConstants {
    // The MAXSwerve module can be configured with one of three pinion gears: 12T, 13T, or 14T.
    // This changes the drive speed of the module (a pinion gear with more teeth will result in a
    // robot that drives faster).
    public static final int kDrivingMotorPinionTeeth = 14;

    // Invert the turning encoder, since the output shaft rotates in the opposite direction of
    // the steering motor in the MAXSwerve Module.
    public static final boolean kTurningEncoderInverted = true;

    // Calculations required for driving motor conversion factors and feed forward
    public static final double kDrivingMotorFreeSpeedRps = NeoMotorConstants.kFreeSpeedRpm / 60;
    public static final double kWheelDiameterMeters = 0.0762;
    public static final double kWheelCircumferenceMeters = kWheelDiameterMeters * Math.PI;
    // 45 teeth on the wheel's bevel gear, 22 teeth on the first-stage spur gear, 15 teeth on the bevel pinion
    public static final double kDrivingMotorReduction = (45.0 * 22) / (kDrivingMotorPinionTeeth * 15);
    public static final double kDriveWheelFreeSpeedRps = (kDrivingMotorFreeSpeedRps * kWheelCircumferenceMeters)
        / kDrivingMotorReduction;

    public static final double kDrivingEncoderPositionFactor = (kWheelDiameterMeters * Math.PI)
        / kDrivingMotorReduction; // meters
    public static final double kDrivingEncoderVelocityFactor = ((kWheelDiameterMeters * Math.PI)
        / kDrivingMotorReduction) / 60.0; // meters per second

    public static final double kTurningEncoderPositionFactor = (2 * Math.PI); // radians
    public static final double kTurningEncoderVelocityFactor = (2 * Math.PI) / 60.0; // radians per second

    public static final double kTurningEncoderPositionPIDMinInput = 0; // radians
    public static final double kTurningEncoderPositionPIDMaxInput = kTurningEncoderPositionFactor; // radians

    public static final double kDrivingP = 0.001; //.04
    public static final double kDrivingI = 0.0;
    public static final double kDrivingD = 0;
    public static final double kDrivingFF = 1 / kDriveWheelFreeSpeedRps;
    public static final double kDrivingMinOutput = -1;
    public static final double kDrivingMaxOutput = 1;

    public static final double kTurningP = 1;
    public static final double kTurningI = 0;
    public static final double kTurningD = 0;
    public static final double kTurningFF = 0;
    public static final double kTurningMinOutput = -1;
    public static final double kTurningMaxOutput = 1;

    public static final IdleMode kDrivingMotorIdleMode = IdleMode.kBrake;
    public static final IdleMode kTurningMotorIdleMode = IdleMode.kBrake;

    public static final int kDrivingMotorCurrentLimit = 80; // amps
    public static final int kTurningMotorCurrentLimit = 20; // amps
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
    public static final double kDriveDeadband = 0.3;
  }

  public static final class AutoConstants {
    public static final double kMaxSpeedMetersPerSecond = 3;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;
    public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
    public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

    public static final double kPXController = 1;
    public static final double kPYController = 1;
    public static final double kPThetaController = 1;

    // Constraint for the motion profiled robot angle controller
    public static final TrapezoidProfile.Constraints kThetaControllerConstraints = new TrapezoidProfile.Constraints(
        kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
  }

  public static final class NeoMotorConstants {
    public static final double kFreeSpeedRpm = 6784;
  }
}