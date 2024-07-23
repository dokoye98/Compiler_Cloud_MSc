package project.msc;

import project.msc.assemblyInstructions.AssemblyGenerator;
import project.msc.codegen.CodeGenerator;
import project.msc.codegen.Instruction;
import project.msc.codegen.Machine;
import project.msc.lexer.Lexicon;
import project.msc.nodes.Node;
import project.msc.syntaxtree.Parser;
import project.msc.intercode.TACgenerator;
import project.msc.tokens.Token;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Compiler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ready to receive input:");
        System.out.flush();
        while (scanner.hasNextLine()) {
            String inputLine = scanner.nextLine();
            if (inputLine.equalsIgnoreCase("exit")) {
                break; // Stop processing if "exit" is received
            }
            Lexicon lexer = new Lexicon(inputLine);
            lexer.splitter();
            Stream<Token> tokensStep1 = lexer.getTokens().stream();
            tokensStep1.forEach(t-> {
                System.out.println(t);
                System.out.flush();
            });
            //System.out.println("checker");
            List<Token> tokens = lexer.getTokens();

            Parser parser = new Parser(tokens);
           try{
               Node ast = parser.parse();
               parser.printAST(ast);
               parser.analyzeSemantic(ast);
               //System.out.println(ast.getClass());
               TACgenerator tac = new TACgenerator();
               List<String> tacCode = tac.generateTAC(ast);
               for(String line: tacCode){
                   System.out.println(line);
               }
               CodeGenerator generator = new CodeGenerator();
               List<Instruction> instructions = generator.generateCode(ast);

               for (Instruction instruction : instructions) {
                  // System.out.println("check");
                   System.out.println(instruction);
               }

               Machine machine = new Machine();
               machine.execute(instructions);
               machine.machineValues();
               System.out.println("Assembly Code here:\n");
               System.out.println(AssemblyGenerator.assemble(ast));
           } catch (Exception e) {
               throw new RuntimeException(e);
           }


        }

        System.out.println("Finished processing input.");
        System.out.flush();
        scanner.close();
    }
}