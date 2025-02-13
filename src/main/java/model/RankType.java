package model;

import java.util.Arrays;

public enum RankType { // 등수 5개, (볼 일치, 보너스 볼, 당첨 금액)
    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),

    NONE(0, false, 0);

    private final int matchNumber;
    private final boolean isBonusNumber;
    private final int price;

    RankType(int matchNumber, boolean isBonusNumber, int price) {
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

    public int getPrice() {
        return price;
    }
}
