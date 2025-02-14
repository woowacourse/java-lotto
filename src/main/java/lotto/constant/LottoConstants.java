package lotto.constant;

public enum LottoConstants {

    LENGTH(6),
    LOTTO_MINIMUM_NUMBER(1),
    LOTTO_MAXIMUM_NUMBER(45),
    ZERO(0);

    private final int number;

    LottoConstants(final int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
