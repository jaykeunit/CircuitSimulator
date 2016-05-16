package circuitSimulator;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Circuit {

    public List<LogicGate> gates = new ArrayList<>();
    public List<Bus> buses = new ArrayList<>();

   public void addGate(LogicGate aGate) {
       gates.add(aGate);
   }

   public void addBus(LogicGate input, LogicGate... outputs) {
       Bus bus = new Bus(input, outputs);

       input.setOutput(bus);
       Arrays.asList(outputs).forEach(gate -> handleInputConnection(gate, bus));

       buses.add(bus);
   }

   public boolean isValid() {
       return !gates.isEmpty() && !buses.isEmpty() && gates.stream().allMatch(LogicGate::isValid) && buses.stream().allMatch(Bus::isValid);
   }

    public boolean handleInputConnection(LogicGate output, Bus bus){
        try{
            if(output.getFirstInput() == null){
                output.setFirstInput(bus);
                return true;
            }
            else if(output.getSecondInput() == null) {
                output.setSecondInput(bus);
                return true;
            }
            else
                return false;
        }catch(Exception e){
            return false;
        }

    }

    public void simulate() {
        gates.forEach(LogicGate::simulate);
        buses.forEach(Bus::simulate);

        gates.forEach(LogicGate::nextState);
        buses.forEach(Bus::nextState);
    }

}