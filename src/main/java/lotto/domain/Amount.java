package lotto.domain;

public class Amount {

    private static final int PRICE_CRITERION = 1000;

    private final int price;

    private Amount(int price) {
        validateCriterion(price);
        this.price = price;
    }

    private void validateCriterion(int price) {
        if (price < PRICE_CRITERION) {
            throw new IllegalArgumentException();
        }
    }

    public static Amount create(String value) {
        int price = translateInteger(value);

        return new Amount(price);
    }

    private static int translateInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public int calculate() {
        return this.price / PRICE_CRITERION;
    }
}
