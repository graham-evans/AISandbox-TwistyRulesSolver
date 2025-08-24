package dev.aisandbox.demo.twisty.rules.cube333;

import dev.aisandbox.demo.twisty.rules.SolverAlgorithmStep;
import dev.aisandbox.demo.twisty.rules.SolverException;
import dev.aisandbox.demo.twisty.rules.TwistySolver;
import java.util.List;

public class Cube333Solver implements TwistySolver {

  private List<SolverAlgorithmStep> algorithmList = List.of(new Orientation());

  @Override
  public String getPuzzleName() {
    return "Cube 3x3x3 (OBTM)";
  }

  @Override
  public String getSolutionStep(String state) throws SolverException {
    for (SolverAlgorithmStep step : algorithmList) {
      if (step.isValid(state)) {
        return step.getMoves(state);
      }
    }
    throw new SolverException("No solution found");
  }
}
