package lotto.domain;

import lotto.Exception.IllegalPriceException;

public class Price {
    private static final int PRICE_OFFSET = 1000;
    private final int price;

    public Price(int price) {
        if (price % PRICE_OFFSET != 0 || price < 0) {
            throw new IllegalPriceException();
        }

        this.price = price;
    }

    public int getSize(){
        return this.price / PRICE_OFFSET;
    }

}
