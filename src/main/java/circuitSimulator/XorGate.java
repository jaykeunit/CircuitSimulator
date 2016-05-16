package circuitSimulator;

public class XorGate extends LogicGate{

    @Override
    public boolean evaluateSignal(boolean input1, boolean input2) {
        return hasNot() ? !((input1 || input2) && !(input1 && input2)) : (input1 || input2) && !(input1 && input2) ;
    }

}