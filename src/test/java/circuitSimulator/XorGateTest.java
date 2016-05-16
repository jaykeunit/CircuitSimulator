package circuitSimulator;

import org.testng.annotations.DataProvider;

public class XorGateTest extends LogicGateTest{

    @DataProvider(name="provideData")
    @Override
    public Object[][] provideData() {
        XorGate inputGate = new XorGate();
        XorGate logicGate = new XorGate();
        return new Object[][] {{inputGate, logicGate}};
    }

    @DataProvider(name="evaluateSignal")
    @Override
    public Object[][] evaluateSignal() {
        XorGate logicGate = new XorGate();
        return new Object[][] {{logicGate, true, true, false}, {logicGate, false, false, false}, {logicGate, true, false, true}, {logicGate, false, true, true}};
    }
    
}