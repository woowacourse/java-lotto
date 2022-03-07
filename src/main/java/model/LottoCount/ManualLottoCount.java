package model.LottoCount;

public class ManualLottoCount {
    private static final String NEGATIVE_COUNT_MESSAGE = "[ERROR] 입력한 수동 구매 로또 개수가 음수입니다.";

    private final int count;

    public ManualLottoCount(final int count) {
        checkValidCount(count);
        this.count = count;
    }

    private void checkValidCount(final int count) {
        checkCountIsNegative(count);
    }

    private void checkCountIsNegative(final int count) {
        if (count < 0) {
            throw new IllegalArgumentException(NEGATIVE_COUNT_MESSAGE);
        }
    }

    public int getCount() {
        return count;
    }
}
