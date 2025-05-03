import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {

    private static Map<String, Double> exchangeRates = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize exchange rates (for simplicity, assuming some common rates)
        initializeExchangeRates();

        // Display available currencies
        displayCurrencies();

        // User input for source and target currencies
        String sourceCurrency = getCurrencyFromUser(scanner, "Enter the source currency: ");
        String targetCurrency = getCurrencyFromUser(scanner, "Enter the target currency: ");

        // Check if the currencies are valid
        if (!isValidCurrency(sourceCurrency) || !isValidCurrency(targetCurrency)) {
            System.out.println("Invalid currency. Exiting the program.");
            return;
        }

        // Get the amount to convert
        double amount = getAmountFromUser(scanner);

        // Convert the currency
        double convertedAmount = convertCurrency(sourceCurrency, targetCurrency, amount);

        // Display the result
        System.out.println("Conversion result: " + amount + " " + sourceCurrency + " = " + convertedAmount + " " + targetCurrency);
    }

    // Initialize some basic exchange rates (could be updated dynamically from an API)
    private static void initializeExchangeRates() {
        exchangeRates.put("USD", 1.0);   // Base currency
        exchangeRates.put("EUR", 0.91);  // Example rate
        exchangeRates.put("GBP", 0.82);  // Example rate
        exchangeRates.put("INR", 82.5);  // Example rate
        exchangeRates.put("JPY", 133.5); // Example rate
        exchangeRates.put("AUD", 1.5);   // Example rate
        exchangeRates.put("CAD", 1.36);  // Example rate
    }

    // Display the available currencies
    private static void displayCurrencies() {
        System.out.println("Available currencies: ");
        for (String currency : exchangeRates.keySet()) {
            System.out.println(currency);
        }
    }

    // Get currency code from user
    private static String getCurrencyFromUser(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().toUpperCase();
    }

    // Check if the entered currency is valid
    private static boolean isValidCurrency(String currency) {
        return exchangeRates.containsKey(currency);
    }

    // Get amount to convert from user
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

    // Convert the source currency to the target currency
    private static double convertCurrency(String sourceCurrency, String targetCurrency, double amount) {
        if (sourceCurrency.equals(targetCurrency)) {
            return amount; // No conversion needed if both currencies are the same
        }

        double sourceRate = exchangeRates.get(sourceCurrency);
        double targetRate = exchangeRates.get(targetCurrency);

        // Convert the amount to base currency (USD), then to the target currency
        return amount * (targetRate / sourceRate);
    }
}
