package theater;

/**
 * This class generates an entity of Performance Data.
 */
public class PerformanceData {
    private final Performance performance;
    private final Play play;
    private final int amount;
    private final int volumeCredits;

    public PerformanceData(Performance performance, Play play, int amount, int volumeCredits) {
        this.performance = performance;
        this.play = play;
        this.amount = amount;
        this.volumeCredits = volumeCredits;
    }

    public Performance getPerformance() {
        return performance;
    }

    public Play getPlay() {
        return play;
    }

    public int getAudience() {
        return performance.getAudience();
    }

    public String getType() {
        return play.getType();
    }

    public int getAmount() {
        return amount;
    }

    public int getVolumeCredits() {
        return volumeCredits;
    }

    /**
     * Get the amount of credits of case comedy.
     * @return the int volume credits
     * @throws RuntimeException when case is neither tragedy nor comedy
     */

    public int amountFor() {
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
}
