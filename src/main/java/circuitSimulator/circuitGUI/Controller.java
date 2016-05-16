package circuitSimulator.circuitGUI;

import circuitSimulator.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    public ComboBox<String> gateChoice;
    public ComboBox<String> leftInput;
    public ComboBox<String> rightInput;
    public ComboBox<String> output;
    public CheckBox applyNotGate;
    public Button addComponentButton;
    public Button runButton;
    public TextArea logText;
    private Circuit circuit;
    private List<LogicGate> logicGates;
    private List<LogicGate> sinks;
    private List<LogicGate> sources;

    public void runSimulation(){
        logText.clear();
        if(circuit.isValid()){
            circuit.simulate();
            logText.appendText("----Results---- \n\n");
            String message;
            for(int iterator = 0; iterator < sinks.size(); iterator++){
                message = "";
                message += "Sink" + iterator + " : " + sinks.get(iterator).getCurrentState() + " \n";
                logText.appendText(message);
            }

            for(int iterator = 0; iterator < logicGates.size(); iterator++){
                message = "";
                message += gateTypeToString(logicGates.get(iterator)) + iterator + " : " + logicGates.get(iterator).getCurrentState() + " \n";
                logText.appendText(message);
            }

            for(int iterator = 0; iterator < sources.size(); iterator++){
                message = "";
                message += "Sources" + iterator + " : " + sources.get(iterator).getCurrentState() + " \n";
                logText.appendText(message);
            }
        }
        else
            logText.appendText("Invalid Circuit");
    }

    public String gateTypeToString(LogicGate gate){
        if(gate instanceof AndGate)
            return "AndGate";
        else if(gate instanceof  OrGate)
            return "OrGate";
        else
            return "XorGate";

    }

    @FXML
    private void initialize(){
        circuit = new Circuit();
        logicGates = new ArrayList<>();
        sinks = new ArrayList<>();
        sources = new ArrayList<>();

        gateChoice.getItems().addAll("And", "Or", "Xor");
        gateChoice.promptTextProperty().set("-Select Gate-");
        leftInput.getItems().addAll("New Source: False", "New Source: True");
        rightInput.getItems().addAll("New Source: False", "New Source: True");
        output.getItems().addAll("New Sink");

        runButton.setDisable(true);
        disableSelections();
    }

    public void disableSelections(){
        gateChoice.getSelectionModel().clearSelection();
        leftInput.setDisable(true);
        rightInput.setDisable(true);
        output.setDisable(true);
        applyNotGate.setDisable(true);
        addComponentButton.setDisable(true);
    }

    public void onSelection() {
        leftInput.setDisable(false);
        rightInput.setDisable(false);
        output.setDisable(false);
        applyNotGate.setDisable(false);
        addComponentButton.setDisable(false);
        runButton.setDisable(false);
    }

    public void addComponentClick(){
        String selectedGate = gateChoice.getSelectionModel().getSelectedItem();
        String gateLeftInput = leftInput.getSelectionModel().getSelectedItem();
        String gateRightInput = rightInput.getSelectionModel().getSelectedItem();
        String gateOutput = output.getSelectionModel().getSelectedItem();
        boolean notApplied = applyNotGate.isSelected();

        buildComponent(selectedGate, gateLeftInput, gateRightInput, gateOutput, notApplied);

        disableSelections();
    }


    private void buildComponent(String selectedGate, String gateLeftInput, String gateRightInput, String gateOutput, boolean notApplied) {
        LogicGate middleGate = buildGate(selectedGate);
        LogicGate leftInputGate = buildGate(gateLeftInput);
        LogicGate rightInputGate = buildGate(gateRightInput);
        LogicGate outputGate = buildGate(gateOutput);

        middleGate.applyNot(notApplied);
        buildBus(leftInputGate, middleGate);
        buildBus(rightInputGate, middleGate);
        buildBus(middleGate, outputGate);
    }

    private void buildBus(LogicGate leftInput, LogicGate gate) {
        circuit.addBus(leftInput, gate);
    }

    private LogicGate buildGate(String selectedGate) {
        switch (selectedGate){
            case "And":
                LogicGate andGate = new AndGate();
                circuit.addGate(andGate);
                logicGates.add(andGate);
                return andGate;
            case "Or":
                LogicGate orGate = new OrGate();
                circuit.addGate(orGate);
                logicGates.add(orGate);
                return orGate;
            case "Xor" :
                LogicGate xorGate = new XorGate();
                circuit.addGate(xorGate);
                logicGates.add(xorGate);
                return xorGate;
            case "New Source: False":
                LogicGate sourceFalse = new Source();
                circuit.addGate(sourceFalse);
                sources.add(sourceFalse);
                return sourceFalse;
            case "New Source: True":
                Source sourceTrue = new Source();
                sourceTrue.setUserInput(true);
                circuit.addGate(sourceTrue);
                sources.add(sourceTrue);
                return sourceTrue;
            case"New Sink":
                LogicGate sink = new Sink();
                circuit.addGate(sink);
                sinks.add(sink);
                return sink;
        }

        return null;
    }
    
}
