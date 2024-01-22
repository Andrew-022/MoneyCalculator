package software;

public interface ExchangeRateLoader {
    ExchangeRate load(Currency from, Currency to);
}
