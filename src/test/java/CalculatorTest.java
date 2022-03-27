import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class CalculatorTest {

    @Test
    void exchangeSmallDecimalsCorrectOutput(){

        BigDecimal bigDecimal1 = new BigDecimal("1.1");
        BigDecimal bigDecimal2 = new BigDecimal("2.2");
        BigDecimal result = new BigDecimal("2.42");
        Assertions.assertEquals(Calculator.exchange(bigDecimal1, bigDecimal2), result);
    }

    @Test
    void exchangeLargePrecisionAndLargeCorrectOutput(){

        BigDecimal bigDecimal1 = new BigDecimal("111111111111.111111111111");
        BigDecimal bigDecimal2 = new BigDecimal("222222222222.222222222222");
        BigDecimal result = new BigDecimal("24691358024691358024691.30");
        Assertions.assertEquals(Calculator.exchange(bigDecimal1, bigDecimal2), result);
    }

    @Test
    void exchangeVeryLargePrecisionAndVeryLargeCorrectOutput(){

        BigDecimal bigDecimal1 = new BigDecimal("111111111111111111111111.111111111111111111111111");
        BigDecimal bigDecimal2 = new BigDecimal("222222222222222222222222.222222222222222222222222");
        BigDecimal result = new BigDecimal("24691358024691358024691358024691358024691358024.64");
        Assertions.assertEquals(Calculator.exchange(bigDecimal1, bigDecimal2), result);
    }

    // tests - incorrect
    @Test
    void exchangeSmallDecimalsIncorrectOutput(){

        BigDecimal bigDecimal1 = new BigDecimal("1.1");
        BigDecimal bigDecimal2 = new BigDecimal("2.2");
        Assertions.assertNotEquals(Calculator.exchange(bigDecimal1, bigDecimal2), new BigDecimal("2.0"));
    }

    @Test
    void exchangeLittlePrecisionAndVeryLargeIncorrectOutput(){

        BigDecimal bigDecimal1 = new BigDecimal("111111111111111111111111.1");
        BigDecimal bigDecimal2 = new BigDecimal("222222222222222222222222.2");
        Assertions.assertNotEquals(Calculator.exchange(bigDecimal1, bigDecimal2), new BigDecimal("2.0"));
    }

    @Test
    void exchangeNullArgumentReturnsNull(){

        BigDecimal bigDecimal = new BigDecimal("1.1");
        Assertions.assertNull(Calculator.exchange(bigDecimal, null));
    }

    @Test
    void exchangeZeroArgumentReturnsZero(){

        BigDecimal bigDecimal1 = new BigDecimal("1.1");
        BigDecimal bigDecimal2 = new BigDecimal("0.0");
        Assertions.assertEquals(Calculator.exchange(bigDecimal1, bigDecimal2), new BigDecimal("0.00"));
    }

    @Test
    void convertStrToBigDecimalReturnsProperResult() {

        String amountStr = "1.1";
        Assertions.assertEquals(Calculator.convertStrToBigDecimal(amountStr), new BigDecimal(amountStr));
    }

    @Test
    void convertStrToBigDecimalCharacterReturnsNull() {

        String amountStr = "-";
        Assertions.assertNull(Calculator.convertStrToBigDecimal(amountStr));
    }

    @Test
    void convertStrToBigDecimalEmptyStrReturnsNull() {

        String amountStr = "";
        Assertions.assertNull(Calculator.convertStrToBigDecimal(amountStr));
    }
}