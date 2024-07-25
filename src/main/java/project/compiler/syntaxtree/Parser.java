package project.compiler.syntaxtree;

import project.compiler.lexer.OperatorCheck;
import project.compiler.nodes.*;
import project.compiler.nodes.binarynodes.*;
import project.compiler.tokens.Token;
import project.compiler.tokens.TokenCheck;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Parser {
    public List<Token> tokens;
    public int currentPlace = 0;
    private Set<String> definedVariables = new HashSet<>();
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Node parse() throws Exception {
        Node ast;
        try {
            if (tokens.get(0).getKey() == TokenCheck.PRINT && tokens.size() == 2) {
                    ExpressionNode expression = new LiteralNode(tokens.get(1).getValue());
                    ast = new PrintStatementNode(expression);
            }else if(tokens.get(0).getKey() == TokenCheck.VARIABLE && tokens.size() == 3){
                String variableName = tokens.get(0).getValue();
                ExpressionNode expression = new LiteralNode(tokens.get(2).getValue());
                ast = new VariableAssignmentNode(variableName,expression);
            }else if (tokens.get(1).getKey() == TokenCheck.VAR_ASSIGN && tokens.get(4).getKey() == TokenCheck.VAR_ASSIGN) {
                String variableName1 = tokens.get(0).getValue();
                ExpressionNode value1 = new LiteralNode(tokens.get(2).getValue());
                VariableAssignmentNode varAssign1 = new VariableAssignmentNode(variableName1, value1);
                String variableName2 = tokens.get(3).getValue();
                ExpressionNode value2 = new LiteralNode(tokens.get(5).getValue());
                VariableAssignmentNode varAssign2 = new VariableAssignmentNode(variableName2, value2);
                    String variableNameRepeat = tokens.get(6).getValue();
                    String variableName2Repeat = tokens.get(8).getValue();

                    if (Objects.equals(variableName1, variableNameRepeat) && Objects.equals(variableName2, variableName2Repeat)) {
                        switch (tokens.get(7).getValue()) {
                            case "+":
                                ast = new AddVarExtended(variableName1,variableName2,value1,value2,"+");
                                break;
                            case "/":
                                ast = new DivVarExtended(variableName1,variableName2,value1,value2,"/");
                                break;
                            case "*":
                                ast = new MulVarExtended(variableName1,variableName2,value1,value2,"*");
                                break;
                            case "-":
                                ast = new SubVarExtended(variableName1,variableName2,value1,value2,"-");
                                break;
                            default:
                                ast = new UnknownNode("Unknown operation");
                        }
                    } else {
                        ast = new UnknownNode("Invalid variable used");

                }
            }else if(tokens.get(0).getKey() == TokenCheck.NUM_VAR && tokens.size() == 6){
                String variableName = tokens.get(0).getValue();
                ExpressionNode value = new LiteralNode(tokens.get(2).getValue());
                String variableName2 = tokens.get(3).getValue();
                ExpressionNode secondValue = new LiteralNode(tokens.get(5).getValue());
                if(Objects.equals(variableName, variableName2)) {

                    switch (tokens.get(4).getValue()){
                        case "+":
                            ast = new AddVariableNode(variableName, value, "+", secondValue, variableName2);
                            break;
                        case "-":
                            ast = new SubVariableNode(variableName, value, "-", secondValue, variableName2);
                            break;
                        case "*":
                            ast = new MulVariableNode(variableName, value, "*", secondValue, variableName2);
                            break;
                        case "/":
                            ast = new DivVariableNode(variableName, value, "/", secondValue, variableName2);
                            break;
                        default:
                            ast = new UnknownNode("Invalid operation");
                    }

                }else{
                    ast = new UnknownNode("Invalid variable");
                }
            }
            else if(OperatorCheck.isBasicBinaryList(tokens) && tokens.size() ==3){
                ExpressionNode left = new LiteralNode(tokens.get(0).getValue());
                ExpressionNode right = new LiteralNode(tokens.get(2).getValue());

                switch (tokens.get(1).getValue()){

                    case "-":
                        ast = new SubtractionNode( left,"-", right);
                        break;
                    case "+":
                        ast = new AdditionNode( left,"+", right);
                        break;
                    case "*":
                        ast = new MultiplicationNode( left,"*", right);
                        break;
                    case "/":
                        ast = new DivideNode( left,"/", right);
                        break;
                    default:
                        ast = new UnknownNode("Unknown operation");
                }




            }else if( tokens.get(0).getKey() == TokenCheck.VARIABLE && tokens.size() == 5 ) {
                System.out.println("check");
                String variableName = tokens.get(0).getValue();
                ExpressionNode value = new LiteralNode(tokens.get(2).getValue());
                String var = tokens.get(4).getValue();
                String functionCall = tokens.get(3).getValue() + " " + var;

            if (!Objects.equals(var, variableName)) {
                    ast = new UnknownNode("Unknown Token: incorrect variable");
                } else {
                    ast = new PrintVariableNode(variableName, value,functionCall);
                }
            }
            else {
                ast =  new UnknownNode("Unknown Token: " + tokens.get(0).getValue());
            }
        }catch (Exception e){
            ast = new UnknownNode(e.getMessage());
        }
        //System.out.println("AST before optimization: " + ast);

        //ast = OptimizedAST.optimize(ast);
        analyzeSemantic(ast);
        return ast;
    }

    public void printAST(Node ast) throws Exception {

        System.out.println("AST after optimization: " + ast);

    }
    public void analyzeSemantic(Node ast)throws Exception {
       // System.out.println("Analyzing semantics for AST: " + ast);
        if(ast instanceof BinaryVarExtended){
            BinaryVarExtended binaryVarExtended = (BinaryVarExtended) ast;
            ExpressionNode value = ((BinaryVarExtended) ast).getExpression();
            ExpressionNode secondValue = ((BinaryVarExtended) ast).getValue2();
            if (!(value instanceof LiteralNode && secondValue instanceof LiteralNode)) {
                throw new Exception("Invalid value");
            }

        }

        else if(ast instanceof BinaryAssignmentNode){
            ExpressionNode value = ((BinaryAssignmentNode) ast).getValue();
            ExpressionNode secondValue = ((BinaryAssignmentNode) ast).getSecondValue();
            if (!(value instanceof LiteralNode && secondValue instanceof LiteralNode)) {
                throw new Exception("Invalid value");
            }
        }
        else if (ast instanceof PrintStatementNode) {
            ExpressionNode expression = ((PrintStatementNode) ast).getExpression();
            checkExpression(expression);
        } else if(ast instanceof VariableAssignmentNode){
            String variableName = ((VariableAssignmentNode) ast).getVariableName();
            ExpressionNode expression  = ((VariableAssignmentNode)ast).getExpression();
        }else if(ast instanceof BinaryOperationNode) {
            ExpressionNode left = ((BinaryOperationNode) ast).getLeft();
            ExpressionNode right = ((BinaryOperationNode) ast).getRight();
            if (!(left instanceof LiteralNode && right instanceof LiteralNode)) {
                throw new Exception("As of this iteration both operands must be literals");
            }

        }
        else if(ast instanceof PrintVariableNode){
        //nothing needed as literal doesnt need process
        }

        else if(ast instanceof LiteralNode){
        //nothing needed as literal doesnt need process
        }
        else if(ast instanceof UnknownNode){
            throw new Exception(((UnknownNode) ast).getErrorMessage());
        }else{
            throw new Exception("Unrecognised AST Node");
        }
    }
    private void checkExpression(ExpressionNode expression) throws Exception{
        if(expression instanceof LiteralNode){

        }else if(expression instanceof VariableNode){
            String variableName = ((VariableNode) expression).getName();
            if (!definedVariables.contains(variableName)) {
                throw new Exception("Undefined variable: " + variableName);
            }
        }else{
            throw new Exception("Unrecognised expression");
        }
    }

}