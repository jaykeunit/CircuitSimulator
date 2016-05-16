package circuitSimulator;

public class Sink extends LogicGate {

    @Override
    public boolean isValid() {
        return getFirstInput() != null;
    }

    @Override
    public boolean evaluateSignal(boolean input1, boolean input2) {
        throw new UnsupportedOperationException("cannot evaluate Sink");
    }

    @Override
    public void simulate() {
        boolean input1 = getFirstInput().requestSignal();
        setNextState(input1);
    }

    @Override
    public void setSecondInput(Bus bus) {
        throw new UnsupportedOperationException("cannot set second input on Sink");
    }

    @Override
    public void setOutput(Bus bus) {
        throw new UnsupportedOperationException("cannot set an output bus on Sink");
    }

    @Override
    public Bus getSecondInput() {
        throw new UnsupportedOperationException("cannot set a second input bus on Sink");
    }

    @Override
    public boolean requestSignal() {
        return getFirstInput().requestSignal();
    }

}