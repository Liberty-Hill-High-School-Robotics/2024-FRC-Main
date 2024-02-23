package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.BarCommands.AngleBarRotatorPivot;
import frc.robot.commands.BarCommands.BarRotateForward;
import frc.robot.commands.ShooterCommands.shooterSetpoint;
import frc.robot.commands.PivotCommmands.Pivot.PivotSetpoint;
import frc.robot.subsystems.Bar;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Shooter;


/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class AmpPrep extends SequentialCommandGroup {
    public AmpPrep(
        Bar bar,
        Shooter shooter,
        Pivot pivot
        
    ){
    
    addCommands( 
        
            new ParallelCommandGroup(
                 
                new BarRotateForward(bar),
                new WaitCommand(4),
                new PivotSetpoint(pivot, 39),
                new shooterSetpoint(shooter, .1)
                )
    );
    }
    
    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}