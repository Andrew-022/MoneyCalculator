package software;

import java.time.LocalDate;

public record ExchangeRate(Currency from, Currency to, double rate)
{
    @Override
    public String toString() {
        return "ExchangeRate{" +
                "from=" + from.symbol() +
                ", to=" + to.symbol()+
                ", rate=" + rate +
                '}';
    }
}
