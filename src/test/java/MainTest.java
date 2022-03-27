import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.verifyZeroInteractions;

class MainTest {

    Map<String, BigDecimal> exchangeMap;

    @Test
    void mainExchangeMapIsNull() {

        exchangeMap = null;
        ExchangeOffice exchangeOffice = mock(ExchangeOffice.class);
        verifyZeroInteractions(exchangeOffice);
    }

    @Test
    void mainExchangeMapIsIncorrect() {

        exchangeMap = new HashMap<>();
        exchangeMap.put("US", new BigDecimal("1.1"));
        ExchangeRateTable exchangeRateTable = new ExchangeRateTable(exchangeMap);
        Assertions.assertFalse(exchangeRateTable.isExchangeMapCorrect());
        ExchangeOffice exchangeOffice = mock(ExchangeOffice.class);
        verifyZeroInteractions(exchangeOffice);
    }
}