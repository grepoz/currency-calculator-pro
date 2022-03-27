import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class ExchangeRateTableTest {

    Map<String, BigDecimal> exchangeMap;

    @BeforeEach
    void init() {
        exchangeMap = new HashMap<>();
    }

    @Test
    void isExchangeMapCorrectCorrect() {

        exchangeMap.put("USD", new BigDecimal("1.1"));

        ExchangeRateTable exchangeRateTable = new ExchangeRateTable(exchangeMap);

        Assertions.assertTrue(exchangeRateTable.isExchangeMapCorrect());
    }

    @Test
    void isExchangeMapCorrectWrongLengthOfCurrency() {

        exchangeMap.put("US", new BigDecimal("1.1"));

        ExchangeRateTable exchangeRateTable = new ExchangeRateTable(exchangeMap);

        Assertions.assertFalse(exchangeRateTable.isExchangeMapCorrect());
    }

    @Test
    void isExchangeMapCorrectCurrencyDoesnotContainOnlyUppercase() {

        exchangeMap.put("usd", new BigDecimal("1.1"));

        ExchangeRateTable exchangeRateTable = new ExchangeRateTable(exchangeMap);

        Assertions.assertFalse(exchangeRateTable.isExchangeMapCorrect());
    }

    @Test
    void isExchangeMapCorrectRateIsNegative() {

        exchangeMap.put("usd", new BigDecimal("-1.1"));

        ExchangeRateTable exchangeRateTable = new ExchangeRateTable(exchangeMap);

        Assertions.assertFalse(exchangeRateTable.isExchangeMapCorrect());
    }

    @Test
    void isExchangeMapCorrectRateIsEqualToZero() {

        exchangeMap.put("usd", new BigDecimal("0.00"));

        ExchangeRateTable exchangeRateTable = new ExchangeRateTable(exchangeMap);

        Assertions.assertFalse(exchangeRateTable.isExchangeMapCorrect());
    }
}