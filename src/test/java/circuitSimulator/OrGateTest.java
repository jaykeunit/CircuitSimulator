package circuitSimulator;

import org.testng.annotations.DataProvider;

public class OrGateTest extends LogicGateTest{

    @DataProvider(name="provideData")
    @Override
    public Object[][] provideData() {
        OrGate inputGate = new OrGate();
        OrGate logicGate = new OrGate();
        return new Object[][] {{inputGate, logicGate}};
    }

    @DataProvider(name="evaluateSignal")
    @Override
    public Object[][] evaluateSignal() {
        OrGate logicGate = new OrGate();
        return new Object[][] {{logicGate, true, true, true}, {logicGate, false, false, false}, {logicGate, true, false, true}, {logicGate, false, true, true}};
    }

}