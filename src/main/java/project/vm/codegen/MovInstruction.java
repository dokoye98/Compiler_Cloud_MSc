package project.vm.codegen;

public class MovInstruction extends Instruction{
    private final Object endPoint; //variable manipulation
    private final Object source; // been having issues with load class so need to offload issues here

    public MovInstruction(String label,  Object endPoint, Object source) {
        super(label, "MOV");
        this.endPoint = endPoint;
        this.source = source;
    }

    @Override
    public int execute(Machine machine) {
       // Object value;
     if(endPoint instanceof RegisterName){
         RegisterName register = (RegisterName) endPoint;
         int varValue = (int) machine.getVariableSingle((String) source);
          machine.setRegister(register,varValue);
     } else if (source instanceof RegisterName) {
        // RegisterName register = (RegisterName) source;
         int varValue = (int) machine.getVariableSingle((String) endPoint);
         machine.setVariable((String)endPoint,varValue);

     }
        return machine.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + endPoint + ", " + source;
    }
}
