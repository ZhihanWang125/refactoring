package theater;

/**
 * Class tragedy calculator holds situations for tragedy.
 */
public class TragedyCalculator extends AbstractPerformanceCalculator {
    public TragedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int amount() {
        int result = Constants.TRAGEDY_BASE_AMOUNT;
        if (getPerformance().getAudience() > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
            result += Constants.TRAGEDY_OVER_BASE_CAPACITY_PER_PERSON
                    * (getPerformance().getAudience() - Constants.TRAGEDY_AUDIENCE_THRESHOLD);
        }
        return result;
    }
}

