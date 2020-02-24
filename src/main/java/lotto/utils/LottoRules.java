package lotto.utils;

public enum LottoRules {
    PAYMENT_UNIT(1000),
    LOTTO_NUMBER_LENGTH(6),
    MINIMUM_PAYMENT(1000),
    MAXIMUM_PAYMENT(100000),
    WINNING_COUNT(1);

    private final int number;

    LottoRules(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
