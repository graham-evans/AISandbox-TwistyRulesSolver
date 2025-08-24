package dev.aisandbox.demo.twisty.rules.cube333;

import dev.aisandbox.demo.twisty.rules.SolverAlgorithmStep;
import lombok.extern.slf4j.Slf4j;
import org.smarthomethinking.homeintranet.ai.twisty.easy.SolverException;
import org.smarthomethinking.homeintranet.ai.twisty.easy.SolverHaltException;

@Slf4j
public class WhiteCorners3  implements SolverAlgorithmStep {
  @Override
  public boolean isValid(String state) {
    // If we've already solved the white side and turned the cube over - ignore
    if (state.matches("....Y..........RRR......GGG......OOO......BBBWWWWWWWWW")) return false;
    // if we've already got the third top corner - ignore
    if (state.matches("WW.WWWWWWOOO......GGG......RR........BB..........Y....")) return false;
    // otherwise return true
    return true;
  }

  @Override
  public String getMoves(String state) throws SolverException {
    log.info("Trying to move white/blue/orange corner to top/back/left {}", state);
    CornerPosition p = Finder.findCorner(state, 'W', 'B', 'O');
    log.info("Found piece in position {}", p.name());
    switch (p) {
      // first two corners - already solved
      case CORNER_FRONT_TOP_LEFT:
      case CORNER_TOP_LEFT_FRONT:
      case CORNER_LEFT_FRONT_TOP:
      case CORNER_TOP_FRONT_RIGHT:
      case CORNER_FRONT_RIGHT_TOP:
      case CORNER_RIGHT_TOP_FRONT:
        return "";

      case CORNER_BACK_LEFT_TOP:
      case CORNER_LEFT_TOP_BACK: // right place, wrong rotation - move to bottom
        return "L' D' L";
      case CORNER_TOP_BACK_LEFT:// solved state
        return "";

      case CORNER_RIGHT_BACK_TOP:
      case CORNER_BACK_TOP_RIGHT:
      case CORNER_TOP_RIGHT_BACK: // move to bottom
        return "R D R'";

      case CORNER_FRONT_BOTTOM_RIGHT:
      case CORNER_BOTTOM_RIGHT_FRONT:
      case CORNER_RIGHT_FRONT_BOTTOM:// move from front right corner to back
        return "D2";

      case CORNER_FRONT_LEFT_BOTTOM:
      case CORNER_LEFT_BOTTOM_FRONT:
      case CORNER_BOTTOM_FRONT_LEFT:
        return "D'";

      case CORNER_BACK_RIGHT_BOTTOM:
      case CORNER_RIGHT_BOTTOM_BACK:
      case CORNER_BOTTOM_BACK_RIGHT:
        return "D";
        // move up
      case CORNER_BACK_BOTTOM_LEFT:
        return "D' L' D L";
      case CORNER_BOTTOM_LEFT_BACK:
        return "B D' B'"; // recursive
      case CORNER_LEFT_BACK_BOTTOM:
        return "D B D' B'";
    }
    throw new SolverHaltException("Can't find white/green/red corner cube");
  }

}
