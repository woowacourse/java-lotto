package lotto.domain;

import java.util.Arrays;

public enum Rank {
    NO_REWARD(0, false, 0),
    FIFTH(3, false, 5000),
    FOURTH(4, false, 50_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FIRST(6, false, 2_000_000_000);

    private final int correctCount;
    private final boolean requireBonusCheck;

    private final long prize;

    Rank(int correctCount, boolean requireBonusCheck, long prize) {
        this.correctCount = correctCount;
        this.requireBonusCheck = requireBonusCheck;
        this.prize = prize;
    }

    public static Rank find(int correctCount, boolean requireBonusCheck) {
        return Arrays.stream(values())
                .filter(rank -> rank.filter(correctCount, requireBonusCheck))
                .findFirst()
                .orElse(NO_REWARD);
    }

    private boolean filter(int correctCount, boolean hasBonusNumber) {
        if (this.requireBonusCheck) {
            return correctCount == this.correctCount && hasBonusNumber;
        }

        return correctCount == this.correctCount;
    }


    public long getPrize() {
        return prize;
    }

}
