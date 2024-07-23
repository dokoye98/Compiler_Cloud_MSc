package project.msc.nodes;

public class NumericNode extends Node {
    private final int value;

    NumericNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}