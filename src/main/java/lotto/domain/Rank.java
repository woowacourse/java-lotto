package lotto.domain;

import java.util.Arrays;

public enum Rank {
    NONE(0, 0, false),
    FIFTH(5_000, 3, false),
    FOURTH(50_000, 4, false),
    THIRD(1_500_000, 5, false),
    SECOND(30_000_000, 5, true),
    FIRST(2_000_000_000, 6, false);

    private final int winningAmount;
    private final int matchCount;
    private final boolean hasBonusNumber;

    Rank(final int winningAmount, final int matchCount, final boolean hasBonusNumber) {
        this.winningAmount = winningAmount;
        this.matchCount = matchCount;
        this.hasBonusNumber = hasBonusNumber;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getWinningAmount() {
        return winningAmount;
    }

    public int getWinningAmountByCount(int count) {
        return winningAmount * count;
    }

    public static Rank getRank(final int matchCount, final boolean hasBonusNumber) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchCount == matchCount && rank.hasBonusNumber == hasBonusNumber)
                .findAny()
                .orElse(NONE);
    }
}
