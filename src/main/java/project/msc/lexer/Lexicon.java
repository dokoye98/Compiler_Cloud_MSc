package project.msc.lexer;

import project.msc.tokens.Token;
import project.msc.tokens.TokenCheck;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexicon {


    private String input;
    private List<Token> tokens;

    public Lexicon(String input) {
        this.input = input;
        this.tokens = new ArrayList<>();
    }

    public void splitter() {
        List<String> lines = LineCheck.lineChecker(input);
        for (String line : lines) {
            //System.out.println(line);
            processor(line);
        }
    }

    public void processor(String line) {
        String remainingInput = line.trim();
        boolean matched = false;

        for (TokenCheck type : TokenCheck.values()) {
            Pattern pattern = Pattern.compile(type.getPattern(), Pattern.CASE_INSENSITIVE);
            Matcher match = pattern.matcher(remainingInput);

            if (match.matches()) {
                matched = true;

                if (type == TokenCheck.VARIABLE_ASSIGN && match.groupCount() == 2) {
                    String variableName = match.group(1);
                    String literalValue = match.group(2);
                    tokens.add(new Token(TokenCheck.VARIABLE, variableName));
                    tokens.add(new Token(TokenCheck.VAR_ASSIGN, "="));
                    tokens.add(new Token(TokenCheck.LITERAL, literalValue));

                } else if (type == TokenCheck.PRINT_ASSIGN && match.groupCount() == 2) {
                    String literal = match.group(2);
                    tokens.add(new Token(TokenCheck.PRINT, match.group(1)));
                    tokens.add(new Token(TokenCheck.LITERAL, literal));
                } else if (type == TokenCheck.NUM_VAR && match.groupCount() == 2) {
                    String functionCall = match.group(1);
                    String variableName = match.group(2);
                    tokens.add(new Token(TokenCheck.NUM_VAR, functionCall));
                    tokens.add(new Token(TokenCheck.VAR_ASSIGN, "="));
                    tokens.add(new Token(TokenCheck.LITERAL, variableName));
                } else if (type == TokenCheck.VAR_ADD && match.groupCount() == 2) {
                    String functionCall = match.group(1);
                    String variableName = match.group(2);
                    tokens.add(new Token(TokenCheck.VAR_ADD, functionCall));
                    tokens.add(new Token(TokenCheck.ADDITION, "+"));
                    tokens.add(new Token(TokenCheck.LITERAL, variableName));
                } else if (type == TokenCheck.VAR_ADD_VAR && match.groupCount() == 2) {
                    String variableName = match.group(1);
                    String variableName2 = match.group(2);
                    tokens.add(new Token(TokenCheck.VAR_ADD_VAR, variableName));
                    tokens.add(new Token(TokenCheck.ADDITION, "+"));
                    tokens.add(new Token(TokenCheck.VAR_ADD_VAR, variableName2));
                }else if (type == TokenCheck.VAR_DIV_VAR && match.groupCount() == 2) {
                    String variableName = match.group(1);
                    String variableName2 = match.group(2);
                    tokens.add(new Token(TokenCheck.VAR_DIV_VAR, variableName));
                    tokens.add(new Token(TokenCheck.DIVIDE, "/"));
                    tokens.add(new Token(TokenCheck.VAR_DIV_VAR, variableName2));
                }else if (type == TokenCheck.VAR_SUB_VAR && match.groupCount() == 2) {
                    String variableName = match.group(1);
                    String variableName2 = match.group(2);
                    tokens.add(new Token(TokenCheck.VAR_SUB_VAR, variableName));
                    tokens.add(new Token(TokenCheck.SUBTRACTION, "-"));
                    tokens.add(new Token(TokenCheck.VAR_SUB_VAR, variableName2));
                }else if (type == TokenCheck.VAR_MUL_VAR && match.groupCount() == 2) {
                    String variableName = match.group(1);
                    String variableName2 = match.group(2);
                    tokens.add(new Token(TokenCheck.VAR_MUL_VAR, variableName));
                    tokens.add(new Token(TokenCheck.MULTIPLICATION, "*"));
                    tokens.add(new Token(TokenCheck.VAR_MUL_VAR, variableName2));
                }
                else if (type == TokenCheck.VAR_SUB && match.groupCount() == 2) {
                    String functionCall = match.group(1);
                    String variableName = match.group(2);
                    tokens.add(new Token(TokenCheck.VAR_SUB, functionCall));
                    tokens.add(new Token(TokenCheck.SUBTRACTION, "-"));
                    tokens.add(new Token(TokenCheck.LITERAL, variableName));
                }
                else if (type == TokenCheck.VAR_MUL && match.groupCount() == 2) {
                    String functionCall = match.group(1);
                    String variableName = match.group(2);
                    tokens.add(new Token(TokenCheck.VAR_MUL, functionCall));
                    tokens.add(new Token(TokenCheck.MULTIPLICATION, "*"));
                    tokens.add(new Token(TokenCheck.LITERAL, variableName));
                }
                else if (type == TokenCheck.VAR_DIV && match.groupCount() == 2) {
                    String functionCall = match.group(1);
                    String variableName = match.group(2);
                    tokens.add(new Token(TokenCheck.VAR_DIV, functionCall));
                    tokens.add(new Token(TokenCheck.DIVIDE, "/"));
                    tokens.add(new Token(TokenCheck.LITERAL, variableName));
                }
                else if (type == TokenCheck.PRINT && match.groupCount() == 2) {
                    String literal = match.group(2);
                    tokens.add(new Token(TokenCheck.PRINT, match.group(1)));
                    tokens.add(new Token(TokenCheck.LITERAL, literal));
                } else if (type == TokenCheck.ADDITION && match.groupCount() == 2) {
                    String operand1 = match.group(1);
                    String operand2 = match.group(2);
                    tokens.add(new Token(TokenCheck.LITERAL, operand1));
                    tokens.add(new Token(TokenCheck.ADDITION, "+"));
                    tokens.add(new Token(TokenCheck.LITERAL, operand2));
                }
                else if (type == TokenCheck.SUBTRACTION && match.groupCount() == 2) {
                    String operand1 = match.group(1);
                    String operand2 = match.group(2);
                    tokens.add(new Token(TokenCheck.LITERAL, operand1));
                    tokens.add(new Token(TokenCheck.SUBTRACTION, "-"));
                    tokens.add(new Token(TokenCheck.LITERAL, operand2));
                } else if (type == TokenCheck.MULTIPLICATION && match.groupCount() == 2) {
                    String operand1 = match.group(1);
                    String operand2 = match.group(2);
                    tokens.add(new Token(TokenCheck.LITERAL, operand1));
                    tokens.add(new Token(TokenCheck.MULTIPLICATION, "*"));
                    tokens.add(new Token(TokenCheck.LITERAL, operand2));
                } else if (type == TokenCheck.DIVIDE && match.groupCount() == 2) {
                    String operand1 = match.group(1);
                    String operand2 = match.group(2);
                    tokens.add(new Token(TokenCheck.LITERAL, operand1));
                    tokens.add(new Token(TokenCheck.DIVIDE, "/"));
                    tokens.add(new Token(TokenCheck.LITERAL, operand2));
                } else {
                    tokens.add(new Token(type, match.group(0)));
                }
                remainingInput = remainingInput.substring(match.end()).trim();


                break;
            }
        }


        if (!matched) {
            tokens.add(new Token(TokenCheck.UNKNOWN, "Unknown command: " + remainingInput));
        }
    }

    public List<Token> getTokens() {

        return tokens;
    }
}



