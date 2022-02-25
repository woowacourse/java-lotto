package lotto.domain;

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
}
