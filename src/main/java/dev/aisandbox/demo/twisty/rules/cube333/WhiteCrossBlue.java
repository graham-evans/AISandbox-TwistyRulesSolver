package dev.aisandbox.demo.twisty.rules.cube333;

import dev.aisandbox.demo.twisty.rules.SolverAlgorithmStep;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 * Last edge of the bottom level - find the white/blue edge and move it to bottom front.
 * Results in
 * ....Y........O..O.....B..B.....R..R.....G..G..W.WWW.W.
 */
@Slf4j
public class WhiteCrossBlue implements SolverAlgorithmStep {

  @Override
  public Optional<String> getMoves(String state) {
    // find the white/blue edge
    EdgePosition p = EdgePosition.findEdgePosition(state, 'W', 'B');
    // place the white blue edge piece ignoring all other pieces
    return switch (p) {
      // top face
      case EDGE_TOP_RIGHT -> Optional.of("U F2");
      case EDGE_TOP_TOP -> Optional.of("U2 F2");
      case EDGE_TOP_LEFT -> Optional.of("U' F2");
      case EDGE_TOP_BOTTOM -> Optional.of("F2");
      // right face
      case EDGE_RIGHT_TOP -> Optional.of("R' F R");
      case EDGE_RIGHT_LEFT -> Optional.of("F");
      case EDGE_RIGHT_RIGHT -> Optional.of("R2 F R2");
      // front face
      case EDGE_FRONT_TOP -> Optional.of("U'"); // throw to right top
      case EDGE_FRONT_RIGHT -> Optional.of("F'"); // throw to front top
      case EDGE_FRONT_LEFT -> Optional.of("F"); // throw to front top
      // left face
      case EDGE_LEFT_TOP -> Optional.of("L F' L'");
      case EDGE_LEFT_RIGHT -> Optional.of("F'");
      case EDGE_LEFT_LEFT -> Optional.of("L2 F' L2");
      // back face
      case EDGE_BACK_TOP -> Optional.of("U"); // move to right top
      case EDGE_BACK_RIGHT -> Optional.of("L U L'"); // move to top top
      case EDGE_BACK_LEFT -> Optional.of("R' U R"); // move to top bottom

      // default
      default -> Optional.empty();
    };
  }
}