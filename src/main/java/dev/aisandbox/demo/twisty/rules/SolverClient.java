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
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SolverClient {

  private final String host;
  private final int port;
  private final Map<String, TwistySolver> solverMap = new HashMap<>();

  public SolverClient(String host, int port) {
    this.host = host;
    this.port = port;
    addSolver(new Cube333Solver());
  }

  private void addSolver(TwistySolver solver) {
    solverMap.put(solver.getPuzzleName(), solver);
  }

  public void connectAndSolve() {
    log.info("Connecting to server on {}:{}", host, port);
    try {
      Socket clientSocket = new Socket(host, port);
      // create input and output streams
      OutputStream outputStream = clientSocket.getOutputStream();
      InputStream inputStream = clientSocket.getInputStream();
      while (true) {
        // read the simulation state
        TwistyState state = TwistyState.parseDelimitedFrom(inputStream);
        if (state != null) {
          // find a solver
          TwistySolver solver = solverMap.get(state.getPuzzleName());
          // write current card to screen
          log.info("Solving state {}:{}", state.getPuzzleName(), state.getState());
          // choose a random action
          TwistyAction action = TwistyAction.newBuilder().setMove(solver.getSolutionStep(state.getState())).build();
          log.info("Trying move(s) {}",action.getMove());
          // send this to the server
          action.writeDelimitedTo(outputStream);
          // read the reward
          TwistyResult result = TwistyResult.parseDelimitedFrom(inputStream);
          log.info("Result is {}", result.getSignal().name());
        } else {
          log.error("Server finished - closing connection");
          throw new RuntimeException("Server finished - closing connection");
        }
      }
    } catch (IOException e) {
      log.error("Error talking to server", e);
    } catch (SolverException e) {
      log.error("Solver error", e);
    }
  }


}
