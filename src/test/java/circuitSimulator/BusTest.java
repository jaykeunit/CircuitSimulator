package circuitSimulator;

import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class BusTest {

    @Test
    public void testIsValidWithInputAndNoOutput() {
        AndGate inputGate = new AndGate();
        Bus bus = new Bus(inputGate);

        assertFalse(bus.isValid());
    }

    @Test
    public void testIsValidWithInputAndOneOutput() {
        AndGate inputGate = new AndGate();
        AndGate outputGate = new AndGate();
        Bus bus = new Bus(inputGate, outputGate);

        assertTrue(bus.isValid());
    }

    @Test
    public void testIsValidWithInputAndTwoOutputs() {
        AndGate inputGate = new AndGate();
        AndGate outputGate1 = new AndGate();
        AndGate outputGate2 = new AndGate();
        Bus bus = new Bus(inputGate, outputGate1, outputGate2);

        assertTrue(bus.isValid());
    }

    @Test
    public void testRequestSignal() {
        AndGate inputGate = Mockito.mock(AndGate.class);
        when(inputGate.requestSignal()).thenReturn(false);

        AndGate outputGate = new AndGate();

        Bus bus = new Bus(inputGate, outputGate);

        assertFalse(bus.requestSignal());
    }

}