import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Sean Kelley
 *
 */
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x, final double e) {
        /*
         * returns 0 if the input is 0
         */
        if (x == 0) {
            return 0;
        }
        double r = x;
        boolean isWithinError = false;

        /*
         * checks to see if r is within the tolerance and runs Newton iteration
         * if not.
         */
        while (!isWithinError) {
            if (Math.abs(r * r - x) / x < e * e) {
                isWithinError = true;
            } else {
                r = (r + x / r) / 2;
            }
        }

        return r;
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
        /*
         * Asks user for the number to square root and the tolerance.
         */

        out.print(
                "Enter a positive number to compute a square root (enter a negative number to exit): ");
        double userNum = in.nextDouble();

        out.print(
                "What would you like the tolerance to be for the square root?): ");
        double userError = in.nextDouble();
        /*
         * checks to see if userNum is positive and calls the sqrt method.
         */
        while (userNum >= 0) {
            double sqrtOfUserNum = sqrt(userNum, userError);

            out.println(sqrtOfUserNum);

            out.print(
                    "Enter a positive number to compute a square root (enter a negative number to exit): ");
            userNum = in.nextDouble();
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
