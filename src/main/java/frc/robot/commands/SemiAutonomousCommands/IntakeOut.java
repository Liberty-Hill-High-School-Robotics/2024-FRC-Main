package frc.robot.commands.SemiAutonomousCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
/*
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.IntakeCommands.IntakePivot.IntakePivotDown;
import frc.robot.commands.IntakeCommands.IntakeRoller.IntakeRollerFeed;
import frc.robot.commands.PivotCommmands.TRollers.TRollerFeed;
import frc.robot.commands.StorageCommands.StorageRollersFeed;
*/
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IntakeCommands.IntakeRoller.IntakeRollerBackFeed;
import frc.robot.commands.PivotCommmands.TRollers.TRollerBackFeed;
import frc.robot.commands.StorageCommands.StorageRollersBackFeed;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Storage;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class IntakeOut extends SequentialCommandGroup {


    public IntakeOut(
        Intake m_intake,
        Storage m_storage,
        Pivot m_pivot
    ){
    
        addCommands(
            new ParallelCommandGroup(
            //want to change this to a parallel deadline group, which ends once a certain command ends, but cant figure out the syntax for it yet
            //run all commands in parallel until the throughbeam == true
            new IntakeRollerBackFeed(m_intake),
            new TRollerBackFeed(m_pivot),
            new StorageRollersBackFeed(m_storage)
            )

        );

        }


    @Override
    public boolean runsWhenDisabled() {

        return false;
    }
}