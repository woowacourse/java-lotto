package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    FIRST(2000000000, 6, false),
    SECOND(30000000, 5, true),
    THIRD(1500000, 5, false),
    FOURTH(50000, 4, false),
    FIFTH(5000, 3, false),
    NO_PRIZE(0, 0, false);

    private final int prizeMoney;
    private final int matchCount;
    private final boolean mustHaveBonus;

    Rank(int prizeMoney, int matchCount, boolean mustHaveBonus) {
        this.prizeMoney = prizeMoney;
        this.matchCount = matchCount;
        this.mustHaveBonus = mustHaveBonus;
    }

    public static Rank of(int matchCount, boolean isBonus) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.isMatch(matchCount, isBonus))
            .findFirst()
            .orElse(NO_PRIZE);
    }

    private boolean isMatch(int matchCount, boolean isBonus) {
        return this.matchCount == matchCount && this.mustHaveBonus == isBonus;
    }

    public static List<Rank> getWithoutDefault() {
        return Arrays.stream(Rank.values())
            .filter(rank -> !rank.equals(NO_PRIZE))
            .sorted(Collections.reverseOrder())
            .collect(Collectors.toList());
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
