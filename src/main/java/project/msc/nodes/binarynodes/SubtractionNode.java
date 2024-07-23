package project.msc.nodes.binarynodes;

import project.msc.nodes.BinaryOperationNode;
import project.msc.nodes.ExpressionNode;

public class SubtractionNode extends BinaryOperationNode {


    public SubtractionNode(ExpressionNode left, String operator, ExpressionNode right) {
        super(left, operator, right);
    }

    @Override
    public String getType() {
        return "Integer";
    }

    @Override
    public String toString() {
        return "Subtraction(" + getOperator() + "): " + getType() + "\n  |\n  --- " + getLeft() + "\n  --- " + getRight();
    }
}
