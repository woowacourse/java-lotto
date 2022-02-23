package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    FIRST(2000000000, 6),
    SECOND(30000000, 5),
    THIRD(1500000, 5),
    FOURTH(50000, 4),
    FIFTH(5000, 3),
    DEFAULT(0, 0);

    private final int prizeMoney;
    private final int matchCount;

    Rank(int prizeMoney, int matchCount) {
        this.prizeMoney = prizeMoney;
        this.matchCount = matchCount;
    }

    public static Rank of(int matchCount, boolean isBonus) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isMatch(matchCount))
                .filter(rank -> !rank.equals(SECOND) || isBonus)
                .findFirst()
                .orElse(DEFAULT);
    }

    private boolean isMatch(int matchCount) {
        return this.matchCount == matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
