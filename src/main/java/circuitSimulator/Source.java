package circuitSimulator;


public class Source extends LogicGate {
    private boolean input = false;
    private Bus output;

    public void setUserInput(boolean userInput) {
        input = userInput;
    }

    public boolean getUserInput(){
        return input;
    }

    public void setOutput(Bus bus) {
        output = bus;
    }

    @Override
    public Bus getFirstInput() {
        throw new UnsupportedOperationException("cannot set an input on a source");
    }

    @Override
    public Bus getSecondInput() {
        throw new UnsupportedOperationException("cannot set an input on a source");
    }

    @Override
    public boolean requestSignal() {
        return input;
    }

    @Override
    public boolean isValid() {
        return output != null;
    }

    @Override
    public boolean evaluateSignal(boolean input1, boolean input2) {
        throw new UnsupportedOperationException("cannot evaluate Source");
    }

    @Override
    public void setFirstInput(Bus bus) {
        throw new UnsupportedOperationException("cannot set an input bus on Source");
    }

    @Override
    public void setSecondInput(Bus bus) {
        throw new UnsupportedOperationException("cannot set an input bus on Source");
    }

    @Override
    public void simulate() {
        setNextState(input);
    }

}
