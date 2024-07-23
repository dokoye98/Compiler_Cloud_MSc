package project.msc.nodes.binarynodes;

import project.msc.nodes.BinaryOperationNode;
import project.msc.nodes.ExpressionNode;

public class DivideNode extends BinaryOperationNode {


    public DivideNode(ExpressionNode left, String operator, ExpressionNode right) {
        super(left, operator, right);
    }

    @Override
    public String toString() {
        return "DivideNode(" + getOperator() + "): " + getType() + "\n  |\n  --- " + getLeft() + "\n  --- " + getRight();
    }
}
