package lotto.constant;

public enum LottoNumberConstants {

    LOTTO_NUMBER_MIN(1),
    LOTTO_NUMBER_MAX(45),
    LOTTO_NUMBER_COUNT(6),
    ;

    private final int value;

    LottoNumberConstants(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
