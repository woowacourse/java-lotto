package lotto.domain;

import java.util.Arrays;

public enum RankType {
    FIFTH(3, 5000),
    FOURTH(4, 50000),
    THIRD(5, 1500000),
    SECOND(5, 30000000),
    FIRST(6, 2000000000),
    NOTHING(0, 0);

    private final int matchingCount;
    private final int prize;

    RankType(final int matchingCount, final int prize) {
        this.matchingCount = matchingCount;
        this.prize = prize;
    }

    public static RankType valueOf(int matchingCount, boolean bonusBall) {
        if (matchingCount == 5 && bonusBall) {
            return SECOND;
        }

        if (matchingCount >= 5) {
            return Arrays.stream(values())
                    .filter(rankType -> rankType.matchingCount == matchingCount)
                    .findFirst().get();
        }

        return NOTHING;
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public int getPrize() {
        return this.prize;
    }
}
