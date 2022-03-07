package lotto.domain;

import java.util.Arrays;

public enum Rank {

    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0),
    ;

    private final int matched;
    private final long prize;

    Rank(final int matched, final long prize) {
        this.matched = matched;
        this.prize = prize;
    }

    public static Rank of(final int matched, final boolean bonusMatched) {
        if (matched == SECOND.matched()) {
            return getSecondOrThird(bonusMatched);
        }
        return Arrays.stream(Rank.values())
                .filter(r -> r.matched == matched)
                .findAny()
                .orElse(MISS);
    }

    private static Rank getSecondOrThird(final boolean bonusMatched) {
        if (bonusMatched) {
            return SECOND;
        }
        return THIRD;
    }

    public int matched() {
        return matched;
    }

    public long prize() {
        return prize;
    }
}
