package circuitSimulator;

import org.testng.annotations.DataProvider;

public class AndGateTest extends LogicGateTest{


    @DataProvider(name="provideData")
    @Override
    public Object[][] provideData() {
        AndGate inputGate = new AndGate();
        AndGate logicGate = new AndGate();
        return new Object[][] {{inputGate, logicGate}};
    }

    @DataProvider(name="evaluateSignal")
    @Override
    public Object[][] evaluateSignal() {
        AndGate logicGate = new AndGate();
        return new Object[][] {{logicGate, true, true, true}, {logicGate, false, false, false}, {logicGate, true, false, false}, {logicGate, false, true, false}};
    }

}