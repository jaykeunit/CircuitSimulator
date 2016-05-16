package circuitSimulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bus {
    private LogicGate inputGate;
    private List<LogicGate> outputGates = new ArrayList<>();
    private boolean currentState;
    private boolean nextState;


    public Bus (LogicGate input, LogicGate... outputs){
        inputGate = input;
        outputGates.addAll((Arrays.asList(outputs)));
    }

    public boolean isValid() {
        return !outputGates.isEmpty();
    }

    public boolean requestSignal() {
        return inputGate.requestSignal();
    }

    public void simulate() {
        nextState = inputGate.requestSignal();
    }

    public void nextState() {
        currentState = nextState;
    }
}