import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Sean Kelley
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {

        NaturalNumber topNum = model.top();
        NaturalNumber bottomNum = model.bottom();

        // Update view to reflect changes in model
        view.updateTopDisplay(topNum);
        view.updateBottomDisplay(bottomNum);

        view.updateDivideAllowed(!bottomNum.isZero());
        view.updateRootAllowed(bottomNum.compareTo(TWO) >= 0
                && bottomNum.compareTo(INT_LIMIT) <= 0);
        view.updateSubtractAllowed(topNum.compareTo(bottomNum) >= 0);

    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottomNum = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottomNum.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = topNum.newInstance();
        temp.transferFrom(topNum);
        topNum.transferFrom(bottomNum);
        bottomNum.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        /*
         * Update model in response to this event
         */
        topNum.copyFrom(bottomNum);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        /*
         * Update model in response to this event
         */
        topNum.add(bottomNum);
        bottomNum.transferFrom(topNum);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processSubtractEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        /*
         * Update model in response to this event
         */
        topNum.subtract(bottomNum);
        bottomNum.transferFrom(topNum);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processMultiplyEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        /*
         * Update model in response to this event
         */
        topNum.multiply(bottomNum);
        bottomNum.transferFrom(topNum);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processDivideEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        /*
         * Update model in response to this event
         */
        NaturalNumber remainder = topNum.divide(bottomNum);
        bottomNum.transferFrom(topNum);
        topNum.transferFrom(remainder);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processPowerEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        /*
         * Update model in response to this event
         */
        int i = bottomNum.toInt();
        topNum.power(i);
        bottomNum.transferFrom(topNum);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processRootEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        /*
         * Update model in response to this event
         */
        int r = bottomNum.toInt();
        topNum.root(r);
        bottomNum.transferFrom(topNum);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {
        /*
         * Get aliases to bottom from model
         */
        NaturalNumber bottomNum = this.model.bottom();

        /*
         * Update model in response to this event
         */
        bottomNum.multiplyBy10(digit);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

}
