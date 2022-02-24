package domain;

import java.util.Arrays;

public enum RankPrice {

    FIRST(6, 2000000000),
    SECOND(7, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000);

    private static final String ERROR_INVALID_WIN_COUNT_MESSAGE = "일치하는 값이 없습니다.";

    private final int winCount;
    private final int winPrice;

    RankPrice(final int winCount, final int winPrice) {
        this.winCount = winCount;
        this.winPrice = winPrice;
    }

    public static RankPrice findByCount(final int winCount) {
        return Arrays.stream(RankPrice.values())
                .filter(winPrice -> winPrice.winCount == winCount)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(ERROR_INVALID_WIN_COUNT_MESSAGE));
    }

    public int getCount() {
        return winCount;
    }

    public int getPrice() {
        return winPrice;
    }
}
