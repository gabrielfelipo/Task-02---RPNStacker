import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Task2RPNCalcSS {

    private static boolean isValid(String string) {
        return string != null && string.matches("[0-9]*");
    }

    public static void main(String[] args) throws Exception {

        Stack<String> stackStr = new Stack<String>();
        List<Token> ListaTokens = new ArrayList<>();
        File text = new File("Calc1Task2.stk");

        try (Scanner scanner = new Scanner(text)) {
            
            while(scanner.hasNextLine()){
                
                String digit = scanner.nextLine();

                if (isValid(digit)) {
                    Token token = new Token(TokenType.NUM, digit);
                    ListaTokens.add(token);

                } else if (digit.equals("+")) {
                    Token token = new Token(TokenType.PLUS, digit);
                    ListaTokens.add(token);

                } else if (digit.equals("-")) {
                    Token token = new Token(TokenType.MINUS, digit);
                    ListaTokens.add(token);
                } else if (digit.equals("/")) {
                    Token token = new Token(TokenType.SLASH, digit);
                    ListaTokens.add(token);
                } else if (digit.equals("*")) {
                    Token token = new Token(TokenType.STAR, digit);
                    ListaTokens.add(token);

                } else {
                    throw new Exception();
                }
            }
        }
        
        while(!ListaTokens.isEmpty()){

            String digit = ListaTokens.remove(0).lexeme;

            switch(digit){
                case "+":
                case "-":
                case "/":
                case "*":

                    int x, y;
                    x = Integer.parseInt(stackStr.pop());
                    y = Integer.parseInt(stackStr.pop());
                    switch(digit){
                        case "+":
                            stackStr.push(Integer.toString(y + x));
                        break;
                        case "-":
                            stackStr.push(Integer.toString(y - x));
                        break;
                        case "/":
                            stackStr.push(Integer.toString(y / x));
                        break;
                        case "*":
                            stackStr.push(Integer.toString(y * x));
                        break;
                    }
                break;
                default:
                    stackStr.push(digit);
                break;
            }
        }

        System.out.println("O resultado é: " + stackStr.pop());
    }
}
