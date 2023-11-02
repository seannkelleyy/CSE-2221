import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Model class.
 *
 * @author Sean Kelley
 */
public final class NNCalcModel1 implements NNCalcModel {

    /**
     * Model variables.
     */
    private final NaturalNumber top, bottom;

    /**
     * Default constructor.
     */
    public NNCalcModel1() {
        this.top = new NaturalNumber2();
        this.bottom = new NaturalNumber2();
    }

    @Override
    public NaturalNumber top() {

        return this.top;

        /*
         * This line added just to make the program compilable. Should be
         * replaced with appropriate return statement.
         */
    }

    @Override
    public NaturalNumber bottom() {

        return this.bottom;

        /*
         * This line added just to make the program compilable. Should be
         * replaced with appropriate return statement.
         */
    }

}
