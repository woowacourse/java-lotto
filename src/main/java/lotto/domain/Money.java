package lotto.domain;

public class Money {

    private static final int LOTTO_PRICE = 1000;
    private static final String PRICE_ERROR_MESSAGE = "로또 1장은 가격은 1000원 이상입니다.";

    private final int price;

    public Money(int price) {
        validatePurchaseCriterion(price);
        this.price = price;
    }

    private void validatePurchaseCriterion(int price) {
        if (price < LOTTO_PRICE) {
            throw new IllegalArgumentException(PRICE_ERROR_MESSAGE);
        }
    }

    public int calculateTicketCount() {
        return price / LOTTO_PRICE;
    }

    public int getPrice() {
        return price;
    }
}
