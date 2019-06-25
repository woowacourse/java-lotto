package lotto.domain;

import java.util.Arrays;

public enum RankType {
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000),
    NOTHING(0, 0);

    private static final int FIFTH_MATCHING_COUNT = 3;
    private static final int SECOND_OR_THIRD_MATCHING_COUNT = 5;

    private final int matchingCount;
    private final int prize;

    RankType(final int matchingCount, final int prize) {
        this.matchingCount = matchingCount;
        this.prize = prize;
    }

    public static RankType valueOf(int matchingCount, boolean bonusBall) {
        if (matchingCount == SECOND_OR_THIRD_MATCHING_COUNT && bonusBall) {
            return SECOND;
        }

        if (matchingCount >= FIFTH_MATCHING_COUNT) {
            return Arrays.stream(values())
                    .filter(rankType -> rankType.matchingCount == matchingCount)
                    .findFirst().get();
        }

        return NOTHING;
    }

    public int getMatchingCount() {
        return this.matchingCount;
    }

    public int getPrize() {
        return this.prize;
    }
}
