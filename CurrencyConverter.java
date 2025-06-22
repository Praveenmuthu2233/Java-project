import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {

    private static Map<String, Double> exchangeRates = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeExchangeRates();

        displayCurrencies();
        String sourceCurrency = getCurrencyFromUser(scanner, "Enter the source currency: ");
        String targetCurrency = getCurrencyFromUser(scanner, "Enter the target currency: ");

        if (!isValidCurrency(sourceCurrency) || !isValidCurrency(targetCurrency)) {
            System.out.println("Invalid currency. Exiting the program.");
            return;
        }

        double amount = getAmountFromUser(scanner);

        double convertedAmount = convertCurrency(sourceCurrency, targetCurrency, amount);

        System.out.println("Conversion result: " + amount + " " + sourceCurrency + " = " + convertedAmount + " " + targetCurrency);
    }

    private static void initializeExchangeRates() {
        exchangeRates.put("USD", 1.0);  
        exchangeRates.put("EUR", 0.91);  
        exchangeRates.put("GBP", 0.82);  
        exchangeRates.put("INR", 82.5);  
        exchangeRates.put("JPY", 133.5); 
        exchangeRates.put("AUD", 1.5); 
        exchangeRates.put("CAD", 1.36);  
    }

    private static void displayCurrencies() {
        System.out.println("Available currencies: ");
        for (String currency : exchangeRates.keySet()) {
            System.out.println(currency);
        }
    }

    private static String getCurrencyFromUser(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().toUpperCase();
    }

    private static boolean isValidCurrency(String currency) {
        return exchangeRates.containsKey(currency);
    }

    private static double getAmountFromUser(Scanner scanner) {
        double amount = -1;
        while (amount <= 0) {
            System.out.print("Enter the amount to convert: ");
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount <= 0) {
                    System.out.println("Amount should be greater than zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }
        return amount;
    }

    private static double convertCurrency(String sourceCurrency, String targetCurrency, double amount) {
        if (sourceCurrency.equals(targetCurrency)) {
            return amount; 
        }

        double sourceRate = exchangeRates.get(sourceCurrency);
        double targetRate = exchangeRates.get(targetCurrency);

        return amount * (targetRate / sourceRate);
    }
}
