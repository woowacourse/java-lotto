package lotto.domain;

import java.util.Arrays;

public enum Rank {
    NO_REWARD(0, false, 0),
    FIFTH(3, false, 5000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000);

    private static final int SPECIFIC_MATCH_COUNT_FOR_BONUS_CHECK = 5;
    private final int correctCount;
    private final boolean hasBonusNumber;
    private final long prize;

    Rank(int correctCount, boolean hasBonusNumber, long prize) {
        this.correctCount = correctCount;
        this.hasBonusNumber = hasBonusNumber;
        this.prize = prize;
    }

    public static Rank find(int correctCount, boolean hasBonusNumber) {
        return Arrays.stream(values())
                .filter(rank -> rank.filter(correctCount, hasBonusNumber))
                .findFirst()
                .orElse(NO_REWARD);
    }

    private boolean filter(int correctCount, boolean hasBonusNumber) {
        if (requireBonusCheck(correctCount)) {
            return correctCount == this.correctCount && hasBonusNumber == this.hasBonusNumber;
        }

        return correctCount == this.correctCount;
    }

    private boolean requireBonusCheck(int correctCount) {
        return correctCount == SPECIFIC_MATCH_COUNT_FOR_BONUS_CHECK;
    }

    public long getPrize() {
        return prize;
    }

}
