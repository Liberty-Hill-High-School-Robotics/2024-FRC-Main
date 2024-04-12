package frc.robot.commands.ElevatorCommands;

import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class ElevatorConstant extends Command {
  // The subsystem the command runs on
  private final Elevator m_elevator;

  public ElevatorConstant(Elevator subsystem) {
    m_elevator = subsystem;
    addRequirements(m_elevator);
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute(){
    m_elevator.elevatorConstant();
  }

  @Override 
  public void end(boolean interrupted){
    m_elevator.elevatorStop();
   }

  @Override
  public boolean isFinished() {
    return m_elevator.elevatorAtReverseLimit();
  }
}