package software.fixerwebservice;

import software.Currency;
import software.ExchangeRateLoader;

public class FixerMain {
    public static void main(String[] args) {
        /*
        CurrencyLoader currencyLoader = new FixerCurrencyLoader();
        List<Currency> currencies = currencyLoader.load();
        for (Currency currency : currencies) {
            System.out.println(currency.toString());
        }
        */

       Currency from = new Currency("USD", "United States dollar");
       Currency to = new Currency("EUR", "Euro");
       ExchangeRateLoader exchangeRateLoader = new FixerExchangeRateLoader();
       System.out.println(exchangeRateLoader.load(from, to).toString());
    }
}
