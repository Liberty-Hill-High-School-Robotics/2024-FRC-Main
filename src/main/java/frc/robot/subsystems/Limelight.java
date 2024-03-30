package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.RobotContainer;
import static java.lang.Math.*;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Limelight extends SubsystemBase {

    public static boolean canSee;
    static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");



    @Override
    public void periodic() {
        //read values periodically
        double x = RobotContainer.getTx();
        double y = RobotContainer.getTy();
        double area = RobotContainer.getTa();
        if(area == 0){
            canSee = false;
        }
        else{
            canSee = true;
        }
        //boolean tv = RobotContainer.getTv();

        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putNumber("GetDistance", getDistance());
        SmartDashboard.putBoolean("cansee?", canSee);

        

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
        return distance;
   }
}