import java.math.BigDecimal;
import java.util.Map;

public class ExchangeRateTable {

    private final Map<String, BigDecimal> exchangeMap;

    public ExchangeRateTable(Map<String, BigDecimal> exchangeMap) {
        this.exchangeMap = exchangeMap;
    }

    public boolean isExchangeMapCorrect() {

        final int currencyRequiredLength = 3;

        for (Map.Entry<String, BigDecimal> entry : exchangeMap.entrySet()) {
            if (entry.getKey().length() != currencyRequiredLength ||
                    !containsOnlyUpperCaseCharacter(entry.getKey()) ||
                    entry.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("Exchange rate table incorrect!");
                return false;
            }
        }

        return true;
    }

    private boolean containsOnlyUpperCaseCharacter(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (Character.isUpperCase(string.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public BigDecimal getRateForCurrency(String currency) {
        return exchangeMap.get(currency);
    }
}
