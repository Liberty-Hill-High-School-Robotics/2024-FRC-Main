package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveAutonCommands.AimWhileMoving;
import frc.robot.commands.PivotCommmands.Pivot.AngleSub;
import frc.robot.commands.ShooterCommands.RevShooterSub;
//import frc.robot.subsystems.Bar;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Shooter;


/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class AimSub extends SequentialCommandGroup {
    public AimSub(
    
        Shooter shooter,
        Pivot pivot,
        DriveSubsystem drivesubsystem

    ){
    
    addCommands(
        
            new ParallelCommandGroup(
                new AngleSub(pivot),
                new RevShooterSub(shooter),
                new AimWhileMoving(drivesubsystem)
            )    
        
    );
    }
    
    @Override
    public boolean runsWhenDisabled() {
        return false;
    }

    
}