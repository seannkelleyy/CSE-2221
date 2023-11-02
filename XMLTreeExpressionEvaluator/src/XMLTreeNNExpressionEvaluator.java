import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Sean Kelley
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        NaturalNumber num1, num2;
        String errorSubtract = "ERROR: Cannot subtract by larger number.";
        String errorDivide = "ERROR: Cannot divide by zero.";

        // assign first number to num1
        if (!exp.child(0).hasAttribute("value")) {
            num1 = evaluate(exp.child(0));
        } else {
            num1 = new NaturalNumber2(
                    Integer.parseInt(exp.child(0).attributeValue("value")));
        }

        // assign second number to num2
        if (!exp.child(1).hasAttribute("value")) {
            num2 = evaluate(exp.child(1));
        } else {
            num2 = new NaturalNumber2(
                    Integer.parseInt(exp.child(1).attributeValue("value")));
        }

        // finds operator and executes the operation
        String operation = exp.label();
        if (operation.equals("plus")) {
            num1.add(num2);
        } else if (operation.equals("minus")) {
            // reports if this >= n
            if (num2.compareTo(num1) > 0) {
                Reporter.fatalErrorToConsole(errorSubtract);
            }
            num1.subtract(num2);
        } else if (operation.equals("times")) {
            num1.multiply(num2);
        } else if (operation.equals("divide")) {
            // reports if divide by zero
            if (num2.isZero()) {
                Reporter.fatalErrorToConsole(errorDivide);
            }
            num1.divide(num2);
        }

        NaturalNumber solution = new NaturalNumber2();
        solution.copyFrom(num1);

        return solution;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
