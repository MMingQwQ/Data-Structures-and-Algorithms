
/**
 * Name: Mingming Zhang
 * 
 * 
 * COMP352 Section (AB)
 * 
 * Assignment # 2
 * 
 * Due Date: 09/06/2024
 * 
 * @author Mingming Zhang
 * @version 08/06/2024
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Evaluate arithmetic expressions from an input file and write the results to
 * an output file.
 */
public class ArithmeticCalculator {

    /**
     * Main method to read expressions from input file, evaluate them, and write
     * results to output file.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            writer = new FileWriter("output.txt");

            String expression;
            expression = reader.readLine();
            while (expression != null) {
                System.out.println("Evaluating expression: " + expression);
                int result = evaluateExpression(expression);
                writer.write(expression + " = " + result + "\n");
                System.out.println("Result: " + result);
                expression = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Evaluates a given arithmetic expression.
     * 
     * @param expression The arithmetic expression to evaluate
     * @return The result of the evaluated expression
     */
    public static int evaluateExpression(String expression) {
        DynamicArrayStack values = new DynamicArrayStack(10);
        DynamicArrayStack operators = new DynamicArrayStack(10);

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (isWhitespace(c)) {
                continue;
            }

            if (isDigit(c)) {
                int num = 0;
                while (i < expression.length() && isDigit(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                i--;
                values.push(num);
            } else if (c == '(') {
                operators.pushString("(");
            } else if (c == ')') {
                while (!operators.isEmpty() && !operators.peekString().equals("(")) {
                    String op = operators.popString();
                    int b = values.pop();
                    int a = values.pop();
                    int result = applyOperator(op, b, a);
                    values.push(result);
                }
                operators.pop(); // Remove the '(' from the stack
            } else if (isOperator(c)) {
                // Handle multi-character operators (e.g., ==, !=, >=, <=)
                String op = String.valueOf(c);
                if ((c == '=' || c == '!' || c == '>' || c == '<') && i + 1 < expression.length()
                        && expression.charAt(i + 1) == '=') {
                    op += expression.charAt(i + 1);
                    i++;
                }

                while (!operators.isEmpty() && precedence(operators.peekString()) >= precedence(op)) {
                    String topOp = operators.popString();
                    int b = values.pop();
                    int a = values.pop();
                    int result = applyOperator(topOp, b, a);
                    values.push(result);
                }
                operators.pushString(op);
            }
        }

        while (!operators.isEmpty()) {
            String op = operators.popString();
            int b = values.pop();
            int a = values.pop();
            int result = applyOperator(op, b, a);
            values.push(result);
        }

        return values.pop();
    }

    /**
     * Checks if a character is an operator.
     * 
     * @param c The character to check
     * @return True if the character is an operator, false otherwise
     */
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '>' || c == '<' || c == '=' || c == '!';
    }

    /**
     * Checks if a character is a whitespace.
     * 
     * @param c The character to check
     * @return True if the character is a whitespace, false otherwise
     */
    public static boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    /**
     * Checks if a character is a digit.
     * 
     * @param c The character to check
     * @return True if the character is a digit, false otherwise
     */
    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * Returns the precedence of an operator.
     * 
     * @param op The operator
     * @return The precedence of the operator
     */
    public static int precedence(String op) {
        switch (op) {
            case "^":
                return 3;
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            case ">":
            case "<":
            case ">=":
            case "<=":
            case "==":
            case "!=":
                return 0;
        }
        return -1;
    }

    /**
     * Applies an operator to two operands and returns the result. 1 is true, 0 is
     * false
     * 
     * @param op The operator
     * @param b  The second operand
     * @param a  The first operand
     * @return The result of applying the operator to the operands
     */
    public static int applyOperator(String op, int b, int a) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return a / b;
            case "^":
                return (int) Math.pow(a, b);
            case ">":
                return a > b ? 1 : 0;
            case "<":
                return a < b ? 1 : 0;
            case "==":
                return a == b ? 1 : 0;
            case "!=":
                return a != b ? 1 : 0;
            case ">=":
                return a >= b ? 1 : 0;
            case "<=":
                return a <= b ? 1 : 0;
        }
        throw new IllegalArgumentException("Invalid operator: " + op);
    }

}