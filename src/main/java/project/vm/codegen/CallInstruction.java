package project.vm.codegen;

public class CallInstruction extends Instruction {
    private final String functionName;
    private final Object value;

    private Integer left;
    private Integer right;

    public CallInstruction(String label, String functionName, Object value) {
        super(label, "CALL");
        this.functionName = functionName;
        this.value = value;
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
    public int execute(VM vm) {
        if ("print".equals(functionName)) {
           if(value instanceof RegisterName){
               System.out.println(vm.getRegister((RegisterName) value));
           }else if (value instanceof String){
               System.out.println(vm.getVariable((String) value));
           }
        }
        return vm.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + functionName + ", " + value;
    }
}
