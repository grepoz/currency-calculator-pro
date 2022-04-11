import javafx.util.Pair;

import java.math.BigDecimal;
import java.util.Scanner;

public class ExchangeOffice {

    private final ExchangeRateTable exchangeRateTable;
    private final Scanner scanner;

    public ExchangeOffice(ExchangeRateTable exchangeRateTable) {
        this.exchangeRateTable = exchangeRateTable;
        scanner = new Scanner(System.in);
    }

    public void start(){

        System.out.println("Welcome to best exchange office!");

        while(checkIfShouldClose()) {

            Pair<BigDecimal, BigDecimal> pair = getUserRequest();
            if (pair == null) continue;

            BigDecimal convertedAmount = Calculator.exchange(pair.getKey(), pair.getValue());
            displayExchangeResult(convertedAmount);
        }
    }

    private Pair<BigDecimal, BigDecimal> getUserRequest() {

        BigDecimal amount = convertUserStrToBigDecimal(getFromUser("amount"));
        if (amount == null) return null;

        BigDecimal rate = getRateForUserCurrency(getFromUser("currency"));
        if (rate == null) return null;

        return new Pair<>(amount, rate);
    }

    public boolean checkIfShouldClose() {
        System.out.println("Press enter to exchange (type 'q' to quit)");
        String cmd = scanner.nextLine();
        if (cmd.equals("q")) System.out.println("Exchange office is closing...");

        return !cmd.equals("q");
    }

    private String getFromUser(String data) {
        System.out.printf("Enter %s: \n", data);
        return scanner.nextLine();
    }

    private BigDecimal getRateForUserCurrency(String currency) {

        BigDecimal rate = exchangeRateTable.getRateForCurrency(currency);
        if (rate == null){
            System.out.println("Unsupported currency!");
        }

        return  rate;
    }

    private BigDecimal convertUserStrToBigDecimal(String amountStr) {

        BigDecimal amount = Calculator.convertStrToBigDecimal(amountStr);
        if (amount == null) {
            System.out.println("Provide valid amount!");
            return null;
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Provide amount greater than 0!");
            return null;
        }

        return amount;
    }

    private void displayExchangeResult(BigDecimal convertedAmount) {

        if (convertedAmount != null) {
            System.out.println("Money after exchange: " + convertedAmount);
        } else {
            System.out.println("Unable to convert! Please try again..");
        }
    }
}
