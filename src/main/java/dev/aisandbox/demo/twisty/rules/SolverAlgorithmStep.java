package dev.aisandbox.demo.twisty.rules;

public interface SolverAlgorithmStep {

  public boolean isValid(String state);

  public String getMoves(String state) throws SolverException;

}
