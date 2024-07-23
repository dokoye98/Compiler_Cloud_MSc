package project.msc.nodes.binarynodes;

import project.msc.nodes.BinaryOperationNode;
import project.msc.nodes.ExpressionNode;

public class AdditionNode extends BinaryOperationNode {


    public AdditionNode(ExpressionNode left, String operator, ExpressionNode right) {
        super(left, operator, right);
    }

    @Override
    public String toString() {
        return "AdditionNode(" + getOperator() + "): " + getType() + "\n  |\n  +-- " + getLeft() + "\n  +-- " + getRight();
    }
}
