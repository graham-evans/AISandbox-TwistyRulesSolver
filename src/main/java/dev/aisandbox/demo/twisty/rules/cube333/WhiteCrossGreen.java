package dev.aisandbox.demo.twisty.rules.cube333;

import dev.aisandbox.demo.twisty.rules.SolverAlgorithmStep;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 * move the white/orange tile to the correct position without moving the white + red/orange tiles.
 */
@Slf4j
public class WhiteCrossGreen implements SolverAlgorithmStep {

  @Override
  public Optional<String> getMoves(String state) {
    // find the green/white edge
    EdgePosition p = EdgePosition.findEdgePosition(state, 'W', 'G');
    return switch (p) {

      default -> Optional.empty();
    };
  }

}
