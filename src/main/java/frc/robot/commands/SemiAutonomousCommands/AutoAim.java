package frc.robot.commands.SemiAutonomousCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveAutonCommands.AimWhileMoving;
import frc.robot.commands.LEDCommands.Animations.CandleLarson;
import frc.robot.commands.LEDCommands.Animations.CandleStrobeRed;
import frc.robot.commands.PivotCommmands.Pivot.AnglePivot;
import frc.robot.commands.ShooterCommands.RevShooter;
//import frc.robot.subsystems.Bar;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LEDs;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Shooter;


/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class AutoAim extends SequentialCommandGroup {
    public AutoAim(
    
        Shooter shooter,
        Pivot pivot,
        DriveSubsystem drivesubsystem,
        LEDs m_leds

    ){
 
    addCommands(
        
            new SequentialCommandGroup(
                new ParallelCommandGroup(
                new AnglePivot(pivot),
                new RevShooter(shooter),
                new AimWhileMoving(drivesubsystem),
                new CandleStrobeRed(m_leds)
                ),
                new CandleLarson(m_leds)
            )     
    );
    }
    
    @Override
    public boolean runsWhenDisabled() {
        return false;

    }

    
}