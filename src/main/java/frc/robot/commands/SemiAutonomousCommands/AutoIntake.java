package frc.robot.commands.SemiAutonomousCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
/*
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.IntakeCommands.IntakePivot.IntakePivotDown;
import frc.robot.commands.IntakeCommands.IntakeRoller.IntakeRollerFeed;
import frc.robot.commands.PivotCommmands.TRollers.TRollerFeed;
import frc.robot.commands.StorageCommands.StorageRollersFeed;
*/
import frc.robot.commands.IntakeCommands.IntakeTogether;
import frc.robot.commands.PivotCommmands.Pivot.AngleAndFeed;
import frc.robot.commands.StorageCommands.StorageRollersFeed;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Storage;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class AutoIntake extends ParallelCommandGroup {


    public AutoIntake(
        Intake m_intake,
        Storage m_storage,
        Pivot m_pivot
    ){
    
        addCommands(
            //want to change this to a parallel deadline group, which ends once a certain command ends, but cant figure out the syntax for it yet
            //run all commands in parallel until the throughbeam == true
            new ParallelCommandGroup(
            new IntakeTogether(m_intake),
            new AngleAndFeed(m_pivot),
            new StorageRollersFeed(m_storage)
            )

        );

        }


    @Override
    public boolean runsWhenDisabled() {

        return false;
    }
}