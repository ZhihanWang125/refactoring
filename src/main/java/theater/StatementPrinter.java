package theater;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * This class generates a statement for a given invoice of performances.
 */
public class StatementPrinter {
    private final StatementData statementData;

    public StatementPrinter(Invoice invoice, Map<String, Play> plays) {
        this.statementData = new StatementData(invoice, plays);
    }

    /**
     * Returns a formatted statement of the invoice associated with this printer.
     *
     * @return the formatted statement
     * @throws RuntimeException if one of the play types is not known
     */
    public String statement() {
        return renderPlainText();
    }

    private String renderPlainText() {
        final StringBuilder result = new StringBuilder("Statement for "
                + statementData.getCustomer()
                + System.lineSeparator());
        final int totalAmount = getTotalAmount();
        final int volumeCredits = getTotalVolumeCredits();

        for (PerformanceData performanceData : statementData.getPerformances()) {
            final Play play = performanceData.getPlay();
            final int thisAmount = performanceData.amountFor();
            result.append(String.format("  %s: %s (%s seats)%n",
                    play.getName(), usd(thisAmount), performanceData.getAudience()));
        }
        result.append(String.format("Amount owed is %s%n", usd(totalAmount)));
        result.append(String.format("You earned %s credits%n", volumeCredits));
        return result.toString();
    }

    private int getTotalAmount() {
        int totalAmount = 0;
        for (PerformanceData performanceData : statementData.getPerformances()) {
            totalAmount += performanceData.amountFor();
        }
        return totalAmount;
    }

    private int getTotalVolumeCredits() {
        int volumeCredits = 0;
        for (PerformanceData performanceData : statementData.getPerformances()) {
            volumeCredits = getVolumeCredits(performanceData, volumeCredits);
        }
        return volumeCredits;
    }

    private static int getVolumeCredits(PerformanceData performanceData, int volumeCredits) {
        int result = 0;
        result += Math.max(performanceData.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
        // add extra credit for every five comedy attendees
        if ("comedy".equals(performanceData.getType())) {
            result += performanceData.getAudience() / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        }
        return volumeCredits + result;
    }

    private static String usd(int totalAmount) {
        final NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        return frmt.format(totalAmount / Constants.PERCENT_FACTOR);
    }
}
