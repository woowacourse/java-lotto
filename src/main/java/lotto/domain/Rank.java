package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5000),
    NO_REWARD(0, false, 0);

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
        if (correctCount == 5) {
            return correctCount == this.correctCount && hasBonusNumber == this.hasBonusNumber;
        }

        return correctCount == this.correctCount;
    }

    public long getPrize() {
        return prize;
    }
}
