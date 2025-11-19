package theater;

/**
 * A simple base calculator for a performance.  For now it only holds the data
 * and provides a factory method; calculation logic will be added/overridden
 * in subclasses in later steps.
 */
public abstract class AbstractPerformanceCalculator {

    private final Performance performance;
    private final Play play;

    public AbstractPerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    /**
     * Factory method used by StatementData.
     * @param performance performance
     * @param play play
     * @return abstract performance calculator
     *
     */
    public static AbstractPerformanceCalculator createPerformanceCalculator(
            Performance performance, Play play) {
        return new BasePerformanceCalculator(performance, play);
    }

    /**
     * Amount calculation logic moved from PerformanceData.
     * @return total amount
     * @throws RuntimeException when case is neither tragedy nor comedy.
     */
    public int amount() {
        int result = 0;
        switch (play.getType()) {
            case "tragedy":
                result = Constants.TRAGEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.TRAGEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience() - Constants.TRAGEDY_AUDIENCE_THRESHOLD);
                }
                break;
            case "comedy":
                result = Constants.COMEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.COMEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                            + (Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience() - Constants.COMEDY_AUDIENCE_THRESHOLD));
                }
                result += Constants.COMEDY_AMOUNT_PER_AUDIENCE * performance.getAudience();
                break;
            default:
                throw new RuntimeException("unknown type: " + play.getType());
        }
        return result;
    }

    /**
     * Volume credits logic moved from PerformanceData.
     * @return int volume credits
     */
    public int volumeCredits() {
        int result = Math.max(performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
        if ("comedy".equals(play.getType())) {
            result += performance.getAudience() / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        }
        return result;
    }
}

