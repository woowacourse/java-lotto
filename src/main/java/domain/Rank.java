package domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum Rank {
    NONE(0L, 0, false),
    FIFTH(5000L, 3, false),
    FOURTH(50000L, 4, false),
    THIRD(1500000L, 5, false),
    SECOND(30000000L, 5, true),
    FIRST(2000000000L, 6, false);

    private final Long prize;
    private final int count;
    private final boolean bonusMatch;

    Rank(Long prize, int count, boolean bonusMatch) {
        this.prize = prize;
        this.count = count;
        this.bonusMatch = bonusMatch;
    }

    public static Rank fromResult(int matchCount, boolean contains) {
        List<Rank> ranks = Arrays.stream(Rank.values())
                .sorted(Comparator.comparingLong(Rank::getPrize)
                        .reversed())
                .toList();
        return ranks.stream()
                .filter(rank -> rank.matchesRank(matchCount, contains))
                .findFirst()
                .orElse(NONE);
    }

    private boolean matchesRank(int matchCount, boolean contains) {
        return this.hasCountMatch(matchCount) && (this.hasNoBonusMatch() || contains);
    }

    public Long getPrize() {
        return prize;
    }

    public int getCount() {
        return count;
    }

    public boolean isSecond() {
        return this == Rank.SECOND;
    }

    public boolean isNotNone() {
        return this != Rank.NONE;
    }

    private boolean hasNoBonusMatch() {
        return !this.isBonusMatch();
    }

    private boolean hasCountMatch(int matchCount) {
        return this.count == matchCount;
    }

    private boolean isBonusMatch() {
        return bonusMatch;
    }
}
