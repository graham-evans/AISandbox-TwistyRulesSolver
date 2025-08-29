package dev.aisandbox.demo.twisty.rules.cube333;

import dev.aisandbox.demo.twisty.rules.SolverAlgorithmStep;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WhiteCrossRed implements SolverAlgorithmStep {

  @Override
  public Optional<String> getMoves(String state) {
    // find the white/ref edge
    EdgePosition p = EdgePosition.findEdgePosition(state, 'W', 'R');
    // place the white red edge piece ignoring all other pieces
    return switch (p) {
      // top face
      case EDGE_TOP_RIGHT -> Optional.of("R2");
      case EDGE_TOP_TOP -> Optional.of("U R2");
      case EDGE_TOP_LEFT -> Optional.of("U2 R2");
      case EDGE_TOP_BOTTOM -> Optional.of("U' R2");
      // right face
      case EDGE_RIGHT_TOP -> Optional.of("U F R'");
      case EDGE_RIGHT_BOTTOM -> Optional.of("R2"); // move to right top
      case EDGE_RIGHT_LEFT -> Optional.of("R"); // move to right top
      case EDGE_RIGHT_RIGHT -> Optional.of("R'"); // move to right top
      // front face
      case EDGE_FRONT_TOP -> Optional.of("F"); // move to front right
      case EDGE_FRONT_RIGHT -> Optional.of("R'");
      case EDGE_FRONT_LEFT -> Optional.of("F2");
      case EDGE_FRONT_BOTTOM -> Optional.of("F'");
      // case bottom face
      case EDGE_BOTTOM_TOP -> Optional.of("D");
      case EDGE_BOTTOM_RIGHT -> Optional.empty(); // finished position
      case EDGE_BOTTOM_LEFT -> Optional.of("D2");
      case EDGE_BOTTOM_BOTTOM -> Optional.of("D'");
      // case left face
      case EDGE_LEFT_BOTTOM -> Optional.of("D");
      case EDGE_LEFT_TOP -> Optional.of("U'");
      case EDGE_LEFT_RIGHT -> Optional.of("F'");
      case EDGE_LEFT_LEFT -> Optional.of("L U");
      // case back
      case EDGE_BACK_TOP -> Optional.of("U");
      case EDGE_BACK_RIGHT -> Optional.of("L");
      case EDGE_BACK_LEFT -> Optional.of("R");
      case EDGE_BACK_BOTTOM -> Optional.of("B");
    };
  }
}
