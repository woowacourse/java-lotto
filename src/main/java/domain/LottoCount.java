package domain;

import java.util.Objects;

public class LottoCount {
    private static final int NEGATIVE_CRITERIA_POINT = 0;
    private int manualCount;
    private int autoCount;

    public LottoCount(final int lottoCount, final String inputManualCount) {
        Objects.requireNonNull(inputManualCount);
        checkNotNumber(inputManualCount);
        this.manualCount = Integer.parseInt(inputManualCount);
        checkManualAvailableRange(lottoCount);
        this.autoCount = lottoCount - manualCount;
    }

    private void checkManualAvailableRange(final int lottoCount) {
        if (isManualAvailableRange(lottoCount)) {
            throw new IllegalArgumentException(String.format("수동으로 구매 가능한 로또 개수가 아닙니다. 현재 입력 : %s ", manualCount));
        }
    }

    private boolean isManualAvailableRange(final int lottoCount) {
        return manualCount < NEGATIVE_CRITERIA_POINT || manualCount > lottoCount;
    }

    private void checkNotNumber(final String number) {
        Objects.requireNonNull(number);
        try {
            Integer.parseInt(number);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("문자는 입력될 수 없습니다. 현재 입력 : %s ", number));
        }
    }

    public int getAutoCount() {
        return autoCount;
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getTotalCount() {
        return autoCount + manualCount;
    }
}
