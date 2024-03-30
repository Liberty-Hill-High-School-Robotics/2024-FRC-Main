package frc.robot.commands.SemiAutonomousCommands;

//import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.LEDCommands.Animations.CandleStrobeRed;
import frc.robot.commands.PivotCommmands.Pivot.AnglePivot;
import frc.robot.commands.ShooterCommands.RevShooter;
import frc.robot.subsystems.LEDs;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Shooter;


/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class AutoRev extends SequentialCommandGroup {
    public AutoRev(
    
        Shooter shooter,
        Pivot pivot,
        LEDs leds
    ){
 
    addCommands(
        
            new ParallelCommandGroup(
                new AnglePivot(pivot).withTimeout(1),
                new RevShooter(shooter).withTimeout(1),
                new CandleStrobeRed(leds).withTimeout(1)
                )
            );
    }
    
    @Override
    public boolean runsWhenDisabled() {
        return false;

    }

    
}