package dev.aisandbox.demo.twisty.rules.cube333;

import dev.aisandbox.demo.twisty.rules.SolverAlgorithmStep;
import dev.aisandbox.demo.twisty.rules.SolverException;
import dev.aisandbox.demo.twisty.rules.TwistySolver;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Cube333Solver implements TwistySolver {

  private List<SolverAlgorithmStep> algorithmList = List.of(new Orientation(), new WhiteCrossRed(),
      new WhiteCrossOrange());

  private List<String> moveQueue = new ArrayList<>();

  @Override
  public String getPuzzleName() {
    return "Cube 3x3x3";
  }

  @Override
  public String getSolutionStep(String state) throws SolverException {
    if (moveQueue.isEmpty()) {
      for (SolverAlgorithmStep step : algorithmList) {
        Optional<String> result = step.getMoves(state);
        if (result.isPresent()) {
          log.info("Algorithm result: {} {}", step.getClass().getSimpleName(), result.get());
          moveQueue.addAll(List.of(result.get().split(" ")));
          return moveQueue.removeFirst();
        }
      }
      throw new SolverException("No solution found");
    } else {
      return moveQueue.removeFirst();
    }
  }
}
