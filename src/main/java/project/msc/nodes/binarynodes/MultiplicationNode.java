package project.msc.nodes.binarynodes;

import project.msc.nodes.BinaryOperationNode;
import project.msc.nodes.ExpressionNode;

public class MultiplicationNode extends BinaryOperationNode {


    public MultiplicationNode(ExpressionNode left, String operator, ExpressionNode right) {
        super(left, operator, right);
    }

    @Override
    public String toString() {
        return "MultiplicationNode(" + getOperator() + "): " + getType() + "\n  |\n  *-- " + getLeft() + "\n  *-- " + getRight();
    }
}
