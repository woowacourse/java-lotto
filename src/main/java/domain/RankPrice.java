package domain;

import java.util.Arrays;

public enum RankPrice {

    FIRST(6, 2000000000),
    SECOND(7, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000);

    private static final String ERROR_INVALID_WIN_COUNT_MESSAGE = "일치하는 값이 없습니다.";

    private final int matchedCount;
    private final int winPrice;

    RankPrice(final int matchedCount, final int winPrice) {
        this.matchedCount = matchedCount;
        this.winPrice = winPrice;
    }

    public static RankPrice findByCount(final int matchedCount) {
        return Arrays.stream(RankPrice.values())
            .filter(winPrice -> winPrice.matchedCount == matchedCount)
            .findFirst()
            .orElseThrow(() -> new RuntimeException(ERROR_INVALID_WIN_COUNT_MESSAGE));
    }

    public int getCount() {
        return matchedCount;
    }

    public int getPrice() {
        return winPrice;
    }
}
