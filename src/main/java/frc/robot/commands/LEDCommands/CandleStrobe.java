package frc.robot.commands.LEDCommands;

import frc.robot.subsystems.LEDs;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class CandleStrobe extends Command {
  // The subsystem the command runs on
  private final LEDs m_leds;

  public CandleStrobe(LEDs subsystem) {
    m_leds = subsystem;
    addRequirements(m_leds);
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute(){
    m_leds.candleSetAnimation("strobe");
  }

  @Override
  public void end(boolean interrupted){
    m_leds.candleOff();
    }

  @Override
  public boolean isFinished() {
    return false;
  }
}