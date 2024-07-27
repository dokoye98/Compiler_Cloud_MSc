package project.compiler.lexer;

import project.compiler.tokens.TokenCheck;

public class VarAssignOperation {

    public static boolean isBinaryVar(TokenCheck type) {
        return type == TokenCheck.VAR_DIV_VAR || type == TokenCheck.VAR_MUL_VAR ||
                type == TokenCheck.VAR_ADD_VAR || type == TokenCheck.VAR_SUB_VAR;
    }

    public static TokenCheck getVarKey(TokenCheck type) {
        switch (type) {
            case NUM_VAR_ADD:
                return TokenCheck.NUM_VAR_ADD;
            case NUM_VAR_SUB:
                return TokenCheck.NUM_VAR_SUB;
            case NUM_VAR_MUL:
                return TokenCheck.NUM_VAR_MUL;
            case NUM_VAR_DIV:
                return TokenCheck.NUM_VAR_ADD;
            default:
                throw new IllegalArgumentException("Unknown operation type");
        }
    }

    public static TokenCheck getVarOperation(TokenCheck type) {
        switch (type) {
            case NUM_VAR_ADD:
                return TokenCheck.ADDITION;
            case NUM_VAR_SUB:
                return TokenCheck.SUBTRACTION;
            case NUM_VAR_MUL:
                return TokenCheck.MULTIPLICATION;
            case NUM_VAR_DIV:
                return TokenCheck.DIVIDE;
            default:
                throw new IllegalArgumentException("Unknown operation type");
        }
    }

    public static String getOperatorSymbol(TokenCheck type) {
        switch (type) {
            case NUM_VAR_ADD:
                return "+";
            case NUM_VAR_SUB:
                return "-";
            case NUM_VAR_MUL:
                return "*";
            case NUM_VAR_DIV:
                return "/";
            default:
                throw new IllegalArgumentException("Unknown operation type");

        }
    }
}
