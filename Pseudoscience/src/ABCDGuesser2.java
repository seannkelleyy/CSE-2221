import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * This program asks a user for a constant and 4 numbers to use the de Jager
 * numbers to approximate the constant provided.
 *
 * @author Sean Kelley
 *
 */
public final class ABCDGuesser2 {
    /**
     *
     */
    private ABCDGuesser2() {
    }

    /**
     *
     * @param constant
     *            the constant chosen by the client
     * @param w
     *            value chosen by client
     * @param x
     *            value chosen by client
     * @param y
     *            value chosen by client
     * @param z
     *            value chosen by client
     * @param out
     *            SimpleWriter out for output
     */
    private static void charmingTheory(double constant, double w, double x,
            double y, double z, SimpleWriter out) {

        final double[] deJagerNums = { -5, -4, -3, -2, -1, -1 / 2, -1 / 3,
                -1 / 4, 0, 1 / 4, 1 / 3, 1 / 2, 1, 2, 3, 4, 5 };

        final int zero = 0, one = 1, two = 2, three = 3, four = 4;
        double[] bestCombo = new double[four];
        int[] bestExponents = new int[four];
        double closestEstimate = 0;

        // tests different combinations of numbers and exponents to find the
        // closest combination.

        for (int a = 0; a < deJagerNums.length - 1; a++) {
            bestCombo[zero] = Math.pow(w, deJagerNums[a]);
            for (int b = 0; b < deJagerNums.length - 1; b++) {
                bestCombo[one] = Math.pow(x, deJagerNums[b]);
                for (int c = 0; c < deJagerNums.length - 1; c++) {
                    bestCombo[two] = Math.pow(y, deJagerNums[c]);
                    for (int d = 0; d < deJagerNums.length - 1; d++) {
                        bestCombo[three] = Math.pow(z, deJagerNums[d]);
                        double estimate = bestCombo[zero] * bestCombo[one]
                                * bestCombo[two] * bestCombo[three];
                        if (Math.abs(constant - estimate) < Math
                                .abs(constant - closestEstimate)) {
                            closestEstimate = estimate;
                            bestExponents[zero] = a;
                            bestExponents[one] = b;
                            bestExponents[two] = c;
                            bestExponents[three] = d;
                        }
                    }
                }
            }
        }
        out.println("Your numbers approximated to "
                + String.format("%.2f", closestEstimate) + " which is "
                + String.format("%.2f", (constant / closestEstimate))
                + "% error. The exponents used were: "
                + deJagerNums[bestExponents[zero]] + " "
                + deJagerNums[bestExponents[one]] + " "
                + deJagerNums[bestExponents[two]] + " "
                + deJagerNums[bestExponents[three]]);
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        boolean positiveDouble = false;
        double constant = 0;

        // asks user for a number and repeats until an acceptable number is provided
        while (!positiveDouble) {
            out.print("Enter a constant that is positive: ");
            String userInput = in.nextLine();
            if (FormatChecker.canParseDouble(userInput)) {
                constant = Double.parseDouble(userInput);
                if (constant > 0) {
                    positiveDouble = true;
                }
            } else {
                out.println("You must enter a positive constant.");
            }
        }
        return constant;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        boolean positiveDoubleNotOne = false;
        double constant = 0;

        // asks user for a number and repeats until an acceptable number is provided
        while (!positiveDoubleNotOne) {
            out.print(
                    "Enter a personal number that is positive and is not '1': ");
            String userInput = in.nextLine();
            if (FormatChecker.canParseDouble(userInput)) {
                constant = Double.parseDouble(userInput);
                if (constant >= 0 && constant != 1) {
                    positiveDoubleNotOne = true;
                }
            } else {
                out.println("You must enter a positive number that isn't '1'.");
            }
        }
        return constant;
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

        double constant;
        double w, x, y, z;
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        constant = getPositiveDouble(in, out);
        w = getPositiveDoubleNotOne(in, out);
        x = getPositiveDoubleNotOne(in, out);
        y = getPositiveDoubleNotOne(in, out);
        z = getPositiveDoubleNotOne(in, out);

        charmingTheory(constant, w, x, y, z, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
