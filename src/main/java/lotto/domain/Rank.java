package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000);

    private final int matched;
    private final int prize;

    Rank(int matched, int prize) {
        this.matched = matched;
        this.prize = prize;
    }

    public static Rank of(int size, boolean isBonusBallMatched) {
        if (size == 5 && isBonusBallMatched) {
            return SECOND;
        }
        if (size == 5) {
            return THIRD;
        }
        return Arrays.stream(Rank.values())
                .filter(r -> r.matched == size)
                .findAny()
                .orElseThrow();
    }

    public int getMatched() {
        return matched;
    }

    public int getPrize() {
        return prize;
    }
}
