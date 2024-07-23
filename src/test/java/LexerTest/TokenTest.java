package LexerTest;


import org.junit.Test;
import project.msc.lexer.Lexicon;
import project.msc.tokens.Token;
import project.msc.tokens.TokenCheck;

import java.util.List;
import static org.junit.Assert.*;
import static project.msc.lexer.LineCheck.lineChecker;

public class TokenTest {

    @Test
    public void testPrintStatementWithoutSpace() {
        Lexicon lexer = new Lexicon("print\"hello world\"");
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();

        assertEquals(2, tokens.size());
        assertEquals(TokenCheck.PRINT, tokens.get(0).getKey());
        assertEquals("print", tokens.get(0).getValue());
        assertEquals(TokenCheck.LITERAL, tokens.get(1).getKey());
        assertEquals("hello world", tokens.get(1).getValue());
    }

    @Test
    public void testPrintStatementWithSpace() {
        Lexicon lexer = new Lexicon("print \"hello world\"");
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();

        assertEquals(2, tokens.size());
        assertEquals(TokenCheck.PRINT, tokens.get(0).getKey());
        assertEquals("print", tokens.get(0).getValue());
        assertEquals(TokenCheck.LITERAL, tokens.get(1).getKey());
        assertEquals("hello world", tokens.get(1).getValue());
    }

    @Test
    public void testInvalidStatement() {
        Lexicon lexer = new Lexicon("invalid \"hello world\"");
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();

        assertEquals(1, tokens.size());
        assertEquals(TokenCheck.UNKNOWN, tokens.get(0).getKey());

       assertTrue(tokens.get(0).getValue().startsWith("invalid \"hello world\""));
    }

    @Test
    public void testExceptionHandling() {
        // Simulating a case where pattern matching could fail
        Lexicon lexer = new Lexicon("null");
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();

        assertEquals(1, tokens.size());
        assertEquals(TokenCheck.UNKNOWN, tokens.get(0).getKey());
        //assertTrue(tokens.get(0).getValue().startsWith("Failed to compile due to code: "));
    }
    @Test
    public void variables() {
        String code = "a = \"hello\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        assertEquals("a", tokens.get(0).getValue());
        assertEquals("=", tokens.get(1).getValue());
        assertEquals("hello", tokens.get(2).getValue());

    }
    @Test
    public void variablesSpacing() {
        String code = "x23= \"    hello\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        assertEquals("x23", tokens.get(0).getValue());
        assertEquals("=", tokens.get(1).getValue());
        assertEquals("    hello", tokens.get(2).getValue());
    }
    /*
    @Test - this code for previous method that was in compiler
    public void lineCheckTest() {
        String code = "wolves = \"big dogs\"; print wolves";
        List<String> lines = lineChecker(code);

        for (String line : lines) {
            System.out.println(line);
        }
    }*/
    @Test
    public void testAddition() {
        String code = "2 + 4";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        assertEquals(3, tokens.size());
        assertEquals("+", tokens.get(1).getValue());
        assertEquals("2", tokens.get(0).getValue());
        assertEquals("4", tokens.get(2).getValue());
    }

    @Test
    public void testSubtraction() {
        String code = "2 - 4";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        assertEquals(3, tokens.size());
        assertEquals("-", tokens.get(1).getValue());
        assertEquals("2", tokens.get(0).getValue());
        assertEquals("4", tokens.get(2).getValue());
    }
    @Test
    public void testAdditionSyntax() {
        String code = "5       +            5";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        assertEquals(3, tokens.size());
        assertEquals("+", tokens.get(1).getValue());
        assertEquals("5", tokens.get(0).getValue());
        assertEquals("5", tokens.get(2).getValue());
    }

    @Test
    public void testMultiply() {
        String code = "2 * 4";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        assertEquals(3, tokens.size());
        assertEquals("2", tokens.get(0).getValue());
        assertEquals("*", tokens.get(1).getValue());
        assertEquals("4", tokens.get(2).getValue());
    }
    @Test
    public void testMultiplySyntax() {
        String code = "2          *           4";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        assertEquals(3, tokens.size());
        assertEquals("2", tokens.get(0).getValue());
        assertEquals("*", tokens.get(1).getValue());
        assertEquals("4", tokens.get(2).getValue());
    }


@Test
    public void variableBinaryTest()
    {String code = "a =12; b = 22; a + b";
    Lexicon lexer = new Lexicon(code);
    lexer.splitter();
    List<Token> tokens = lexer.getTokens();
        System.out.println(tokens);
    System.out.println(tokens.size());
}



}
