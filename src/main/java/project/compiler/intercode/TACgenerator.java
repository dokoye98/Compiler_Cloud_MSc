package project.compiler.intercode;

import project.compiler.nodes.*;
import project.vm.codegen.*;

import java.util.ArrayList;
import java.util.List;

public class TACgenerator {
    private int tempVarCounter = 0;

    private String getNextTempVar() {
        return "t" + (tempVarCounter++);
    }

    public List<Instruction> generateCode(Node ast) {
        List<Instruction> instructions = new ArrayList<>();
        if (ast instanceof PrintStatementNode) {
            PrintStatementNode printNode = (PrintStatementNode) ast;
            ExpressionNode value = printNode.getExpression();
            RegisterName reg1 = getNextRegister();
            if (value instanceof LiteralNode) {
                String literalString = "\"" + ((LiteralNode) value).getValue() + "\"";
                instructions.add(new LoadStringInstruction(null, reg1,literalString ));
                instructions.add(new CallInstruction(null, "print", reg1));
            }
            else if (value instanceof VariableNode) {
                String variableName = ((VariableNode) value).getName();
                instructions.add(new MovInstruction(null, RegisterName.R1, variableName));
                instructions.add(new CallInstruction(null, "print", RegisterName.R1));
            }

        } else if (ast instanceof BinaryOperationNode) {
            BinaryOperationNode binaryOperationNode = (BinaryOperationNode) ast;
            ExpressionNode left = binaryOperationNode.getLeft();
            ExpressionNode right = binaryOperationNode.getRight();
            String operation = binaryOperationNode.getOperator();

            if (left instanceof LiteralNode && right instanceof LiteralNode) {
                RegisterName reg1 = getNextRegister();
                RegisterName reg2 = getNextRegister();
                RegisterName regResult = getNextRegister();
                instructions.add(new LoadInstruction(null, reg1, ((LiteralNode) left).getValue()));
                instructions.add(new LoadInstruction(null, reg2, ((LiteralNode) right).getValue()));

                switch (operation) {
                    case "+":
                        instructions.add(new AddInstruction(null, regResult, reg1, reg2));
                        break;
                    case "-":
                        instructions.add(new SubInstruction(null, regResult, reg1, reg2));
                        break;
                    case "*":
                        instructions.add(new MulInstruction(null, regResult, reg1, reg2));
                        break;
                    case "/":
                        instructions.add(new DivInstruction(null, regResult, reg1, reg2));
                        break;
                }

                instructions.add(new MovInstruction(null, "result", regResult));
                instructions.add(new CallInstruction(null, "print", regResult));
            }
        } else if (ast instanceof BinaryAssignNode) {
            BinaryAssignNode binaryAssignNode = (BinaryAssignNode) ast;
            VariableAssignmentNode assignOperationOne = binaryAssignNode.getVariableOne();
            VariableAssignmentNode assignOperationTwo = binaryAssignNode.getVariableTwo();
            String operation = binaryAssignNode.getOperation();
            String totalVar = binaryAssignNode.getVariableName();
            RegisterName reg1 = getNextRegister();
            RegisterName reg2 = getNextRegister();
            RegisterName reg3 = getNextRegister();
            RegisterName reg4 = getNextRegister();
            RegisterName regResult = getNextRegister();

            if (assignOperationOne != null && assignOperationTwo != null) {
                ExpressionNode value1 = assignOperationOne.getExpression();
                ExpressionNode value2 = assignOperationTwo.getExpression();
                if (value1 instanceof LiteralNode && value2 instanceof LiteralNode) {
                    instructions.add(new LoadInstruction(null, reg1, ((LiteralNode) value1).getValue()));
                    instructions.add(new StoreInstruction(null, reg1, assignOperationOne.getVariableName()));
                    instructions.add(new LoadInstruction(null, reg2, ((LiteralNode) value2).getValue()));
                    instructions.add(new StoreInstruction(null, reg2, assignOperationTwo.getVariableName()));
                    instructions.add(new MovInstruction(null, reg3, assignOperationOne.getVariableName()));
                    instructions.add(new MovInstruction(null, reg4, assignOperationTwo.getVariableName()));
                    switch (operation) {
                        case "+":
                            instructions.add(new AddInstruction(null, regResult, reg3, reg4));
                            break;
                        case "-":
                            instructions.add(new SubInstruction(null, regResult, reg3, reg4));
                            break;
                        case "*":
                            instructions.add(new MulInstruction(null, regResult, reg3, reg4));
                            break;
                        case "/":
                            instructions.add(new DivInstruction(null, regResult, reg3, reg4));
                            break;
                    }

                    instructions.add(new MovInstruction(null, totalVar, regResult));
                    instructions.add(new CallInstruction(null, "print", totalVar));
                }
            }
        } else if (ast instanceof BinaryAssignmentNode) {
            BinaryAssignmentNode binaryAssignmentNode = (BinaryAssignmentNode) ast;
            String variableName = binaryAssignmentNode.getVariableName();
            ExpressionNode value = binaryAssignmentNode.getValue();
            ExpressionNode secondValue = binaryAssignmentNode.getSecondValue();
            String operation = binaryAssignmentNode.getOperation();
            RegisterName reg1 = getNextRegister();
            RegisterName reg2 = getNextRegister();
            RegisterName regResult = getNextRegister();

            if (value instanceof LiteralNode && secondValue instanceof LiteralNode) {
                instructions.add(new LoadInstruction(null, reg1, ((LiteralNode) value).getValue()));
                instructions.add(new StoreInstruction(null, reg1, variableName));
                instructions.add(new LoadInstruction(null, reg2, ((LiteralNode) secondValue).getValue()));

                switch (operation) {
                    case "+":
                        instructions.add(new AddInstruction(null, regResult, reg1, reg2));
                        break;
                    case "-":
                        instructions.add(new SubInstruction(null, regResult, reg1, reg2));
                        break;
                    case "*":
                        instructions.add(new MulInstruction(null, regResult, reg1, reg2));
                        break;
                    case "/":
                        instructions.add(new DivInstruction(null, regResult, reg1, reg2));
                        break;
                }

                instructions.add(new MovInstruction(null, variableName, regResult));
                instructions.add(new CallInstruction(null, "print", regResult));
            }
        } else if (ast instanceof BinaryVarExtended) {
            BinaryVarExtended binaryVarExtended = (BinaryVarExtended) ast;
            String variableName = binaryVarExtended.getVariableName();
            ExpressionNode value = binaryVarExtended.getExpression();
            ExpressionNode secondValue = binaryVarExtended.getValue2();
            String operation = binaryVarExtended.getOperation();
            String variableName2 = binaryVarExtended.getVariableName2();
            RegisterName reg1 = getNextRegister();
            RegisterName reg2 = getNextRegister();
            RegisterName regResult = getNextRegister();

            if (value instanceof LiteralNode && secondValue instanceof LiteralNode) {

                instructions.add(new LoadInstruction(null, reg1, ((LiteralNode) value).getValue()));
                instructions.add(new StoreInstruction(null, reg1, variableName));
                instructions.add(new LoadInstruction(null, reg2, ((LiteralNode) secondValue).getValue()));
                instructions.add(new StoreInstruction(null, reg2, variableName2));

                switch (operation) {
                    case "+":
                        instructions.add(new AddInstruction(null, regResult, reg1, reg2));
                        break;
                    case "-":
                        instructions.add(new SubInstruction(null, regResult, reg1, reg2));
                        break;
                    case "*":
                        instructions.add(new MulInstruction(null, regResult, reg1, reg2));
                        break;
                    case "/":
                        instructions.add(new DivInstruction(null, regResult, reg1, reg2));
                        break;
                }

                instructions.add(new MovInstruction(null, variableName, regResult));
                instructions.add(new CallInstruction(null, "print", regResult));
            }
        } else if (ast instanceof LiteralNode) {

            instructions.add(new LoadStringInstruction(null, RegisterName.R0, ((LiteralNode) ast).getValue()));
        } else if (ast instanceof PrintVariableNode) {
            PrintVariableNode printNode = (PrintVariableNode) ast;
            String variableName = printNode.getVariableName();
            ExpressionNode value = printNode.getVariableValue();
            RegisterName reg1 = getNextRegister();
            RegisterName reg2 = getNextRegister();

            String literalString = "\"" + ((LiteralNode) value).getValue() + "\"";
            if (value instanceof LiteralNode) {
                instructions.add(new LoadStringInstruction(null, reg1, literalString));
                instructions.add(new StoreInstruction(null, reg1, variableName));
                instructions.add(new MovInstruction(null, reg2,variableName));
                instructions.add(new CallInstruction(null, "print", reg2));
            } else if (value instanceof VariableNode) {
                instructions.add(new MovInstruction(null, RegisterName.R0, ((VariableNode) value).getName()));
                instructions.add(new CallInstruction(null, "print", RegisterName.R0));
            }
        } else if (ast instanceof VariableAssignmentNode) {
            VariableAssignmentNode variableAssignmentNode = (VariableAssignmentNode) ast;
            String variableName = variableAssignmentNode.getVariableName();
            ExpressionNode value = variableAssignmentNode.getExpression();
            RegisterName reg = getNextRegister();
            String literalString = "\"" + ((LiteralNode) value).getValue() + "\"";
            if (value instanceof LiteralNode) {
                instructions.add(new LoadStringInstruction(null, reg, literalString));
                instructions.add(new StoreInstruction(null, reg, variableName));
            }
        }

        return instructions;
    }

    private RegisterName getNextRegister() {
        return RegisterName.values()[tempVarCounter++ % RegisterName.values().length];
    }
}
