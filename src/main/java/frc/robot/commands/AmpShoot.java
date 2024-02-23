package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ShooterCommands.shooterSetpoint;
import frc.robot.commands.StorageCommands.StorageRollersShooter;
import frc.robot.subsystems.Bar;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;


/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class AmpShoot extends SequentialCommandGroup {
    public AmpShoot(
        Bar bar,
        Shooter shooter,
        Pivot pivot,
        Storage storage
        
    ){
    
    addCommands( 
        
        new AmpPrep(bar, shooter, pivot),
        new shooterSetpoint(shooter, .1),
        new ParallelCommandGroup(
            new shooterSetpoint(shooter, .1),
            new StorageRollersShooter(storage)
        ),

        new AmpBack(bar, shooter, pivot, storage)

            
           
    );
    }
    
    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}