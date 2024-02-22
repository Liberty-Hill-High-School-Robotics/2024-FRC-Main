package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.PivotCommmands.Pivot.AnglePivot;
import frc.robot.commands.ShooterCommands.RevShooter;
import frc.robot.commands.StorageCommands.StorageRollersShooter;
//import frc.robot.subsystems.Bar;
import frc.robot.subsystems.DriveSubsystem;
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
        DriveSubsystem drivesubsystem,
        Storage storage

    ){
 
    addCommands(
        
            new ParallelCommandGroup(
                new AnglePivot(pivot),
                new RevShooter(shooter),
                new WaitCommand(.25),
                new StorageRollersShooter(storage)
            )    
        
    );
    }
    
    @Override
    public boolean runsWhenDisabled() {
        return false;

    }

    
}