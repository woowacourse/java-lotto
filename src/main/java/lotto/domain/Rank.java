package lotto.domain;

import java.util.Arrays;

public enum Rank {

    RANK_5(5000, 3),
    RANK_4(50000, 4),
    RANK_3(1500000, 5),
    RANK_2(30000000, 5, true),
    RANK_1(2000000000, 6),
    RANK_OUT(0, 0);

    private final long prize;
    private final int winningLottoMatchCount;
    private final boolean isMatchBonusNumber;

    Rank(long prize, int winningLottoMatchCount) {
        this.prize = prize;
        this.winningLottoMatchCount = winningLottoMatchCount;
        this.isMatchBonusNumber = false;
    }

    Rank(long prize, int winningLottoMatchCount, boolean isMatchBonusNumber) {
        this.prize = prize;
        this.winningLottoMatchCount = winningLottoMatchCount;
        this.isMatchBonusNumber = isMatchBonusNumber;
    }

    public static Rank getRank(int winningNumbersMatchCount, boolean bonusNumberMatch) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.winningLottoMatchCount == winningNumbersMatchCount)
                .filter(rank -> rank.isMatchBonusNumber == bonusNumberMatch)
                .findFirst()
                .orElse(RANK_OUT);
    }

    public long multiplyPrizeBy(int count) {
        return prize * count;
    }

    public String toStringWinningNumberCount() {
        return String.valueOf(winningLottoMatchCount);
    }

    public boolean isMatchBonusNumber() {
        return isMatchBonusNumber;
    }

    public String toStringPrize() {
        return String.valueOf(prize);
    }
}
