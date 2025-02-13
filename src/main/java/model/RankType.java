package model;

import java.util.Arrays;

public enum RankType {
    FIRST(1, 6, false, 2000000000),
    SECOND(2, 5, true, 30000000),
    THIRD(3, 5, false, 1500000),
    FOURTH(4, 4, false, 50000),
    FIFTH(5, 3, false, 5000),

    NONE(0, 0, false, 0);

    private final int rank;
    private final int matchNumber;
    private final boolean isBonusNumber;
    private final int price;

    RankType(int rank, int matchNumber, boolean isBonusNumber, int price) {
        this.rank = rank;
        this.matchNumber = matchNumber;
        this.isBonusNumber = isBonusNumber;
        this.price = price;
    }

    public static RankType evaluateRank(int matchNumber, boolean isBonusNumber) {
        return Arrays.stream(values())
                .filter(rankType -> rankType.matchNumber == matchNumber && rankType.isBonusNumber == isBonusNumber)
                .findFirst()
                .orElse(NONE);
    }

    public int getRank() {
        return rank;
    }

    public int getPrice() {
        return price;
    }
}
