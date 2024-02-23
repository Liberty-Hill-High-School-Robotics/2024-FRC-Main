package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.BarCommands.AngleBarRotatorPivot;
import frc.robot.commands.BarCommands.BarRotateBackward;
import frc.robot.commands.BarCommands.BarRotateForward;
import frc.robot.commands.ShooterCommands.shooterSetpoint;
import frc.robot.commands.StorageCommands.StorageRollersShooter;
import frc.robot.commands.PivotCommmands.Pivot.PivotSetpoint;
import frc.robot.commands.PivotCommmands.Pivot.PivotWithBar;
import frc.robot.subsystems.Bar;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;


/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class AmpBack extends SequentialCommandGroup {
    public AmpBack(
        Bar bar,
        Shooter shooter,
        Pivot pivot,
        Storage storage
        
    ){
    
    addCommands( 
            
            
            new ParallelCommandGroup(
                 
                new BarRotateBackward(bar),
                new PivotWithBar(pivot, 0)
                
                )
    );
    }
    
    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}