package domain;

public enum LottoRule {
    MIN_LOTTO_NUMBER(1),
    MAX_LOTTO_NUMBER(45),
    LOTTO_SELECTION_SIZE(6),
    LOTTO_PRICE(1000);

    LottoRule(int value) {
        this.value = value;
    }

    private final int value;

    public int getValue() {
        return value;
    }

    static {
        validateRange();
        validateCount();
    }

    private static void validateRange() {
        if (MIN_LOTTO_NUMBER.value > MAX_LOTTO_NUMBER.value) {
            throw new IllegalArgumentException(
                    "최소 값(" + MIN_LOTTO_NUMBER.value + ")이 최대 값(" + MAX_LOTTO_NUMBER.value + ")보다 클 수 없습니다."
            );
        }
    }

    private static void validateCount() {
        if ((MAX_LOTTO_NUMBER.value - MIN_LOTTO_NUMBER.value + 1) < LOTTO_SELECTION_SIZE.value) {
            throw new IllegalArgumentException(
                    "생성하려는 개수(" + LOTTO_SELECTION_SIZE.value + ")가 " +
                            "지정된 범위 [" + MIN_LOTTO_NUMBER.value + " ~ " + MAX_LOTTO_NUMBER.value + "]보다 큽니다."
            );
        }
    }
}
