package lotto.domain;

import lotto.PriceValidException;

public class Price {
    private int price;

    public Price(int price) {
        if (price < 1000) {
            throw new PriceValidException("금액이 너무 적습니다.");
        }
        this.price = price;
    }

    public int getCountOfLotto() {
        return price / 1000;
    }
}
