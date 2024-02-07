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
//import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.RobotContainer;
import static java.lang.Math.*;
/*
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.Command;
*/
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class Limelight extends SubsystemBase {
 

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
    }
    


    @Override
    public void simulationPeriodic() {
        
    }

    public int getDistance(){
        //takes distance (currently subed for .getTa), and rounds it to the nearest whole number (casted to an int because round returns long)
        int distance =  (int) round(RobotContainer.getTa());
        return distance;
    }

}
