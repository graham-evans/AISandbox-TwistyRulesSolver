package dev.aisandbox.demo.twisty.rules.cube333;

import static org.smarthomethinking.homeintranet.ai.twisty.easy.easycube333.YellowEdge1.EDGE_SWITCH;

import dev.aisandbox.demo.twisty.rules.SolverAlgorithmStep;
import lombok.extern.slf4j.Slf4j;
import org.smarthomethinking.homeintranet.ai.twisty.easy.SolverException;

@Slf4j
public class YellowEdge3 implements SolverAlgorithmStep {

  @Override
  public boolean isValid(String state) {
    if (state.matches(".Y.YYY.Y..R.RRRRRR.G.GGGGGG.O.OOOOOO.B.BBBBBBWWWWWWWWW"))
      return false; // front & left edge already solved
    return true;
  }

  @Override
  public String getMoves(String state) throws SolverException {
    log.info("Solving last yellow edges yellow/orange and yellow/blue {}", state);
    return "y2 " + EDGE_SWITCH + " y2";
  }
}
