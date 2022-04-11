import java.math.BigDecimal;
import java.util.Map;

public class ExchangeRateTable {

    private final Map<String, BigDecimal> exchangeMap;

    public ExchangeRateTable(Map<String, BigDecimal> exchangeMap) {
        this.exchangeMap = exchangeMap;
    }

    public boolean isExchangeMapCorrect() {

        for (Map.Entry<String, BigDecimal> entry : exchangeMap.entrySet()) {
            if (!isCurrencyCorrect(entry.getKey()) || !isRateCorrect(entry.getValue())) {
                System.out.println("Exchange rate table incorrect!");
                return false;
            }
        }

        return true;
    }

    private boolean isCurrencyCorrect(String currency) {
        return hasRequiredLength(currency) && isUpperCase(currency);
    }

    private boolean hasRequiredLength(String currency) {
        final int currencyRequiredLength = 3;
        return currency.length() == currencyRequiredLength;
    }

    private boolean isUpperCase(String string) {
        return string.equals(string.toUpperCase());
    }

    private boolean isRateCorrect(BigDecimal rate) {
        return rate.compareTo(BigDecimal.ZERO) > 0;
    }

    public BigDecimal getRateForCurrency(String currency) {
        return exchangeMap.get(currency);
    }
}
