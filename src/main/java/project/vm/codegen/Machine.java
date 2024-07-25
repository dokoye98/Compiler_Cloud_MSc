package project.vm.codegen;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Machine {
    private final Map<RegisterName, Object> registers = new EnumMap<>(RegisterName.class);


    public void setVariable(String variableName, Object value) {
        variables.put(variableName, value);
    }

    private   Map<String,Object> variables = new HashMap<>();
    private int programCounter = 0;

    public Object getRegister(RegisterName register) {
        return registers.getOrDefault(register, 0);
    }

    public void setRegister(RegisterName register, Object value) {
        registers.put(register, value);
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public Map<RegisterName, Object> getRegisters() {
        return registers;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }
    public Object getVariableSingle(String name){
        return variables.get(name);
    }

    public void execute(List<Instruction> instructions) {
        programCounter = 0;
        while (programCounter < instructions.size()) {
            Instruction instruction = instructions.get(programCounter);
            programCounter = instruction.execute(this);
        }
    }
    public void machineValues(){
        System.out.println("Registers");
        for (Map.Entry<RegisterName, Object> entry : registers.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
       // System.out.println("\n"); -gap is too terminal is already packed
        System.out.println("Variables:");
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
