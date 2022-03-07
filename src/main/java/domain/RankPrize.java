package domain;

import java.util.Arrays;

public enum RankPrize {

    FIRST(6, 2000000000, false),
    SECOND(5, 30000000, true),
    THIRD(5, 1500000, false),
    FOURTH(4, 50000, false),
    FIFTH(3, 5000, false);

    private static final String ERROR_INVALID_WIN_COUNT_MESSAGE = "일치하는 값이 없습니다.";

    private final int correctNumber;
    private final int winPrize;
    private final boolean secondRank;

    RankPrize(final int correctNumber, final int winPrize, final boolean secondRank) {
        this.correctNumber = correctNumber;
        this.winPrize = winPrize;
        this.secondRank = secondRank;
    }

    public int getCount() {
        return correctNumber;
    }

    public int getPrize() {
        return winPrize;
    }

    public static RankPrize findByCount(final int correctNumber, final boolean secondRank) {
        return Arrays.stream(RankPrize.values())
                .filter(winPrize -> winPrize.correctNumber == correctNumber)
                .filter(winPrize -> winPrize.secondRank == secondRank)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(ERROR_INVALID_WIN_COUNT_MESSAGE));
    }

    public static boolean isSecondRank(final int count, final boolean bonus) {
        return (count == SECOND.correctNumber) && bonus;
    }

    public static boolean isInRank(final int count) {
        return count >= FIFTH.correctNumber;
    }

    public int calculatePrize(Integer count) {
        return this.winPrize * count;
    }
}
