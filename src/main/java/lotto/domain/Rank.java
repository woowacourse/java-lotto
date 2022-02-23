package lotto.domain;

import java.util.Arrays;

public enum Rank {

    RANK_1(6, false, 2000000000),
    RANK_2(5, true, 30000000),
    RANK_3(5, false, 1500000),
    RANK_4(4, false, 50000),
    RANK_5(3, false, 5000),
    RANK_OUT(0, false, 0);

    private final int winningNumbersMatchCount;
    private final boolean bonusNumberMatch;
    private final long prize;

    Rank(int winningNumbersMatchCount, boolean bonusNumberMatch, long prize) {
        this.winningNumbersMatchCount = winningNumbersMatchCount;
        this.bonusNumberMatch = bonusNumberMatch;
        this.prize = prize;
    }

    public static Rank getRank(int winningNumbersMatchCount, boolean bonusNumberMatch) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.winningNumbersMatchCount == winningNumbersMatchCount)
                .filter(rank -> rank.bonusNumberMatch == bonusNumberMatch)
                .findFirst()
                .orElse(RANK_OUT);
    }

    public long multiplyPrizeBy(int count) {
        return prize * count;
    }
}
