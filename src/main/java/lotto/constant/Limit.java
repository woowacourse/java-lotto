package lotto.constant;

public enum Limit {
    LOTTO_UNIT_PRICE(1_000),
    MAX_PURCHASE_AMOUNT(100_000),
    MIN_LOTTO_NUMBER(1),
    MAX_LOTTO_NUMBER(45),
    LOTTO_SIZE(6)
    ;

    private final int value;

    Limit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
