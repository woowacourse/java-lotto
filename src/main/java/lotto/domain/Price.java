package lotto.domain;

import lotto.PriceValidException;

public class Price {
    private final int price;
    private final int lottoPerPrice = 1000;

    public Price(final int price) {
        if (price < lottoPerPrice) {
            throw new PriceValidException("금액이 너무 적습니다.");
        }
        this.price = price;
    }

    public int getCountOfLotto() {
        return price / lottoPerPrice;
    }

    public int getPrice() {
        return price;
    }
}
