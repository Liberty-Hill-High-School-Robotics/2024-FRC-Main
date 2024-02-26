package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.PivotCommmands.Pivot.AnglePivot;
import frc.robot.commands.ShooterCommands.RevShooter;
import frc.robot.commands.StorageCommands.FeedNoteAuto;
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
        
            new ParallelRaceGroup(
                new AnglePivot(pivot).withTimeout(2.5),
                new RevShooter(shooter),
                new FeedNoteAuto(storage).onlyWhile(shooter::atSpeed)
                //also try .onlyWhile(()->{return Shooter.isatspeed;} (uses a boolean instead)
            )    
        
    );
    }
    
    @Override
    public boolean runsWhenDisabled() {
        return false;

    }

    
}