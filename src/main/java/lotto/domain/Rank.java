package lotto.domain;

import java.util.Arrays;

public enum Rank {
    LOSE(0, false,0),
    FIFTH(3, false,5_000),
    FOURTH(4, false,50_000),
    THIRD(5, false,1_500_000),
    SECOND(5, true,30_000_000),
    FIRST(6, false,2_000_000_000);

    private final int matchCount;
    private final boolean bonusBall;
    private final int prize;

    Rank(int matchCount, boolean bonusBall, int prize) {
        this.matchCount = matchCount;
        this.bonusBall = bonusBall;
        this.prize = prize;
    }

    public static Rank makeRankByMatch(int match, boolean bonusMatch) {
        return Arrays.stream(Rank.values())
                .filter(singleRank -> singleRank.matchCount == match && singleRank.bonusBall == bonusMatch)
                .findFirst()
                .orElse(LOSE);
    }

    public int getPrize() {
        return this.prize;
    }

    public int getMatchCount() {
        return this.matchCount;
    }
}
