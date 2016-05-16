package circuitSimulator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class CircuitTest {

    Circuit circuit;

    @BeforeMethod
    public void setUp() {
        circuit = new Circuit();
    }

    @Test
    public void testHandleInputConnectionWithFirstInput(){
        AndGate inputGate = new AndGate();
        AndGate outputGate = new AndGate();
        Bus bus =  new Bus(inputGate, outputGate);

        assertTrue(circuit.handleInputConnection(outputGate, bus));
    }

    @Test
    public void testHandleInputConnectionWithSecondInput(){
        AndGate inputGate = new AndGate();
        AndGate outputGate = new AndGate();
        Bus bus =  new Bus(inputGate, outputGate);
        circuit.handleInputConnection(outputGate, bus);

        assertTrue(circuit.handleInputConnection(outputGate, bus));
    }

    @Test
    public void testHandleInputConnectionWithNoEmptyInput(){
        AndGate inputGate = new AndGate();
        AndGate outputGate = new AndGate();
        Bus bus =  new Bus(inputGate, outputGate);
        circuit.handleInputConnection(outputGate, bus);
        circuit.handleInputConnection(outputGate, bus);

        assertFalse(circuit.handleInputConnection(outputGate, bus));
    }

    @Test
    public void testHandleInputConnectionDoesNotAttemptToConnectSecondInputOnNonLogicGate(){
        AndGate inputGate = new AndGate();
        Sink outputSink = new Sink();
        Bus bus =  new Bus(inputGate, outputSink);
        circuit.handleInputConnection(outputSink, bus);

        assertFalse(circuit.handleInputConnection(outputSink, bus));
    }

    @Test
    public void testAddGateWithNoConnectionSet() {
        AndGate logicGate = new AndGate();
        circuit.addGate(logicGate);
        assertFalse(circuit.isValid());
    }

    @Test
    public void testWithEmptyGates() {
        assertFalse(circuit.isValid());
    }

    @Test
    public void testAddBus() {
        AndGate inputGate = new AndGate();
        AndGate outputGate = new AndGate();
        circuit.addGate(inputGate);
        circuit.addGate(outputGate);

        circuit.addBus(inputGate, outputGate);
        assertFalse(circuit.isValid());
    }

    @Test
    public void testGatesArePartOfCircuit() {
        AndGate inputGate = new AndGate();
        AndGate outputGate = new AndGate();
        circuit.addGate(inputGate);
        circuit.addGate(outputGate);
        List<LogicGate> expected = new ArrayList<>(Arrays.asList(inputGate, outputGate));

        assertTrue(circuit.gates.containsAll(expected));
    }

   @Test
   public void testCreateValidCircuit() {
       Sink output = new Sink();
       Source source1 = new Source();
       Source source2 = new Source();
       Source source3 = new Source();
       AndGate topLevelGate = new AndGate();
       AndGate bottomLevelGate = new AndGate();

       circuit.addGate(output);
       circuit.addGate(source1);
       circuit.addGate(source2);
       circuit.addGate(source3);
       circuit.addGate(topLevelGate);
       circuit.addGate(bottomLevelGate);

       circuit.addBus(topLevelGate, output);
       circuit.addBus(bottomLevelGate,topLevelGate);
       circuit.addBus(source1,bottomLevelGate);
       circuit.addBus(source2,bottomLevelGate);
       circuit.addBus(source3,topLevelGate);

       assertTrue(circuit.isValid());
   }

    @Test
    public void testCreateCircuitWithInvalidBus() {
        Source input = new Source();
        circuit.addGate(input);
        circuit.addBus(input);

        assertFalse(circuit.isValid());
    }

    @Test
    public void testRunCircuitWithAllDefaultsForAndGate() {
        Sink output = new Sink();
        Source source1 = new Source();
        Source source2 = new Source();
        Source source3 = new Source();
        AndGate topLevelGate = new AndGate();
        AndGate bottomLevelGate = new AndGate();

        circuit.addGate(output);
        circuit.addGate(source1);
        circuit.addGate(source2);
        circuit.addGate(source3);
        circuit.addGate(topLevelGate);
        circuit.addGate(bottomLevelGate);

        circuit.addBus(topLevelGate, output);
        circuit.addBus(bottomLevelGate,topLevelGate);
        circuit.addBus(source1,bottomLevelGate);
        circuit.addBus(source2,bottomLevelGate);
        circuit.addBus(source3,topLevelGate);

        circuit.simulate();

        assertFalse(output.getCurrentState());
    }

    @Test
    public void testRunCircuitWithUserInputsForAndGate() {
        Sink output = new Sink();
        Source source1 = new Source();
        Source source2 = new Source();
        Source source3 = new Source();
        AndGate topLevelGate = new AndGate();
        AndGate bottomLevelGate = new AndGate();

        source1.setUserInput(true);
        source2.setUserInput(true);
        source3.setUserInput(true);

        circuit.addGate(output);
        circuit.addGate(source1);
        circuit.addGate(source2);
        circuit.addGate(source3);
        circuit.addGate(topLevelGate);
        circuit.addGate(bottomLevelGate);

        circuit.addBus(topLevelGate, output);
        circuit.addBus(bottomLevelGate,topLevelGate);
        circuit.addBus(source1,bottomLevelGate);
        circuit.addBus(source2,bottomLevelGate);
        circuit.addBus(source3,topLevelGate);

        circuit.simulate();

        assertTrue(output.getCurrentState());
    }

    @Test
     public void testRunCircuitWithAllDefaultsWithOrGate() {
        Sink output = new Sink();
        Source source1 = new Source();
        Source source2 = new Source();
        Source source3 = new Source();
        OrGate topLevelGate = new OrGate();
        OrGate bottomLevelGate = new OrGate();

        circuit.addGate(output);
        circuit.addGate(source1);
        circuit.addGate(source2);
        circuit.addGate(source3);
        circuit.addGate(topLevelGate);
        circuit.addGate(bottomLevelGate);

        circuit.addBus(topLevelGate, output);
        circuit.addBus(bottomLevelGate,topLevelGate);
        circuit.addBus(source1,bottomLevelGate);
        circuit.addBus(source2,bottomLevelGate);
        circuit.addBus(source3,topLevelGate);

        circuit.simulate();

        assertFalse(output.getCurrentState());
    }

    @Test
    public void testRunCircuitWithAllDefaultsWithXOrGate() {
        Sink output = new Sink();
        Source source1 = new Source();
        Source source2 = new Source();
        Source source3 = new Source();
        XorGate topLevelGate = new XorGate();
        XorGate bottomLevelGate = new XorGate();

        circuit.addGate(output);
        circuit.addGate(source1);
        circuit.addGate(source2);
        circuit.addGate(source3);
        circuit.addGate(topLevelGate);
        circuit.addGate(bottomLevelGate);

        circuit.addBus(topLevelGate, output);
        circuit.addBus(bottomLevelGate,topLevelGate);
        circuit.addBus(source1,bottomLevelGate);
        circuit.addBus(source2,bottomLevelGate);
        circuit.addBus(source3,topLevelGate);

        circuit.simulate();

        assertFalse(output.getCurrentState());
    }

    @Test
    public void testRunCircuitWithAllDefaultsWithMixedGatesAndNotApplied() {
        Sink output = new Sink();
        Source source1 = new Source();
        Source source2 = new Source();
        Source source3 = new Source();
        AndGate topLevelGate = new AndGate();
        OrGate bottomLevelGate = new OrGate();

        topLevelGate.applyNot(true);

        circuit.addGate(output);
        circuit.addGate(source1);
        circuit.addGate(source2);
        circuit.addGate(source3);
        circuit.addGate(topLevelGate);
        circuit.addGate(bottomLevelGate);

        circuit.addBus(topLevelGate, output);
        circuit.addBus(bottomLevelGate,topLevelGate);
        circuit.addBus(source1,bottomLevelGate);
        circuit.addBus(source2,bottomLevelGate);
        circuit.addBus(source3,topLevelGate);

        circuit.simulate();

        assertTrue(output.getCurrentState());
    }

    @Test
    public void testRunCircuitTest() {
        Sink output = new Sink();
        Source source1 = new Source();
        Source source2 = new Source();
        OrGate topLevelGate = new OrGate();

        circuit.addGate(output);
        circuit.addGate(source1);
        circuit.addGate(source2);
        circuit.addGate(topLevelGate);

        circuit.addBus(topLevelGate, output);
        circuit.addBus(source1,topLevelGate);
        circuit.addBus(source2,topLevelGate);

        circuit.simulate();

        assertFalse(output.getCurrentState());
    }

}