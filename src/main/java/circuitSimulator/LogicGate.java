package circuitSimulator;

public abstract class LogicGate {
    private Bus firstInput;
    private Bus secondInput;
    private Bus output;
    private boolean not = false;
    private boolean currentState;
    private boolean nextState;

    public void setFirstInput(Bus bus) {
        firstInput = bus;
    }

    public void setSecondInput(Bus bus) {
        secondInput = bus;
    }

    public void setOutput(Bus bus) {
        output = bus;
    }

    public Bus getFirstInput() {
        return firstInput;
    }

    public Bus getSecondInput() {
        return secondInput;
    }

    public boolean requestSignal() {
        boolean input1 = firstInput.requestSignal();
        boolean input2 = secondInput.requestSignal();
        return evaluateSignal(input1, input2);
    }

    public boolean isValid() {
        return firstInput != null && secondInput != null && output != null;
    }

    public abstract boolean evaluateSignal(boolean input1, boolean input2);

    public void applyNot(boolean useNot) {
        not = useNot;
    }

    public boolean hasNot() {
        return not;
    }

    public void simulate() {
        boolean input1 = firstInput.requestSignal();
        boolean input2 = secondInput.requestSignal();

        nextState = evaluateSignal(input1, input2);
    }

    public void setNextState(boolean input){
        nextState = input;
    }

    public void nextState() {
        currentState = nextState;
    }

    public boolean getCurrentState() {
        return currentState;
    }

}