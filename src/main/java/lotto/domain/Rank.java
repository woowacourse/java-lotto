package lotto.domain;

import java.util.Arrays;

public enum Rank {

    RANK_5(3, false, 5000),
    RANK_4(4, false, 50000),
    RANK_3(5, false, 1500000),
    RANK_2(5, true, 30000000),
    RANK_1(6, false, 2000000000),
    RANK_OUT(0, false, 0);

    private final int winningLottoMatchCount;
    private final boolean bonusNumberMatch;
    private final long prize;

    Rank(int winningLottoMatchCount, boolean bonusNumberMatch, long prize) {
        this.winningLottoMatchCount = winningLottoMatchCount;
        this.bonusNumberMatch = bonusNumberMatch;
        this.prize = prize;
    }

    public static Rank getRank(int winningNumbersMatchCount, boolean bonusNumberMatch) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.winningLottoMatchCount == winningNumbersMatchCount)
                .filter(rank -> rank.bonusNumberMatch == bonusNumberMatch)
                .findFirst()
                .orElse(RANK_OUT);
    }

    public long multiplyPrizeBy(int count) {
        return prize * count;
    }

    public String toStringWinningNumberCount() {
        return String.valueOf(winningLottoMatchCount);
    }

    public boolean isBonusNumberMatch() {
        return bonusNumberMatch;
    }

    public String toStringPrize() {
        return String.valueOf(prize);
    }
}
