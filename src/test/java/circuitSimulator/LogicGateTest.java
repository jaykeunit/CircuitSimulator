package circuitSimulator;

import org.mockito.Mockito;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public abstract class LogicGateTest {

    @DataProvider(name="provideData")
    public abstract Object[][] provideData();

    @DataProvider(name="evaluateSignal")
    public abstract Object[][] evaluateSignal();

    @Test(dataProvider = "provideData")
    public void testIsValidIsTrue(LogicGate inputGate, LogicGate logicGate) {
        Bus firstInput = new Bus(inputGate);
        Bus secondInput = new Bus(inputGate);
        Bus outputBus = new Bus(inputGate);

        logicGate.setFirstInput(firstInput);
        logicGate.setSecondInput(secondInput);
        logicGate.setOutput(outputBus);

        assertTrue(logicGate.isValid());
    }

    @Test(dataProvider = "provideData")
    public void testIsValidForMissingLeftBus(LogicGate inputGate, LogicGate logicGate) {
        Bus secondInput = new Bus(inputGate);
        Bus outputBus = new Bus(inputGate);

        logicGate.setFirstInput(secondInput);
        logicGate.setOutput(outputBus);

        assertFalse(logicGate.isValid());
    }

    @Test(dataProvider = "provideData")
    public void testIsValidForMissingRightBus(LogicGate inputGate, LogicGate logicGate) {
        Bus firstInput = new Bus(inputGate);
        Bus outputBus = new Bus(inputGate);

        logicGate.setSecondInput(firstInput);
        logicGate.setOutput(outputBus);

        assertFalse(logicGate.isValid());
    }

    @Test(dataProvider = "provideData")
    public void testIsValidForMissingOutputBus(LogicGate inputGate, LogicGate logicGate) {
        Bus firstInput = new Bus(inputGate);
        Bus secondInput = new Bus(inputGate);

        logicGate.setFirstInput(firstInput);
        logicGate.setSecondInput(secondInput);

        assertFalse(logicGate.isValid());
    }

    @Test(dataProvider = "evaluateSignal")
    public void testPlaceHolderEvaluateSignalWithTrueTrue(LogicGate logicGate, boolean input1, boolean input2, boolean expectedOutput) {
        boolean evaluatedResult = logicGate.evaluateSignal(input1, input2);
        assertEquals(expectedOutput, evaluatedResult);
    }

    @Test(dataProvider = "provideData")
    public void testRequestSignal(LogicGate inputGate, LogicGate logicGate) {
        Bus bus = Mockito.mock(Bus.class);
        when(bus.requestSignal()).thenReturn(false);

        logicGate.setFirstInput(bus);
        logicGate.setSecondInput(bus);

        assertFalse(logicGate.requestSignal());
    }

    @Test(dataProvider = "evaluateSignal")
    public void testEvaluateWithNot(LogicGate logicGate, boolean input1, boolean input2, boolean expectedOutput) {
        logicGate.applyNot(true);
        boolean evaluatedResult = logicGate.evaluateSignal(input1, input2);
        assertEquals(!expectedOutput, evaluatedResult);
    }

    @Test(dataProvider = "provideData")
    public void testHasNot(LogicGate inputGate, LogicGate logicGate){
        logicGate.applyNot(true);
        assertTrue(logicGate.hasNot());
    }

}