package project.vm.codegen;

public class AddInstruction extends Instruction{

    private final RegisterName operand1;
    private final RegisterName result;

    private final RegisterName operand2;

    public AddInstruction(String label, RegisterName result, RegisterName operand1, RegisterName operand2) {
        super(label, "ADD");
        this.result = result;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public int execute(Machine machine) {
        //System.out.println(machine.getRegister(operand1)+ " "+ machine.getRegister(operand2));
        int value = (int) machine.getRegister(operand1);
        int value1 = (int) machine.getRegister(operand2);
        //int hex = value1 + value;
       machine.setRegister(result,value+value1);

       return machine.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + ", " + operand1 + " , " + operand2;
    }
}
