package constant;

public enum LottoConstants {
    LOTTO_SIZE(6),
    LOTTO_PRICE(1000),
    LOTTO_RANGE_MIN(1),
    LOTTO_RANGE_MAX(45);

    private int value;

    LottoConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
