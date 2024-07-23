package project.msc.nodes.binarynodes;

import project.msc.nodes.BinaryAssignmentNode;
import project.msc.nodes.ExpressionNode;

public class AddVariableNode extends BinaryAssignmentNode {


    public AddVariableNode(String variableName, ExpressionNode value, String operation, ExpressionNode secondValue, String variableNameRepeat) {
        super(variableName, value, operation, secondValue, variableNameRepeat);
    }

    @Override
    public String toString() {
        return "AddVariableNode(" + getOperation() + "): " + getType() + "\n  |\n  +-- Variable(" + getVariableName() + ")\n  +-- " + getValue() + "\n  +-- " + getSecondValue();
    }
}
