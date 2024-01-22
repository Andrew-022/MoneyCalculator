package software;

public record Money(double amount, Currency currency ) {
    @Override
    public String toString() {
        return String.format("%.2f",amount) + " " + currency;
    }
}
