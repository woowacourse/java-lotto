package domain;

public enum LottoRule {
    MIN_LOTTO_NUMBER(1),
    MAX_LOTTO_NUMBER(45),
    LOTTO_SELECTION_SIZE(6),
    LOTTO_PRICE(1000)
    ;

    LottoRule(int value) {
        this.value = value;
    }

    private final int value;

    public int getValue() {
        return value;
    }
}
