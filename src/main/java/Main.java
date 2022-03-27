import java.math.BigDecimal;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<String, BigDecimal> exchangeMap = Parser.parse();
        if (exchangeMap == null) {
            System.out.println("Unable to parse xml document!");
            return;
        }

        ExchangeRateTable exchangeRateTable = new ExchangeRateTable(exchangeMap);
        if (!exchangeRateTable.isExchangeMapCorrect()) {
            System.out.println("Exchange map is incorrect!");
            return;
        }

        ExchangeOffice exchangeOffice = new ExchangeOffice(exchangeRateTable);
        exchangeOffice.start();
    }
}
