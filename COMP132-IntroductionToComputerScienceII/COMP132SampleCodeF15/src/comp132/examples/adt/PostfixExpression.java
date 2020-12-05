package comp132.examples.adt;

/**

 * A postfix expression evaluator. The postfix expressions must be 
 * entered with one space between each operand and each operator.
 * For example: "3 4 +" or "15 3 * 2 +"
 * 
 * This expression evaluator is compatible with the InfixExpression class
 * that converts infix expressions to postfix.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version May 5, 2010
 */
public class PostfixExpression {

    private String expression;
    
    /**
     * Construct a new PostfixExpression using the provided string.
     * 
     * @param exp the postfix expresion to evaluate.
     */
    public PostfixExpression(String exp) {
        expression = exp;
    }
    
    /**
     * Evaluate this PostfixExpression and return the result.
     * 
     * @return the result of evaluating the postfix expression.
     */
    public int evaluate() {
        CS132LinkedStack operands = new CS132LinkedStack();
        String[] tokens = expression.split(" ");
        
        for (String tok : tokens) {
            if (!isOperator(tok)) {
                Integer num = new Integer(tok);
                operands.push(num);
            }
            else {
                Integer y = (Integer) operands.pop();
                Integer x = (Integer) operands.pop();
                Integer result = processOperator(tok, x, y);
                operands.push(result);
            }
        }

        return ((Integer) operands.pop()).intValue();
    }

    /*
     * Returns true if tok is an operator (+,-,*,/,^)
     */
    private boolean isOperator(String tok) {
        return tok.equals("+") || tok.equals("-") || tok.equals("*") || tok.equals("/")
                || tok.equals("^");
    }

    /*
     * Process the operator.
     */
    private Integer processOperator(String op, Integer val1, Integer val2) {
        if (op.equals("+")) {
            return new Integer(val1.intValue() + val2.intValue());
        }
        else if (op.equals("-")) {
            return new Integer(val1.intValue() - val2.intValue());
        }
        else if (op.equals("*")) {
            return new Integer(val1.intValue() * val2.intValue());
        }
        else if (op.equals("/")) {
            return new Integer(val1.intValue() / val2.intValue());
        }
        else if (op.equals("^")) {
            return new Integer((int) (Math.round(Math.pow(val1.intValue(), val2.intValue()))));
        }
        else {
            throw new IllegalArgumentException("Invalid Operator: " + op);
        }
    }
    
    /**
     * Evaluate a few postfix expressions and display the results.
     * 
     * @param args none.
     */
    public static void main(String[] args) {
        String exp1 = "3 4 +";
        PostfixExpression pe1 = new PostfixExpression("3 4 +");
        System.out.println(exp1 + " = " + pe1.evaluate());

        String exp2 = "15 3 * 2 +";
        PostfixExpression pe2 = new PostfixExpression(exp2);
        System.out.println(exp2 + " = " + pe2.evaluate());

        String exp3 = "2 2 3 ^ * 6 2 / + 4 - 1 -";
        PostfixExpression pe3 = new PostfixExpression(exp3);
        System.out.println(exp3 + " = " + pe3.evaluate());
    }
}
