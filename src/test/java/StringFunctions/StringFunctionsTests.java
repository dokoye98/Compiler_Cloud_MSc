package StringFunctions;

import org.junit.Test;
import project.compiler.syntaxfunctions.StringChecker;
import project.compiler.tokens.Token;
import project.compiler.tokens.TokenCheck;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StringFunctionsTests {

    @Test
    public void testQuoteCheckWithValidToken() {
        List<Token> tokens = Arrays.asList(
                new Token(TokenCheck.PRINT, "PRINT"),
                new Token(TokenCheck.LITERAL, "'hello world'")
        );

        boolean result = StringChecker.quoteCheck(tokens);
        assertTrue(result);
    }

    @Test
    public void testQuoteCheckWithInvalidToken() {
        List<Token> tokens = Arrays.asList(
                new Token(TokenCheck.PRINT, "PRINT"),
                new Token(TokenCheck.LITERAL, "hello world")
        );

        boolean result = StringChecker.quoteCheck(tokens);
        assertFalse(result);
    }

    @Test
    public void testQuoteCheckLexerWithValidLiteral() {
        String literal = "'hello world'";
        boolean result = StringChecker.quoteCheckLexer(literal);
        assertTrue(result);
    }

    @Test
    public void testQuoteCheckLexerWithInvalidLiteral() {
        String literal = "hello world";
        boolean result = StringChecker.quoteCheckLexer(literal);
        assertFalse(result);
    }

    @Test
    public void testCleanLiteralWithValidLiteral() {
        String literal = "'hello world'";
        String cleaned = StringChecker.cleanLiteral(literal);
        assertEquals("hello world", cleaned);
    }

    @Test
    public void testCleanLiteralWithInvalidLiteral() {
        String literal = "hello world";
        String cleaned = StringChecker.cleanLiteral(literal);
        assertEquals("hello world", cleaned);
    }
}
