package theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class generates an entity of Statement Data.
 */
public class StatementData {
    private String customer;
    private final List<PerformanceData> performances = new ArrayList<>();

    public StatementData(Invoice invoice, Map<String, Play> plays) {
        this.customer = invoice.getCustomer();

        for (Performance p : invoice.getPerformances()) {
            final Play play = plays.get(p.getPlayID());
            this.performances.add(createPerformanceData(p, play));
        }
    }

    private PerformanceData createPerformanceData(Performance performance, Play play) {
        final AbstractPerformanceCalculator calculator =
                AbstractPerformanceCalculator.createPerformanceCalculator(performance, play);
        return new PerformanceData(
                performance,
                play,
                calculator.amount(),
                calculator.volumeCredits()
        );
    }

    public String getCustomer() {
        return customer;
    }

    public List<PerformanceData> getPerformances() {
        return performances;
    }

    /**
     * Returns an int of total amount.
     *
     * @return the formatted statement
     */
    public int totalAmount() {
        int total = 0;
        for (PerformanceData p : performances) {
            total += p.getAmount();
        }
        return total;
    }

    /**
     * Returns an int of volume credit.
     *
     * @return the formatted statement
     *
     */
    public int volumeCredits() {
        int volumeCredits = 0;
        for (PerformanceData p : performances) {
            volumeCredits += p.getVolumeCredits();
        }
        return volumeCredits;
    }
}
