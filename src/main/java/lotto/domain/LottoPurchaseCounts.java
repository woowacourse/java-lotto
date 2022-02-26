package lotto.domain;

import java.util.Objects;

public class LottoPurchaseCounts {

    private final int manualCount;
    private final int automaticCount;

    public LottoPurchaseCounts(final int manualCount, final int automaticCount) {
        checkNegativeCount(manualCount);
        checkNegativeCount(automaticCount);
        this.manualCount = manualCount;
        this.automaticCount = automaticCount;
    }

    private void checkNegativeCount(final int count) {
        if (count < 0) {
            throw new IllegalArgumentException("[ERROR] 구매 갯수는 음수가 들어올 수 없습니다.");
        }
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutomaticCount() {
        return automaticCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoPurchaseCounts that = (LottoPurchaseCounts) o;
        return manualCount == that.manualCount && automaticCount == that.automaticCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manualCount, automaticCount);
    }
}
