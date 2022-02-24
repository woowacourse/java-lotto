package domain.result;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    NONE(0, 0),
    FIFTH(5000, 3),
    FOURTH(50000, 4),
    THIRD(1500000, 5),
    SECOND(30000000, 5),
    FIRST(2000000000, 6);

    private final int prize;
    private final int matchCount;

    Rank(int prize, int matchCount) {
        this.prize = prize;
        this.matchCount = matchCount;
    }

    public static Rank of(int matchCount, boolean isBonus) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isMatch(matchCount))
                .filter(rank -> rank.equals(SECOND) || !isBonus)
                .findFirst()
                .orElse(NONE);
    }

    public static List<Rank> getWithoutDefault() {
        return Arrays.stream(Rank.values())
                .filter(rank -> !rank.equals(NONE))
                .collect(Collectors.toList());
    }

    private boolean isMatch(int matchCount) {
        return this.matchCount == matchCount;
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
