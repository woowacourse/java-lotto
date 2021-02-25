package lotto.domain;

import java.util.Arrays;

public enum Rank {
    LOSE(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

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
        if (match == 5 && bonusMatch) {
            rank = SECOND;
        }
        return rank;
    }

    public int getPrize() {
        return this.prize;
    }

    @Override
    public String toString() {
        if (this == SECOND) {
            return matchCount + "개 일치, 보너스 볼 일치 (" + prize + "원) - ";
        }
        return matchCount + "개 일치 (" + prize + "원) - ";
    }
}
