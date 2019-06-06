package domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2000000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    MISS(0, 0);

    private int numberOfMatching;
    private int winningMoney;

    Rank (int numberOfMatching, int winningMoney) {
        this.numberOfMatching = numberOfMatching;
        this.winningMoney = winningMoney;
    }

    public static Rank of(int numberOfMatching) {
        return Arrays.stream(values())
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
}
