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

  public static final class ShooterConstants{
    public static final double sP = 0.01;
    public static final double sI = 0;
    public static final double sD = 0;

    public static final double Slope = .05;

    public static final double ApTagHeight = 36.875; //height of the BOTTOM of the apriltags from the ground (speaker tags) in inches
    public static final double CamHeight = 7; //in inches
    public static final double CamAngle = 30; //in degrees

    public enum ShootingPosition {
      //create an enum list of shooting posistions (one every foot until 20 feet away)
      F0       (0), 
      F1       (1),   //Shoot from x number of feet away from subwoofer 
      F2       (2),     
      F3       (3),      
      F4       (4),      
      F5       (5),    
      F6       (6),    
      F7       (7),    
      F8       (8),     
      F9       (9),     
      F10      (10),     
      F11      (11),    
      F12      (12),  
      F13      (13),   
      F14      (14),   
      F15      (15),  
      F16      (16),     
      F17      (17),   
      F18      (18),   
      F19      (19),      
      F20      (20),      

      ERROR    (21); //error value in case of, well, error

      private final int shootingPositionIndex; //create the integer for the shooting posistion, from 0-21, each one represents an index in the above enum
    
      ShootingPosition(int shootingPositionIndex) {
        this.shootingPositionIndex = shootingPositionIndex; //assign the index to ShootingPosition
      }
        
      public int getShootingPositionIndex() {
        return this.shootingPositionIndex; //returns shooting pos. when called
      } 
    }

    private double[][] shootingSpeeds = {
      //create an array for shooting speeds at set points, each entry is aligned with each index point
      //TODO: tune shooter values
      {.5, 50}, //F0
      {.5, 50}, //F1
      {.5, 50}, //F2
      {.5, 50}, //F3
      {.5, 50}, //F4
      {.5, 50}, //F5
      {.5, 50}, //F6
      {.5, 50}, //F7
      {.5, 50}, //F8
      {.5, 50}, //F9
      {.5, 50}, //F10
      {.5, 50}, //F11
      {.5, 50}, //F12
      {.5, 50}, //F13
      {.5, 50}, //F14
      {.5, 50}, //F15
      {.5, 50}, //F16
      {.5, 50}, //F17
      {.5, 50}, //F18
      {.5, 50}, //F19
      {.5, 50}, //F20
      {.5, 50}, //ERROR
    };

    public ShootingPosition getPositionFromDistance(int distance){ // a series of if statements to determine which index point to use for the shooter
      ShootingPosition distancePosition = ShootingPosition.ERROR; //set pos. to error for troubleshooting

      //just a bunch of if statements choosing a shooting pos.
      //distance needs to be an int, use .round() command to round the calculated distance from the limelight
      if (distance == 0)  distancePosition = ShootingPosition.F0;
      if (distance == 1)  distancePosition = ShootingPosition.F1;
      if (distance == 2)  distancePosition = ShootingPosition.F2;
      if (distance == 3)  distancePosition = ShootingPosition.F3;
      if (distance == 4)  distancePosition = ShootingPosition.F4;
      if (distance == 5)  distancePosition = ShootingPosition.F5;
      if (distance == 6)  distancePosition = ShootingPosition.F6;
      if (distance == 7)  distancePosition = ShootingPosition.F7;
      if (distance == 8)  distancePosition = ShootingPosition.F8;
      if (distance == 9)  distancePosition = ShootingPosition.F9;
      if (distance == 10) distancePosition = ShootingPosition.F10;
      if (distance == 11) distancePosition = ShootingPosition.F11;
      if (distance == 12) distancePosition = ShootingPosition.F12;
      if (distance == 13) distancePosition = ShootingPosition.F13;
      if (distance == 14) distancePosition = ShootingPosition.F14;
      if (distance == 15) distancePosition = ShootingPosition.F15;
      if (distance == 16) distancePosition = ShootingPosition.F16;
      if (distance == 17) distancePosition = ShootingPosition.F17;
      if (distance == 18) distancePosition = ShootingPosition.F18;
      if (distance == 19) distancePosition = ShootingPosition.F19;
      if (distance == 20) distancePosition = ShootingPosition.F20;

      return distancePosition;
    }

    public double getShootingSpeed(ShootingPosition position){
      //returns current index of the shooter, although i think this wont work currently
      //TODO: fix or test this command
      double speed = shootingSpeeds[position.getShootingPositionIndex()][position.getShootingPositionIndex()];
      return speed;
    }
  }

  public static final class PivotConstants{
    public static final double pP = 0.01;
    public static final double pI = 0;
    public static final double pD = 0;

    public static final double Slope = 0; //angle subtracted with each foot the robot is away from the sub



  }
  public static final class IntakeConstants{
    public final static float fLimit = 5;

  }
  public static final class MotorSpeeds{
    //all speeds should be a double
    public static final double elevatorSpeed = .5;
    public static final double pivotIntakeSpeed = .5;
    public static final double groundRollerSpeed = .5;
    public static final double transferRollerSpeed = .5;
    public static final double pivotSpeed = .5;
    public static final double shooterSpeed = .75;
    public static final double storageRollerSpeed = .5;
    public static final double barRotatorSpeed = .5;

    
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
    //whether the robot is field centric or not, used to change control with a button

    
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

    public static final int kDrivingMotorCurrentLimit = 60; // amps
    public static final int kTurningMotorCurrentLimit = 20; // amps
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
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
