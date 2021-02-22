package lotto.domain;

import java.util.Arrays;

public enum Rank {
    LOSE(0, 0),
    FIFTH(3, 5000),
    FOURTH(4, 50000),
    THIRD(5, 1500000),
    SECOND(5, 30000000),
    FIRST(6, 2000000000);

    public static final int SECOND_MATCHCOUNT = 5;
    private final int matchCount;
    private final int prize;

    Rank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static Rank makeRankByMatch(int match, boolean bonusMatch) {
        Rank rank = Arrays.stream(Rank.values())
                .filter(singleRank -> singleRank.matchCount == match)
                .findFirst()
                .orElse(LOSE);
        if (match == SECOND_MATCHCOUNT && bonusMatch) {
            rank = SECOND;
        }
        return rank;
    }

    public int getPrize() {
        return this.prize;
    }

    public int getMatchCount() {
        return this.matchCount;
    }
}
