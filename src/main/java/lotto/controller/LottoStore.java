package lotto.controller;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;

    public LottoStore() {
    }

    public int calculateAffordableLottoPieces(int money) {
        return money / LOTTO_PRICE;
    }
}
