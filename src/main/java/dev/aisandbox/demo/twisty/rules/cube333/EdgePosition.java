package dev.aisandbox.demo.twisty.rules.cube333;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Enum to describe the position and orientation of edge pieces on a 3x3x3 cube.
 * <p>The face is described first (TOP / LEFT / RIGHT / FRONT / BACK / BOTTOM), then the position
 * of
 * the first colour (TOP / LEFT / RIGHT / BOTTOM) these are how they appear in the flattened
 * cube.</p>
 */
@Slf4j
@Getter
public enum EdgePosition {
  // Primary is on top
  EDGE_TOP_TOP(".x...................................y................"),
  EDGE_TOP_BOTTOM(".......x...........y.................................."),
  EDGE_TOP_LEFT("...x......y..........................................."),
  EDGE_TOP_RIGHT(".....x......................y........................."),
  // primary is on back face
  EDGE_BACK_TOP(".y...................................x................"),
  EDGE_BACK_LEFT("................................y......x.............."),
  EDGE_BACK_RIGHT("............y............................x............"),
  EDGE_BACK_BOTTOM("...........................................x........y."),
  // primary is on front face
  EDGE_FRONT_TOP(".......y...........x.................................."),
  EDGE_FRONT_LEFT("..............y......x................................"),
  EDGE_FRONT_RIGHT(".......................x......y......................."),
   EDGE_FRONT_BOTTOM(".........................x....................y......."),
  // primary is on left face
  EDGE_LEFT_TOP("...y......x..........................................."),
  EDGE_LEFT_LEFT("............x............................y............"),
  EDGE_LEFT_RIGHT("..............x......y................................"),
  EDGE_LEFT_BOTTOM("................x...............................y....."),
  //primary is on right face
  EDGE_RIGHT_TOP(".....y......................x........................."),
  EDGE_RIGHT_LEFT(".......................y......x......................."),
  EDGE_RIGHT_RIGHT("................................x......y.............."),
  EDGE_RIGHT_BOTTOM("..................................x...............y..."),
  //primary on bottom face
  EDGE_BOTTOM_TOP(".........................y....................x......."),
  EDGE_BOTTOM_LEFT("................y...............................x....."),
  EDGE_BOTTOM_RIGHT("..................................y...............x..."),
  EDGE_BOTTOM_BOTTOM("...........................................y........x.");

  private final String regex;

  EdgePosition(String regex) {
    this.regex = regex;
  }

  public static EdgePosition findEdgePosition(String state,char prime,char second) {
    String target = state.replace(prime, 'x').replace(second, 'y');
    for (EdgePosition p : EdgePosition.values()) {
      if (target.matches(p.getRegex())) return p;
    }
    log.warn("Can't find edge {}-{} from state {}", prime, second, state);
    return null;
  }
}
