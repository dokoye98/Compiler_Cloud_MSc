package project.vm.codegen;

public class LoadInstruction extends Instruction {

    private final RegisterName register;

    private final String value;

    public LoadInstruction(String label, RegisterName register, String value) {
        super(label, "LOAD");
        this.register = register;
        this.value = value;
    }

    @Override
    public int execute(Machine machine) {
        if (value.startsWith("\"") && value.endsWith("\"")) {
            machine.setRegister(register, value.substring(1, value.length() - 1));
        } else {
            try {

                machine.setRegister(register, Integer.parseInt(value));
            } catch (NumberFormatException e) {
                // Honestly error handling this was the error that kept popping up then
                String memoryValue = (String) machine.getVariableSingle(value);
                if (memoryValue != null) {

                    machine.setVariable(memoryValue,register);
                } else {
                    throw new IllegalArgumentException("Unknown variable: " + value);
                }
            }
        }
        return machine.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + register + ", " + value;
    }
}
