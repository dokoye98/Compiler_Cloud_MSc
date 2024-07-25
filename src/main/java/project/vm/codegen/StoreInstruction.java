package project.vm.codegen;

public class StoreInstruction extends Instruction{

    private final RegisterName register;
    private final String variableName;

    public StoreInstruction(String label,  RegisterName register, String variableName) {
        super(label, "STORE");
        this.register = register;
        this.variableName = variableName;
    }

    @Override
    public int execute(Machine machine) {
      Object value = machine.getRegister(register);
      machine.setVariable(variableName,value);
      return machine.getProgramCounter() + 1;

    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " "  + variableName + " , " + register;
    }


}
