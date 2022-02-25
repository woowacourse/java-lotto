package domain;

import java.util.Arrays;

public enum RankPrice {

    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000);

    private static final String ERROR_INVALID_WIN_COUNT_MESSAGE = "일치하는 값이 없습니다.";

    private final int matchedCount;
    private final boolean isBonusMatched;
    private final int winPrice;

    RankPrice(final int matchedCount, final boolean isBonusMatched, final int winPrice) {
        this.matchedCount = matchedCount;
        this.isBonusMatched = isBonusMatched;
        this.winPrice = winPrice;
    }

    public static RankPrice findByCount(final int matchedCount, final boolean isBonusMatched) {
        return Arrays.stream(RankPrice.values())
            .filter(winPrice -> winPrice.isSameWith(matchedCount, isBonusMatched))
            .findFirst()
            .orElseThrow(() -> new RuntimeException(ERROR_INVALID_WIN_COUNT_MESSAGE));
    }

    private boolean isSameWith(final int matchedCount, final boolean isBonusMatched) {
        return this.matchedCount == matchedCount && this.isBonusMatched == isBonusMatched;
    }

    public int getCount() {
        return matchedCount;
    }

    public int getPrice() {
        return winPrice;
    }
}
