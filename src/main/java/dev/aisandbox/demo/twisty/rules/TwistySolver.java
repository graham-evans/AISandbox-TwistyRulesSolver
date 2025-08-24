package dev.aisandbox.demo.twisty.rules;

public interface TwistySolver {

  public String getPuzzleName();
  public String getSolutionStep(String state) throws SolverException;

}
