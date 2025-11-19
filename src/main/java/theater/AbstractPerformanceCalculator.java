package theater;

/**
 * A simple base calculator for a performance.
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
     * @throws RuntimeException when case is neither tragedy nor comedy
     */
    public static AbstractPerformanceCalculator createPerformanceCalculator(
            Performance performance, Play play) {

        switch (play.getType()) {
            case "tragedy":
                return new TragedyCalculator(performance, play);
            case "comedy":
                return new ComedyCalculator(performance, play);
            case "pastoral":
                return new PastoralCalculator(performance, play);
            case "history":
                return new HistoryCalculator(performance, play);
            default:
                throw new RuntimeException("unknown type: " + play.getType());
        }
    }

    public Performance getPerformance() {
        return performance;
    }

    public Play getPlay() {
        return play;
    }

    /**
     * Amount calculation logic moved from PerformanceData.
     * @return total amount
     * @throws RuntimeException when case is neither tragedy nor comedy.
     */
    public abstract int amount();

    /**
     * Volume credits logic moved from PerformanceData.
     * @return int volume credits
     */
    public int volumeCredits() {
        return Math.max(performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
    }
}
