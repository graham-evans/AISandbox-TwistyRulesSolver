package dev.aisandbox.demo.twisty.rules.cube333;

import dev.aisandbox.demo.twisty.rules.SolverAlgorithmStep;
import dev.aisandbox.demo.twisty.rules.SolverException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Orientation implements SolverAlgorithmStep {

  /**
   * This takes the cube to a state of ....Y........O........B........R........G........W....
   * @param state
   * @return
   * @throws SolverException
   */
  @Override
  public Optional<String> getMoves(String state) throws SolverException {
    if (state.matches("....W.................................................")) {
      // white on top
      return Optional.of("x2");
    }
    if (state.matches("......................W...............................")) {
      // white on front
      return Optional.of("x'");
    }
    if (state.matches("........................................W.............")) {
      // white at back
      return Optional.of("x");
    }
    if (state.matches(".............W........................................")) {
      // white on left
      return Optional.of("z'");
    }
    if (state.matches("...............................W......................")) {
      // white on right
      return Optional.of("z");
    }
    if (state.matches("....Y.................O..........................W....")) {
      // orange on front, white at bottom
      return Optional.of("y");
    }
    if (state.matches("....Y.................R..........................W....")) {
      // red on front, white at bottom
      return Optional.of("y'");
    }
    if (state.matches("....Y.................G..........................W....")) {
      // green on front, white on bottom
      return Optional.of("y2");
    }
    return Optional.empty();
  }
}
