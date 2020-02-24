package domain;

public class ManualCount {
    public static final int NEGATIVE_CRITERIA_POINT = 0;
    private int manualCount;

    public ManualCount(String manualCount, int lottoCount) {
        checkNotNumber(manualCount);
        this.manualCount = Integer.parseInt(manualCount);
        checkManualAvailableRange(lottoCount);
    }

    private void checkManualAvailableRange(int lottoCount) {
        if (isManualAvailableRange(lottoCount)) {
            throw new IllegalArgumentException(String.format("수동으로 구매 가능한 로또 개수가 아닙니다. 현재 입력 : %s ", manualCount));
        }
    }

    private boolean isManualAvailableRange(int lottoCount) {
        return manualCount < NEGATIVE_CRITERIA_POINT || manualCount > lottoCount;
    }

    private void checkNotNumber(final String number) {
        try {
            Integer.parseInt(number);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("문자는 입력될 수 없습니다. 현재 입력 : %s ", number));
        }
    }

    public int getManualCount() {
        return manualCount;
    }
}
