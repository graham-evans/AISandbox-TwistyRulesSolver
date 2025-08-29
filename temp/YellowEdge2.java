package dev.aisandbox.demo.twisty.rules.cube333;

import static org.smarthomethinking.homeintranet.ai.twisty.easy.easycube333.YellowEdge1.EDGE_SWITCH;

import dev.aisandbox.demo.twisty.rules.SolverAlgorithmStep;
import lombok.extern.slf4j.Slf4j;
import org.smarthomethinking.homeintranet.ai.twisty.easy.SolverException;
import org.smarthomethinking.homeintranet.ai.twisty.easy.SolverHaltException;

@Slf4j
public class YellowEdge2 implements SolverAlgorithmStep {

  @Override
  public boolean isValid(String state) {
    if (state.matches(".Y.YYY.Y..R.RRRRRR.G.GGGGGG...OOOOOO...BBBBBBWWWWWWWWW"))
      return false; // front & left edge already solved
    return true;
  }

  @Override
  public String getMoves(String state) throws SolverException {
    log.info("Solving yellow/red edge for state {}", state);
    EdgePosition p = Finder.findEdge(state, 'Y', 'R');
    log.info("Found at position {}", p.name());
    switch (p) {
      case EDGE_TOP_RIGHT:
        return "y2 " + EDGE_SWITCH + " y " + EDGE_SWITCH + " y";
      case EDGE_TOP_BACK:
        return "y " + EDGE_SWITCH + " y'";
      default:
        throw new SolverHaltException("Can't find yellow/red edge cube");
    }
  }
}
