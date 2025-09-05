package dev.aisandbox.demo.twisty.rules.cube333;

import dev.aisandbox.demo.twisty.rules.SolverAlgorithmStep;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 * Second edge of the bottom level - find the white/orange edge and move it to bottom left.
 * Results in
 * ....Y........O..O.....B........R..R.....G.......WWW...
 */
@Slf4j
public class WhiteCrossOrange implements SolverAlgorithmStep {

  @Override
  public Optional<String> getMoves(String state) {
    // find the white/green edge
    EdgePosition p = EdgePosition.findEdgePosition(state, 'W', 'O');
    return switch (p) {
      // bottom edge - move upwards and re-run
      case EDGE_BOTTOM_BOTTOM -> Optional.of("B");
      case EDGE_BOTTOM_TOP -> Optional.of("F");
      // top edges
      case EDGE_TOP_TOP -> Optional.of("U' L2");
      case EDGE_TOP_LEFT -> Optional.of("L2");
      case EDGE_TOP_RIGHT -> Optional.of("U2 L2");
      case EDGE_TOP_BOTTOM -> Optional.of("U L2");
      // front
      case EDGE_FRONT_TOP -> Optional.of("F' L");
      case EDGE_FRONT_LEFT -> Optional.of("L");
      case EDGE_FRONT_RIGHT -> Optional.of("F2 L");
      case EDGE_FRONT_BOTTOM -> Optional.of("F L");
      // left
      case EDGE_LEFT_TOP -> Optional.of("U' F' L");
      case EDGE_LEFT_LEFT -> Optional.of("L U' F' L");
      case EDGE_LEFT_RIGHT -> Optional.of("L' U' F' L");
      case EDGE_LEFT_BOTTOM -> Optional.of("L2 U' F' L");
      // right
      case EDGE_RIGHT_TOP -> Optional.of("U"); // throw to top
      case EDGE_RIGHT_LEFT -> Optional.of("D F D'");
      case EDGE_RIGHT_RIGHT -> Optional.of("R2");
      // back
      case EDGE_BACK_TOP -> Optional.of("U2"); // throw to front
      case EDGE_BACK_LEFT -> Optional.of("R' U R"); // throw to top
      case EDGE_BACK_RIGHT -> Optional.of("L'");
      case EDGE_BACK_BOTTOM -> Optional.of("B'"); // throw to back right

      default -> Optional.empty(); // finished or invalid move
    };
  }
}
