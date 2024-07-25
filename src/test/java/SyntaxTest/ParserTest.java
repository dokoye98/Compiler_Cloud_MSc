package SyntaxTest;

import org.junit.jupiter.api.Test;
import project.compiler.lexer.Lexicon;
import project.compiler.nodes.*;
import project.compiler.nodes.binarynodes.AdditionNode;
import project.compiler.syntaxtree.Parser;
import project.compiler.tokens.Token;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void validPrintTest1() throws Exception {
        String code = "print \"hello world\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(PrintStatementNode.class, ast);
        PrintStatementNode printNode = (PrintStatementNode) ast;
        assertEquals("Literal(\"hello world\") : String", printNode.getExpression().toString());
    }
    @Test
    public void validPrintTest2() throws Exception {
        String code = "print\"hello world\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(PrintStatementNode.class, ast);
        PrintStatementNode printNode = (PrintStatementNode) ast;
        assertEquals("Literal(\"hello world\") : String", printNode.getExpression().toString());
    }
    @Test
    public void caseSensitivePrint() throws Exception {
        String code = "PRINT\"hello world\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(PrintStatementNode.class, ast);
        PrintStatementNode printNode = (PrintStatementNode) ast;
        assertEquals("Literal(\"hello world\") : String", printNode.getExpression().toString());
    }
    @Test
    public void stringSpace() throws Exception {
        String code = "print\"hello                       world\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(PrintStatementNode.class, ast);
        PrintStatementNode printNode = (PrintStatementNode) ast;
        assertEquals("Literal(\"hello                       world\") : String", printNode.getExpression().toString());
    }
    @Test
    public void emptyStringLiteral() throws Exception {
        String code = "print \"\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(PrintStatementNode.class, ast);
        PrintStatementNode printNode = (PrintStatementNode) ast;
        assertEquals("Literal(\"\") : String", printNode.getExpression().toString());
    }

    @Test
    public void specialCharacterStringLiteral() throws Exception {
        String code = "print \"hello\\nworld\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(PrintStatementNode.class, ast);
        PrintStatementNode printNode = (PrintStatementNode) ast;
        assertEquals("Literal(\"hello\\nworld\") : String", printNode.getExpression().toString());
    }

    @Test
    public void whitespaceHandling() throws Exception {
        String code = "    print \"hello world\"   ";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(PrintStatementNode.class, ast);
        PrintStatementNode printNode = (PrintStatementNode) ast;
        assertEquals("Literal(\"hello world\") : String", printNode.getExpression().toString());
    }
    @Test
    public void additionTest() throws Exception {
        String code = "5 + 5";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        System.out.println(tokens);
        Parser parser = new Parser(tokens);
        Node ast = parser.parse();
        assertInstanceOf(BinaryOperationNode.class, ast);
        BinaryOperationNode binaryNode = (AdditionNode) ast;



    }
    @Test
    public void similarToPrint() {
        String code = "prin \"hello world\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Exception exception = assertThrows(Exception.class, parser::parse);
       // assertTrue(exception.getMessage().contains("Unknown Token:"));
    }


    @Test
    public void similarToPrint2() {
        String code = "rint \"hello world\"";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Exception exception = assertThrows(Exception.class, parser::parse);
       // assertTrue(exception.getMessage().contains("Unknown Token:"));
    }
    @Test
    public void invalidPrint() {
        String code = "print hello world";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Exception exception = assertThrows(Exception.class, parser::parse);

       // assertTrue(exception.getMessage().contains("Unknown Token:"));
    }


    @Test
    public void invalidFunction() {
        String code = "hello world";
        Lexicon lexer = new Lexicon(code);
        lexer.splitter();
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        Exception exception = assertThrows(Exception.class, parser::parse);
        //assertTrue(exception.getMessage().contains("Unknown Token:"));
    }



}