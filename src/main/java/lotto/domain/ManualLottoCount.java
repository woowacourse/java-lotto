package lotto.domain;

public class ManualLottoCount {
    private static final String ERROR_EXCEED_UPPER_BOUND = "최대 구매 개수를 초과했습니다.";

    private final int value;

    private ManualLottoCount(int count) {
        value = count;
    }

    public static void from(int inputCount, int upperBound) {
        if (upperBound < inputCount) {
            throw new IllegalArgumentException(ERROR_EXCEED_UPPER_BOUND);
        }
    }
}
