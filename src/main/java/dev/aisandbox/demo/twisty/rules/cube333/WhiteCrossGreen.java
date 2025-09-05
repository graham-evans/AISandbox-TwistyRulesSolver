package dev.aisandbox.demo.twisty.rules.cube333;

import dev.aisandbox.demo.twisty.rules.SolverAlgorithmStep;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 * Third edge of the bottom level - find the white/green edge and move it to bottom back.
 * Results in
 * ....Y........O..O.....B........R..R.....G..G....WWW.W.
 */
@Slf4j
public class WhiteCrossGreen implements SolverAlgorithmStep {

  @Override
  public Optional<String> getMoves(String state) {
    // find the green/white edge
    EdgePosition p = EdgePosition.findEdgePosition(state, 'W', 'G');
    return switch (p) {
      // top level
      case EDGE_TOP_RIGHT -> Optional.of("U' B2");
      case EDGE_TOP_TOP -> Optional.of("B2");
      case EDGE_TOP_LEFT -> Optional.of("U B2");
      case EDGE_TOP_BOTTOM -> Optional.of("U2 B2");
      // front - throw to top
      case EDGE_FRONT_TOP -> Optional.of("F R U R'");
      case EDGE_FRONT_RIGHT -> Optional.of("R U R'");
      case EDGE_FRONT_LEFT -> Optional.of("L' U L");
      case EDGE_FRONT_BOTTOM -> Optional.of("F' R U R'");
      // left
      case EDGE_LEFT_TOP -> Optional.of("L' B L");
      case EDGE_LEFT_RIGHT -> Optional.of("L2 B L2");
      case EDGE_LEFT_LEFT -> Optional.of("B");
      // right
      case EDGE_RIGHT_TOP -> Optional.of("R B'");
      case EDGE_RIGHT_LEFT -> Optional.of("R2 B'");
      case EDGE_RIGHT_RIGHT -> Optional.of("B'");
      // back
      case EDGE_BACK_TOP -> Optional.of("U"); // move right
      case EDGE_BACK_RIGHT -> Optional.of("B'"); // move to back top
      case EDGE_BACK_LEFT -> Optional.of("B"); // move to back top
      case EDGE_BACK_BOTTOM -> Optional.of("B2"); // move to back top
      // default - no move
      default -> Optional.empty();
    };
  }

}
