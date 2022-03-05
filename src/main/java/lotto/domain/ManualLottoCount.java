package lotto.domain;

public class ManualLottoCount {
    private static final String LOTTO_COUNT_ERROR = "구매 개수는 최소 0개, 최대 %s개 입니다.";
    private static final int MIN_NATURAL_NUMBER = 0;

    private final int value;

    public ManualLottoCount(int value, int max) {
        validation(value, max);
        this.value = value;
    }

    private void validation(int value, int max) {
        if (value < MIN_NATURAL_NUMBER || value > max) {
            throw new IllegalArgumentException(String.format(LOTTO_COUNT_ERROR, max));
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ManualLottoCount)) {
            return false;
        }

        ManualLottoCount that = (ManualLottoCount) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
