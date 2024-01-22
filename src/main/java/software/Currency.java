package software;

public record Currency(String symbol, String name) {
    @Override
    public String toString() {
        return symbol + " : " + name ;
    }
}
