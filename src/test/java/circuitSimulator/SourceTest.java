package circuitSimulator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class SourceTest {

    Source source;

    @BeforeMethod
    public void setUp() {
        source = new Source();
    }

    @Test
    public void testGetInput() {

        assertFalse(source.getUserInput());
    }

    @Test
    public void testUserSetInput() {
        source.setUserInput(true);

        assertTrue(source.getUserInput());
    }

    @Test
    public void testIsValidWithNoOutputSet() {

        assertFalse(source.isValid());
    }

    @Test
    public void testIsValidWithOutputSet() {

        AndGate inputGate = new AndGate();
        AndGate outputGate = new AndGate();
        Bus bus = new Bus(inputGate, outputGate);

        source.setOutput(bus);

        assertTrue(source.isValid());
    }

    @Test
    public void TestSetFirstInputShouldFail() {
        boolean expected = false;
        try{
            AndGate inputGate = new AndGate();
            AndGate outputGate = new AndGate();
            Bus bus = new Bus(inputGate, outputGate);
            source.setFirstInput(bus);
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
            AndGate outputGate = new AndGate();
            Bus bus = new Bus(inputGate, outputGate);
            source.setSecondInput(bus);
        }
        catch (Exception e){
            expected = true;
        }
        assertTrue(expected);
    }

    @Test
    public void TestGetFirstInputShouldFail() {
        boolean expected = false;
        try{
            source.getFirstInput();
        }
        catch (Exception e){
            expected = true;
        }
        assertTrue(expected);
    }

    @Test
    public void TestGetSecondInputShouldFail() {
        boolean expected = false;
        try{
            source.getSecondInput();
        }
        catch (Exception e){
            expected = true;
        }
        assertTrue(expected);
    }

    @Test
    public void testRequestSignal() {
        source.setUserInput(true);

        assertTrue(source.requestSignal());
    }

    @Test
    public void TestEvaluateSignalShouldFail() {
        boolean expected = false;
        try{
            source.evaluateSignal(true, true);
        }
        catch (Exception e){
            expected = true;
        }
        assertTrue(expected);
    }

}