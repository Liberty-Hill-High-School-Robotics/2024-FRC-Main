package frc.robot.subsystems;
//CANdle has yet to be ported to Phoenix6, so for now use the Phoenix5 imports and libraries
import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.RainbowAnimation;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ColorConstants;


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
        int r;
        int g;
        int b;

        //switch statement
        if(color == "purple"){
            r = ColorConstants.purple[0]; 
            g = ColorConstants.purple[1]; 
            b = ColorConstants.purple[2]; 
        }


        else if(color == "gold"){
            r = ColorConstants.gold[0]; 
            g = ColorConstants.gold[1]; 
            b = ColorConstants.gold[2]; 
        }

        else if(color == "orange"){
            r = ColorConstants.orange[0]; 
            g = ColorConstants.orange[1]; 
            b = ColorConstants.orange[2]; 
        }


        else if(color == "blue"){
            r = ColorConstants.blue[0]; 
            g = ColorConstants.blue[1]; 
            b = ColorConstants.blue[2]; 
        }


        else if(color == "purple"){
            r = ColorConstants.red[0]; 
            g = ColorConstants.red[1]; 
            b = ColorConstants.red[2]; 
        }

        else if(color == "purple"){
            r = ColorConstants.green[0]; 
            g = ColorConstants.green[1]; 
            b = ColorConstants.green[2];
        }     

        else{
            r = 255;
            g = 255;
            b = 255;
        }

        //set color
        candle.setLEDs(r, g, b);
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

