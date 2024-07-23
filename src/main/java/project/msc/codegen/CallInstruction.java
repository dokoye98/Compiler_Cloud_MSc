package project.msc.codegen;

public class CallInstruction extends Instruction {
    private final String functionName;
    private final RegisterName register;

    private Integer left;
    private Integer right;

    public CallInstruction(String label, String functionName, RegisterName register) {
        super(label, "CALL");
        this.functionName = functionName;
        this.register = register;
    }



    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    @Override
    public int execute(Machine machine) {
        if ("print".equals(functionName)) {
            System.out.println(machine.getRegister(register));
        }else if("add".equals(functionName)){
            System.out.println(machine.getRegister(register));

        }
        return machine.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + functionName + ", " + register;
    }
}
