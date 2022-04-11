import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    public static BigDecimal exchange(BigDecimal amount, BigDecimal rate) {
        BigDecimal convertedAmount;

        convertedAmount = amount.multiply(rate);
        convertedAmount = roundAmount(convertedAmount);

        return convertedAmount;
    }

    public static BigDecimal roundAmount(BigDecimal amount) {

        try {
            if(amount != null) {
                amount = amount.setScale(2, RoundingMode.FLOOR);
            }
        } catch (Exception e) {
            // in case of changing 'RoundingMode'
            System.out.println("Unable to round!");
        }

        return amount;
    }

    public static BigDecimal convertStrToBigDecimal(String amountStr) {

        BigDecimal amount;
        try {
            amount = new BigDecimal(amountStr);
        }
        catch (NumberFormatException e) {
            return null;
        }

        return amount;
    }
}
