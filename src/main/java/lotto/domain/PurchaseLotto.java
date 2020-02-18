package lotto.domain;

public class PurchaseLotto {
    public static final int LOTTO_PRICE = 1000;

    public static int purchase(int money) {
        return money / LOTTO_PRICE;
    }
}
