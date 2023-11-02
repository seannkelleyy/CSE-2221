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
public final class Newton2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton2() {
    }

    /**
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        /*
         * returns 0 if the input is 0
         */
        if (x == 0) {
            return 0;
        }
        final double e = .0001;
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
         * Asks user if they want to square root a number
         */

        out.print(
                "Type 'y' to compute a square root (type any other key to exit): ");
        String userContinue = in.nextLine().toLowerCase();

        /*
         * checks to see if userNum is positive and calls the sqrt method.
         */
        while (userContinue.equals("y")) {
            out.print("Enter the number you would like to square root: ");
            double num = in.nextDouble();

            double sqrtOfUserNum = sqrt(num);

            out.println(sqrtOfUserNum);

            out.print(
                    "Type 'y' to compute a square root (type any other key to exit): ");
            userContinue = in.nextLine().toLowerCase();
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
