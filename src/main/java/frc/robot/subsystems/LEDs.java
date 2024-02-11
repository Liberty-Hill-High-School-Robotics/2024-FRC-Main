package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ColorConstants;

//CANdle has yet to be ported to Phoenix6, so for now use the Phoenix5 imports and libraries
import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.RainbowAnimation;


public class LEDs extends SubsystemBase {
    //CANdle colors
        //purple = 80, 45, 127
        //gold = 255, 200, 46
        //orange = 255, 145, 0
        //blue = 0, 0, 255
        //red = 255, 0, 0
        //green = 0, 255, 0


    CANdle candle = new CANdle(20);
    //create a rainbow anim.
    RainbowAnimation rainbowAnimation = new RainbowAnimation(1, 1, 0);

    private boolean dashboardPurple = false;
    private boolean dashboardGold = false;


    public LEDs() {
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putBoolean("LEDpurple", dashboardPurple);
        SmartDashboard.putBoolean("LEDgold", dashboardGold);
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation
    }

    //----------------------------------------------------------------------------------------------------

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void candleSetColor(String color){
        //set brightness
        candle.configBrightnessScalar(100);

        //set color
        candle.setLEDs(ColorConstants.color[0], ColorConstants.color[1], ColorConstants.color[2]);
    }


    public void candleOff(){
        //set to an idle configuration
        candle.configBrightnessScalar(0);
        candle.setLEDs(255, 255, 255);

        dashboardGold = false;
        dashboardPurple = false;
    }


    public void candleRainbow(){
        //not yet working
        candle.configBrightnessScalar(1);
        candle.animate(rainbowAnimation);
    }
}

