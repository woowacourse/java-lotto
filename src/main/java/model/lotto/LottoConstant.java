package model.lotto;

public enum LottoConstant {
    MIN_LOTTO_NUMBER(1),
    MAX_LOTTO_NUMBER(45),
    LOTTO_LENGTH(6);

    private final int value;

    LottoConstant(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
