package frc.robot.commands.LEDCommands.Colors;

import frc.robot.subsystems.LEDs;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class CandleRed extends Command {
  // The subsystem the command runs on
  private final LEDs m_leds;

  public CandleRed(LEDs subsystem) {
    m_leds = subsystem;
    addRequirements(m_leds);
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute(){
    m_leds.candleSetColor("red");
  }

  @Override
  public void end(boolean interrupted){
    m_leds.candleSetColor("stop");
    }

  @Override
  public boolean isFinished() {
    return false;
  }
}