package lotto.domain;

public class Money {

    private static final int PRICE_CRITERION = 1000;

    private final int price;

    public Money(int price) {
        validateCriterion(price);
        this.price = price;
    }

    private void validateCriterion(int price) {
        if (price < PRICE_CRITERION) {
            throw new IllegalArgumentException();
        }
    }

    public int calculate() {
        return this.price / PRICE_CRITERION;
    }

    public int getPrice() {
        return this.price;
    }
}
