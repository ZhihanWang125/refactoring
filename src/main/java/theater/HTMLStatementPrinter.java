package theater;

import java.util.Map;

/**
 * Generates an HTML-formatted statement.
 */
public class HTMLStatementPrinter extends StatementPrinter {
    private StatementData statementData;
    /**
     * Constructs an HTMLStatementPrinter using invoice and plays data.
     *
     * @param invoice the invoice to format
     * @param plays   mapping of play IDs to play data
     */

    public HTMLStatementPrinter(Invoice invoice, Map<String, Play> plays) {
        super(invoice, plays);
        statementData = super.getStatementData();
    }

    /**
     * Returns the HTML-formatted statement.
     *
     * @return HTML statement as String
     */
    @Override
    public String statement() {
        final StringBuilder result = new StringBuilder(
                String.format("<h1>Statement for %s</h1>%n", statementData.getCustomer())
        );

        result.append("<table>").append(System.lineSeparator());
        result.append(
                String.format(" <caption>Statement for %s</caption>%n", statementData.getCustomer())
        );
        result.append(" <tr><th>play</th><th>seats</th><th>cost</th></tr>")
                .append(System.lineSeparator());

        for (PerformanceData perfData : statementData.getPerformances()) {
            result.append(String.format(
                    " <tr><td>%s</td><td>%d</td><td>%s</td></tr>%n",
                    perfData.getPlay().getName(),
                    perfData.getAudience(),
                    usd(perfData.getAmount())
            ));
        }

        result.append("</table>").append(System.lineSeparator());

        result.append(String.format(
                "<p>Amount owed is <em>%s</em></p>%n",
                usd(statementData.totalAmount())
        ));

        result.append(String.format(
                "<p>You earned <em>%s</em> credits</p>%n",
                statementData.volumeCredits()
        ));

        return result.toString();
    }
}
