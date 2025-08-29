package dev.aisandbox.demo.twisty.rules;


import dev.aisandbox.demo.twisty.rules.cube333.Cube333Solver;
import dev.aisandbox.server.simulation.twisty.proto.TwistyAction;
import dev.aisandbox.server.simulation.twisty.proto.TwistyResult;
import dev.aisandbox.server.simulation.twisty.proto.TwistyState;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Try and solve a twisty puzzle using random moves
 */
public class Launch {

  private Map<String,TwistySolver> solverMap = new HashMap<>();

  private void addSolver(TwistySolver solver) {
    solverMap.put(solver.getPuzzleName(), solver);
  }

  public Launch() {
    addSolver(new Cube333Solver());
  }

  public static void main(String[] args) {
    // Work out the port to connect to - defaults to localhost:9000
    String host = "localhost";
    int port = 9000;
    if (args.length == 1) {
      port = Integer.parseInt(args[0]);
    }
    if (args.length == 2) {
      host = args[0];
      port = Integer.parseInt(args[1]);
    }
    SolverClient client = new SolverClient(host, port);
    client.connectAndSolve();
  }
}
