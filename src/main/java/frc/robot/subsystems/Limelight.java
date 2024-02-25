package frc.robot.subsystems;


//unused imports

/*
import java.util.List;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
*/
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
//import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.RobotContainer;
import static java.lang.Math.*;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
/*
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.Command;
*/
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class Limelight extends SubsystemBase {
    public static int increment;
    static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");



    @Override
    public void periodic() {
        //read values periodically
        double x = RobotContainer.getTx();
        double y = RobotContainer.getTy();
        double area = RobotContainer.getTa();

        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putNumber("GetDistance", getDistance());
        SmartDashboard.putNumber("increment", increment);



    }
    


    @Override
    public void simulationPeriodic() {
        
    }

    public static int roundDistance(){
        //takes distance (currently subed for .getTa), and rounds it to the nearest whole number (casted to an int because round returns long)
        int distance = (int) round(getDistance());
        return distance;
    }

    public static double getDistance(){
    //https://docs.wpilib.org/en/latest/docs/software/vision-processing/introduction/identifying-and-processing-the-targets.html#distance
    //uses this equation ^
    //distance = (targetheight - cameraheight) / tan(cameraangle + Ty)
    var y = table.getEntry("ty");
    double distance = (ShooterConstants.ApTagHeight - ShooterConstants.CamHeight) / Math.tan((ShooterConstants.CamAngle + (y.getDouble(0))) * (Math.PI/180));
    increment++;
    return distance;
   }

   public static boolean isTarget(){
    var target = table.getEntry("tv");
    boolean value = target.getBoolean(isTarget());
    return value;
   }


}
