package dev.aisandbox.demo.twisty.rules.cube333;

import dev.aisandbox.demo.twisty.rules.SolverAlgorithmStep;
import dev.aisandbox.demo.twisty.rules.SolverException;

public class Orientation implements SolverAlgorithmStep {

  @Override
  public boolean isValid(String state) {
    // If we've already solved the white side and turned the cube over - ignore
    if (state.matches("....Y..........RRR......GGG......OOO......BBBWWWWWWWWW")) return false;
    // if we're already in position - ignore
    if(state.matches("....W........O........G........R........B........Y....")) return false;
    // otherwise return true
    return true;
  }

  @Override
  public String getMoves(String state) throws SolverException {
    log.info("Trying to orientate cube from state {}",state);
    // white in front
    if (state.matches("......................W...............................")) return "x";
    // white at bottom
    if (state.matches(".................................................W....")) return "x2";
    // white at back
    if (state.matches("........................................W.............")) return "x'";
    // white at left
    if (state.matches(".............W........................................")) return "z";
    // white on right
    if (state.matches("...............................W......................")) return "z'";
    // green on left
    if (state.matches("....W........G...................................Y....")) return "y'";
    // green must be on right
    if (state.matches("....W..........................G.................Y....")) return "y";
    // green at back
    if (state.matches("....W...................................G........Y....")) return "y2";
      // there must have been an error
      throw new SolverHaltException("Can't oriantate cube");
  }
}
