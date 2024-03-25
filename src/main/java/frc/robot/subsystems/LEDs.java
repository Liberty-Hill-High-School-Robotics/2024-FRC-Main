package frc.robot.subsystems;
//CANdle has yet to be ported to Phoenix6, so for now use the Phoenix5 imports and libraries
import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.CANdle.LEDStripType;
import com.ctre.phoenix.led.LarsonAnimation.BounceMode;
import com.ctre.phoenix.led.CANdleConfiguration;
import com.ctre.phoenix.led.ColorFlowAnimation;
import com.ctre.phoenix.led.FireAnimation;
import com.ctre.phoenix.led.LarsonAnimation;
import com.ctre.phoenix.led.RainbowAnimation;
import com.ctre.phoenix.led.RgbFadeAnimation;
import com.ctre.phoenix.led.SingleFadeAnimation;
import com.ctre.phoenix.led.StrobeAnimation;
import com.ctre.phoenix.led.TwinkleAnimation;
import com.ctre.phoenix.led.TwinkleOffAnimation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ColorConstants;


public class LEDs extends SubsystemBase {

    CANdle candle = new CANdle(20);

    private int r;
    private int g;
    private int b;

    //create some animations for the LEDs, all are purple for now (with exceptions)
    //rainbow/set color animations
    RainbowAnimation rainbowAnimation = new RainbowAnimation(1, .5, ColorConstants.numLEDs);

    FireAnimation fireAnimation = new FireAnimation();

    RgbFadeAnimation rgbFadeAnimation = new RgbFadeAnimation();

    //set color animations
    LarsonAnimation larsonAnimation = new LarsonAnimation            (ColorConstants.purple[0], ColorConstants.purple[1], ColorConstants.purple[2], 0, .8, ColorConstants.numLEDs, BounceMode.Center, 10);
    //https://www.youtube.com/watch?v=Ns0f6jZIo9I

    ColorFlowAnimation colorFlowAnimation = new ColorFlowAnimation   (ColorConstants.purple[0], ColorConstants.purple[1], ColorConstants.purple[2]);
    //Animation that gradually lights the entire LED strip one LED at a time.

    SingleFadeAnimation singleFadeAnimation = new SingleFadeAnimation(ColorConstants.purple[0], ColorConstants.purple[1], ColorConstants.purple[2], 0, .1, ColorConstants.numLEDs);
    //Animation that fades into and out of a specified color 

    StrobeAnimation strobeAnimation = new StrobeAnimation            (ColorConstants.purple[0], ColorConstants.purple[1], ColorConstants.purple[2], 0, .5, ColorConstants.numLEDs);
    StrobeAnimation greenstrobe = new StrobeAnimation                (0, 255, 0, 0, .5, ColorConstants.numLEDs);
    StrobeAnimation redstrobe = new StrobeAnimation                  (255, 0, 0, 0, .5, ColorConstants.numLEDs);
    StrobeAnimation bluestrobe = new StrobeAnimation                (0, 0, 0, 255, .5, ColorConstants.numLEDs);
    //Animation that strobes the LEDs a specified color  

    TwinkleAnimation twinkleAnimation = new TwinkleAnimation         (ColorConstants.purple[0], ColorConstants.purple[1], ColorConstants.purple[2]);
    //Animation that randomly turns LEDs on and off to a certain color

    TwinkleOffAnimation twinkleOffAnimation = new TwinkleOffAnimation(ColorConstants.purple[0], ColorConstants.purple[1], ColorConstants.purple[2]);
    //Animation that randomly turns on LEDs, until it reaches the maximum count and turns them all off 

    public LEDs() {
        //
        //
        //
        //https://store.ctr-electronics.com/content/api/java/html/classcom_1_1ctre_1_1phoenix_1_1led_1_1_c_a_ndle.html
        //
        //
        //
        CANdleConfiguration configAll = new CANdleConfiguration();
        configAll.statusLedOffWhenActive = true;
        configAll.disableWhenLOS = false;
        configAll.stripType = LEDStripType.RGB;
        candle.configAllSettings(configAll);

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("redvalue()", r);
        SmartDashboard.putNumber("greenvalue()", g);
        SmartDashboard.putNumber("bluevalue()", b);
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
        candle.configBrightnessScalar(1);
        candle.clearAnimation(1);

        //switch statement
        if(color == "purple"){
            r = ColorConstants.purple[0]; 
            g = ColorConstants.purple[1]; 
            b = ColorConstants.purple[2]; 
        }

        if(color == "dimpurple"){
            r = ColorConstants.purple[0]; 
            g = ColorConstants.purple[1]; 
            b = ColorConstants.purple[2];
            candle.configBrightnessScalar(.4);
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


        else if(color == "red"){
            r = ColorConstants.red[0]; 
            g = ColorConstants.red[1]; 
            b = ColorConstants.red[2]; 
        }

        else if(color == "green"){
            r = ColorConstants.green[0]; 
            g = ColorConstants.green[1]; 
            b = ColorConstants.green[2];
        }     

        else {
            candle.configBrightnessScalar(0);
            r = 255;
            g = 255;
            b = 255;
        }

        //set color
        candle.setLEDs(r, g, b);
    }



    public void candleSetAnimation(String animation){
        //set animation based on given string.=
        candle.configBrightnessScalar(1);
        candle.clearAnimation(1);

        if(animation == "rainbow"){
           candle.animate(rainbowAnimation, 1);
        }

        else if(animation == "fire"){
           candle.animate(fireAnimation, 1);
        }

        else if(animation == "rgbfade"){
           candle.animate(rgbFadeAnimation, 1);
        }

        else if(animation == "larson"){
           candle.animate(larsonAnimation, 1);
        }

        else if(animation == "colorflow"){
           candle.animate(colorFlowAnimation, 1);
        }

        else if(animation == "singlefade"){
           candle.animate(singleFadeAnimation, 1);
        }

        else if(animation == "strobe"){
           candle.animate(strobeAnimation, 1);
        }

        else if(animation == "twinkle"){
           candle.animate(twinkleAnimation, 1);
        }

        else if(animation == "twinkleoff"){
           candle.animate(twinkleOffAnimation, 1);
        }

        else if(animation == "greenstrobe"){
            candle.animate(greenstrobe, 1);
        }

        else if(animation == "redstrobe"){
            candle.animate(redstrobe, 1);
        }

        else if(animation == "bluestrobe"){
            candle.animate(bluestrobe, 1);
        }
    }


    public void candleClear(){
        candle.clearAnimation(1);
        candle.configBrightnessScalar(0);
    }
}