package theater;

/**
 * A simple base calculator for a performance.  For now it only holds the data
 * and provides a factory method; calculation logic will be added/overridden
 * in subclasses in later steps.
 */
public class AbstractPerformanceCalculator {
    private final Performance performance;
    private final Play play;

    // Generate constructor (performance, play)
    public AbstractPerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    /**
     * Factory method: create an appropriate PerformanceCalculator.
     *
     * @param performance the performance information
     * @param play the play associated with the performance
     * @return new abstract performance calculator
     */
    public static AbstractPerformanceCalculator createPerformanceCalculator(Performance performance, Play play) {
        return new AbstractPerformanceCalculator(performance, play);
    }

    // placeholder methods: later steps will move amount/credits logic here
    public int amount() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public int volumeCredits() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

