
import java.util.Scanner;

/*
 * Convert a expression as String with infix operators to postfix operator 
 * and evaluate the expression 
 */
/**
 *
 * @author trangluong
 */
public class ExpressionTools {

    private String infixString;

    public ExpressionTools() {
        this.infixString = null;

    }

    public ExpressionTools(String infixString) {
        this.infixString = infixString;
    }

    public int InfixtoPostFix() throws PostFixException {
        String str = this.infixString;
        StringBuilder result = new StringBuilder("");
        Stack<Character> stack = new Stack<>(str.length());

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {

                if (stack.isEmpty()) {
                    throw new PostFixException();
                }
                while (!stack.isEmpty() && stack.peak() != '(') {

                    char operator = stack.pop();
                    result.append(' ').append(operator).append(' ');

                }
                stack.pop();

            } else if (c == '*' || c == '/' || c == '%') {
                if (str.charAt(i + 1) == '*' || str.charAt(i + 1) == '/'
                        || str.charAt(i + 1) == '%' || str.charAt(i + 1) == '+'
                        || str.charAt(i + 1) == '-') {
                    throw new PostFixException();
                }
                while (!stack.isEmpty() && (stack.peak() == '*' || stack.peak() == '/' || stack.peak() == '%')) {
                    char operator = stack.pop();
                    result.append(' ').append(operator).append(' ');
                }
                stack.push(c);
            } else if (c == '+' || c == '-') {
                if (str.charAt(i + 1) == '*' || str.charAt(i + 1) == '/'
                        || str.charAt(i + 1) == '%' || str.charAt(i + 1) == '+'
                        || str.charAt(i + 1) == '-') {
                    throw new PostFixException();
                }
                while (!stack.isEmpty() && stack.peak() != '(') {
                    char operator = stack.pop();
                    result.append(' ').append(operator).append(' ');
                }
                stack.push(c);
            } else {
                result.append(c);
            }

        }
        while (!stack.isEmpty()) {
            char operator = stack.pop();
            result.append(' ').append(operator).append(' ');
        }

        String re = "";

        re = "" + result;
        if (re.charAt(re.length() - 2) == '(') {
            throw new PostFixException();
        }

        return postFixEvaluation(re);
    }
//Check if a value is an operator or an operand
    //If operand, pop 2 values on top of stack in order 
    //and evaluate the result before pushing back to stack

    public int postFixEvaluation(String str) throws PostFixException {
        int result = 0;
        Stack<String> stack = new Stack<>(str.length());
        Scanner s = new Scanner(str);
        while (s.hasNext()) {
            String string = s.next();

            if (Character.isDigit(string.charAt(0))) {
                stack.push(string);

            } else {
                char operator = string.charAt(0);

                String operand2 = stack.pop();

                String operand1 = stack.pop();
                result = Evaluate(operand2, operand1, operator);
                stack.push("" + result);

            }

        }


        if (stack.count() >= 2) {
            throw new PostFixException();
        }

//        At the end of expression, last value on stack is result
        while (!stack.isEmpty()) {
            try {
                result = Integer.parseInt(stack.pop());
            } catch (Exception e) {
                System.out.println("Invalid expression");
            }
        }
        return result;
    }

    public int Evaluate(String operand2, String operand1, char operator) {
        int re = 0;
        int op2 = Integer.parseInt(operand2);
        int op1 = Integer.parseInt(operand1);
        switch (operator) {
            case '+':
                re = op1 + op2;
                break;
            case '-':
                re = op1 - op2;
                break;
            case '*':
                re = op1 * op2;
                break;
            case '/':
                re = op1 / op2;
                break;
            case '%':
                re = op1 % op2;
                break;
            default: // do nothing;
                break;

        }
        return re;

    }

}
