package lotto.domain;

import java.util.Arrays;

public enum Rank {

    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000);

    private static final int MATCHED_FOURTH_OR_THIRD = 5;

    private final int matched;
    private final long prize;

    Rank(final int matched, final long prize) {
        this.matched = matched;
        this.prize = prize;
    }

    public static Rank of(final int size, final boolean isBonusBallMatched) {
        if (size == MATCHED_FOURTH_OR_THIRD && isBonusBallMatched) {
            return SECOND;
        }
        if (size == MATCHED_FOURTH_OR_THIRD) {
            return THIRD;
        }
        return Arrays.stream(Rank.values())
                .filter(r -> r.matched == size)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 순위에 접근했습니다."));
    }

    public int getMatched() {
        return matched;
    }

    public long getPrize() {
        return prize;
    }
}
