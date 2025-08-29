package dev.aisandbox.demo.twisty.rules;

import java.util.Optional;

public interface SolverAlgorithmStep {

  public Optional<String> getMoves(String state) throws SolverException;

}
