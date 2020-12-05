package comp132.examples.adt;

/**
 * An infix expression evaluator. This class represents and infix expression
 * (e.g. "3 + 5" or "2 * 3 + 7"). The infix expression can be converted to
 * postfix and then evaluated using the PostfixExprression class. The infix
 * expressions must be entered with one space between each operand and each
 * operator, as shown above.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version May 5, 2010
 */
public class InfixExpression {

    private String expression;

    /**
     * Construct a new InfixExpression.
     * 
     * @param exp the expression.
     */
    public InfixExpression(String exp) {
        expression = exp;
    }

    /**
     * Convert this InfixExpression to postfix.
     * 
     * @return the postfix version of this expression.
     */
    public String toPostfix() {
        CS132LinkedStack operators = new CS132LinkedStack();
        String[] tokens = expression.split(" ");
        String postFix = "";

        for (String tok : tokens) {
            if (!isOperator(tok)) {
                postFix = postFix + " " + tok;
            }
            else {

                while (operators.size() > 0
                        && getPrecedence((String) operators.peek()) >= getPrecedence(tok)) {
                    postFix = postFix + " " + operators.pop();

                }
                operators.push(tok);
            }
        }

        while (operators.size() > 0) {
            postFix = postFix + " " + operators.pop();
        }

        return postFix.trim();
    }

    /*
     * Returns true if tok is an operator (+,-,*,/,^)
     */
    private boolean isOperator(String tok) {
        return tok.equals("+") || tok.equals("-") || tok.equals("*") || tok.equals("/")
                || tok.equals("^");
    }

    /**
     * Get the precedence of an operator.
     * 
     * @param op the operator.
     * @return the precedence of op. The higher the number the higher the
     *         operator's precedence.
     */
    private int getPrecedence(String op) {
        if ("+-".contains(op)) {
            return 1;
        }
        else if ("*/".contains(op)) {
            return 2;
        }
        else if ("^".contains(op)) {
            return 3;
        }
        else {
            return -1;
        }
    }

    /**
     * Evaluate this InfixExpression and return the result.
     * 
     * @return the result of evaluating the infix expression.
     */
    public int evaluate() {
        PostfixExpression pe = new PostfixExpression(toPostfix());
        return pe.evaluate();
    }

    /**
     * Convert a few infix expressions to postfix and display the results.
     * 
     * @param args none.
     */
    public static void main(String[] args) {
        String exp1 = "3 + 4";
        InfixExpression ie1 = new InfixExpression(exp1);
        System.out.println(exp1 + " = " + ie1.toPostfix());
        System.out.println(exp1 + " = " + ie1.evaluate());

        String exp2 = "15 * 3 + 2";
        InfixExpression ie2 = new InfixExpression(exp2);
        System.out.println(exp2 + " = " + ie2.toPostfix());
        System.out.println(exp2 + " = " + ie2.evaluate());

        String exp3 = "2 * 2 ^ 3 + 6 / 2 - 4 - 1";
        InfixExpression ie3 = new InfixExpression(exp3);
        System.out.println(exp3 + " = " + ie3.toPostfix());
        System.out.println(exp3 + " = " + ie3.evaluate());
    }

}
