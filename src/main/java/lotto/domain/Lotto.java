package lotto.domain;

public class Lotto {
    private final int PRICE = 1_000;

    int convertMoneyToLottosSize(int money) {
        return money / PRICE;
    }
}
