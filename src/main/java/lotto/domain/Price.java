package lotto.domain;

import lotto.exception.PriceValidException;

public class Price {
    private static final int PRICE_PER_LOTTO = 1000;

    private final int price;

    public Price(final int price) {
        if (price < PRICE_PER_LOTTO) {
            throw new PriceValidException("금액이 너무 적습니다.");
        }
        this.price = price;
    }

    public int getCountOfLotto() {
        return price / PRICE_PER_LOTTO;
    }

    public int getPrice() {
        return price;
    }
}
