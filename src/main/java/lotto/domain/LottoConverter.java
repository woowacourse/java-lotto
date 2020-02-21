package lotto.domain;

public class LottoConverter {
    private static final int PRICE = 1_000;
    static int convertMoneyToLottosSize(int money) {
        return money / PRICE;
    }
}
