package lotto.domain;

public class Money {

    private static final String PRICE_CRITERION_ERROR_MESSAGE = "로또 1장은 가격은 1000원 이상입니다.";
    private static final int PRICE_CRITERION = 1000;

    private final int price;

    public Money(int price) {
        validateCriterion(price);
        this.price = price;
    }

    private void validateCriterion(int price) {
        if (price < PRICE_CRITERION) {
            throw new IllegalArgumentException(PRICE_CRITERION_ERROR_MESSAGE);
        }
    }

    public int calculate() {
        return this.price / PRICE_CRITERION;
    }

    public int getPrice() {
        return this.price;
    }
}
