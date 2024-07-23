package project.msc.codegen;

public abstract class Instruction {
    private final String label;
    private final String opcode;

    public Instruction(String label, String opcode) {
        this.label = label;
        this.opcode = opcode;
    }

    public String getLabel() {
        return label;
    }

    public String getOpcode() {
        return opcode;
    }

    public abstract int execute(Machine machine);

    public String getLabelString() {
        return (getLabel() == null) ? "" : getLabel() + ": ";
    }

    @Override
    public abstract String toString();
}
