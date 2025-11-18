package theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class generates an entity of Statement Data.
 */
public class StatementData {
    private String customer;
    private List<PerformanceData> performances = new ArrayList<>();

    public StatementData(Invoice invoice, Map<String, Play> plays) {
        this.customer = invoice.getCustomer();
        // populate PerformanceData list
        for (Performance p : invoice.getPerformances()) {
            final Play play = plays.get(p.getPlayID());
            this.performances.add(new PerformanceData(p, play));
        }
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<PerformanceData> getPerformances() {
        return performances;
    }

    public void setPerformances(List<PerformanceData> performances) {
        this.performances = performances;
    }

    /**
     * Returns an int of total amount.
     *
     * @return the formatted statement
     */
    public int totalAmount() {
        int total = 0;
        for (PerformanceData p : performances) {
            total += p.amountFor();
        }
        return total;
    }

    /**
     * Returns an int of volume credit.
     *
     * @return the formatted statement
     */
    public int volumeCredits() {
        int volumeCredits = 0;
        for (PerformanceData performanceData : getPerformances()) {
            volumeCredits += Math.max(performanceData.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
            if ("comedy".equals(performanceData.getType())) {
                volumeCredits += performanceData.getAudience() / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
            }
        }
        return volumeCredits;
    }
}
