package domain;

import java.util.Objects;

public class ManualCount {
    private static final int NEGATIVE_CRITERIA_POINT = 0;
    private static int manualCount;

    public static void inputManualCount(final String count, final int lottoCount) {
        Objects.requireNonNull(count);
        checkNotNumber(count);
        manualCount = Integer.parseInt(count);
        checkManualAvailableRange(lottoCount);
    }

    private static void checkManualAvailableRange(final int lottoCount) {
        if (isManualAvailableRange(lottoCount)) {
            throw new IllegalArgumentException(String.format("수동으로 구매 가능한 로또 개수가 아닙니다. 현재 입력 : %s ", manualCount));
        }
    }

    private static boolean isManualAvailableRange(final int lottoCount) {
        return manualCount < NEGATIVE_CRITERIA_POINT || manualCount > lottoCount;
    }

    private static void checkNotNumber(final String number) {
        Objects.requireNonNull(number);
        try {
            Integer.parseInt(number);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("문자는 입력될 수 없습니다. 현재 입력 : %s ", number));
        }
    }

    public static int getManualCount() {
        return manualCount;
    }
}
