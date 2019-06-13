package domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private int numberOfMatching;
    private int winningMoney;

    Rank(int numberOfMatching, int winningMoney) {
        this.numberOfMatching = numberOfMatching;
        this.winningMoney = winningMoney;
    }

    public static Rank of(int numberOfMatching, boolean BonusNumberMatch) {
        if (numberOfMatching == SECOND.numberOfMatching && BonusNumberMatch) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(rank -> rank != SECOND)
                .filter(rank -> rank.hasSame(numberOfMatching))
                .findFirst()
                .orElse(MISS);
    }

    private boolean hasSame(int numberOfMatching) {
        return this.numberOfMatching == numberOfMatching;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public int getNumberOfMatching() {
        return numberOfMatching;
    }
}
