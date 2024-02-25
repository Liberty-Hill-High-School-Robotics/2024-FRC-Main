package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.PivotCommmands.Pivot.AnglePivot;
import frc.robot.commands.ShooterCommands.RevShooter;
import frc.robot.commands.StorageCommands.FeedNoteAuto;
//import frc.robot.subsystems.Bar;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;


/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class AutoShoot extends SequentialCommandGroup {
    public AutoShoot(
    
        Shooter shooter,
        Pivot pivot,
        Storage storage

    ){
 
    addCommands(
        
            new ParallelCommandGroup(
                new AnglePivot(pivot),
                new RevShooter(shooter),
                new FeedNoteAuto(storage)
            )    
        
    );
    }
    
    @Override
    public boolean runsWhenDisabled() {
        return false;

    }

    
}