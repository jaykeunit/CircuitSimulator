package circuitSimulator;

import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class SinkTest {

    Sink sink;

    @BeforeMethod
    public void setUp() {
        sink = new Sink();
    }

    @Test
    public void testIsValidWithNoInputSet() {
        Sink sink = new Sink();

        assertFalse(sink.isValid());
    }

    @Test
    public void testIsValidWithInputSet() {
        Sink output = new Sink();

        AndGate inputGate = new AndGate();
        AndGate outputGate = new AndGate();
        Bus bus = new Bus(inputGate, outputGate);

        output.setFirstInput(bus);

        assertTrue(output.isValid());
    }

    @Test
    public void TestSetOutputShouldFail() {
        boolean expected = false;
        try{
            AndGate inputGate = new AndGate();
            AndGate outputGates = new AndGate();
            Bus bus = new Bus(inputGate, outputGates);
            sink.setOutput(bus);
        }
        catch (Exception e){
            expected = true;
        }
        assertTrue(expected);
    }

    @Test
    public void TestSetSecondInputShouldFail() {
        boolean expected = false;
        try{
            AndGate inputGate = new AndGate();
            AndGate outputGates = new AndGate();
            Bus bus = new Bus(inputGate, outputGates);
            sink.setSecondInput(bus);
        }
        catch (Exception e){
            expected = true;
        }
        assertTrue(expected);
    }

    @Test
    public void testRequestSignal() {
        Bus bus = Mockito.mock(Bus.class);
        when(bus.requestSignal()).thenReturn(false);

        sink.setFirstInput(bus);

        assertFalse(sink.requestSignal());
    }

    @Test
    public void TestEvaluateSignalShouldFail() {
        boolean expected = false;
        try{
            sink.evaluateSignal(true, true);
        }
        catch (Exception e){
            expected = true;
        }
        assertTrue(expected);
    }

}