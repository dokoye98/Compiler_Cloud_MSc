package project.msc.nodes.binarynodes;

import project.msc.nodes.BinaryVarExtended;
import project.msc.nodes.ExpressionNode;
import project.msc.nodes.VariableNode;

public class AddVarExtended extends BinaryVarExtended {


    public AddVarExtended(String variableName, String variableName2, ExpressionNode expression, ExpressionNode value2, String operation) {
        super(variableName, variableName2, expression, value2, operation);
    }

    @Override
    public String getType() {
        return "Integer";
    }

    @Override
    public String toString() {
        return "VariableAssignment : void\n  |\n  +-- Variable(" + getVariableName() + ")\n  +-- " + getExpression() +
                "\nVariableAssignment : void\n  |\n  +-- Variable(" + getVariableName2() + ")\n  +-- " + getValue2() +
                "\n" + getClass().getSimpleName() + " (" + getOperation() + "): " + getType() + "\n  |\n  +-- Variable(" + getVariableName() + ")\n  +-- Variable(" + getVariableName2() + ")";
    }

}
