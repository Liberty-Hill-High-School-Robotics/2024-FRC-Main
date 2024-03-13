package frc.robot.commands.SemiAutonomousCommands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
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
public class AutoIntakeTimeout extends ParallelRaceGroup {


    public AutoIntakeTimeout(
        Intake m_intake,
        Storage m_storage,
        Pivot m_pivot
    ){
    
        addCommands(
            new ParallelRaceGroup(
            //want to change this to a parallel deadline group, which ends once a certain command ends, but cant figure out the syntax for it yet
            //run all commands in parallel until the throughbeam == true
            new StorageRollersFeed(m_storage).withTimeout(1),
            new IntakeTogether(m_intake).withTimeout(1),
            new AngleAndFeed(m_pivot).withTimeout(1)
            //new CandleStrobeBlue(m_leds).withTimeout(.65).andThen(new CandleRainbow(m_leds))
            )

        );

        }


    @Override
    public boolean runsWhenDisabled() {

        return false;
    }
}