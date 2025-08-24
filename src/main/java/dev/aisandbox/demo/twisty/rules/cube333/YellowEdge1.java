package dev.aisandbox.demo.twisty.rules.cube333;

import dev.aisandbox.demo.twisty.rules.SolverAlgorithmStep;
import lombok.extern.slf4j.Slf4j;
import org.smarthomethinking.homeintranet.ai.twisty.easy.SolverException;
import org.smarthomethinking.homeintranet.ai.twisty.easy.SolverHaltException;

@Slf4j
public class YellowEdge1 implements SolverAlgorithmStep {

  public static final String EDGE_SWITCH = "R U R' U R U2 R' U";

  @Override
  public boolean isValid(String state) {
    if (state.matches(".Y.YYY.Y....RRRRRR.G.GGGGGG...OOOOOO...BBBBBBWWWWWWWWW"))
      return false; // front edge already solved
    return true;
  }

  @Override
  public String getMoves(String state) throws SolverException {
    log.info("Solving yellow/green edge for state {}", state);
    EdgePosition p = Finder.findEdge(state, 'Y', 'G');
    log.info("Found at position {}", p.name());
    switch (p) {
      case EDGE_TOP_LEFT:
        return "U'";
      case EDGE_TOP_RIGHT:
        return "U";
      case EDGE_TOP_BACK:
        return "U2";
      default:
        throw new SolverHaltException("Can't find yellow/green edge cube");
    }
  }
}
