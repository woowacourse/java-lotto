package domain;

import java.util.Arrays;

public enum LottoPrize {
    FIRST(2000000000, 6, false, 1),
    SECOND(30000000, 5, true, 2),
    THIRD(1500000, 5, false, 3),
    FOURTH(50000, 4, false, 4),
    FIFTH(5000, 3, false, 5),
    NOTHING(0, 0, false, Integer.MAX_VALUE);

    private final int money;
    private final int countMatched;
    private final boolean isBonusNumberMatched;
    private final int rank;

    LottoPrize(int money, int countMatched, boolean isBonusNumberMatched, int rank) {
        this.money = money;
        this.countMatched = countMatched;
        this.isBonusNumberMatched = isBonusNumberMatched;
        this.rank = rank;
    }

    public int getMoney() {
        return money;
    }

    public int getCountMatched() {
        return countMatched;
    }

    public boolean isBonusNumberMatched() {
        return isBonusNumberMatched;
    }

    public static LottoPrize value(int countMatched, boolean isBonusNumberMatched) {
        return Arrays.stream(LottoPrize.values())
                .filter(prize -> prize.countMatched == countMatched)
                .filter(prize -> countMatched != 5 || prize.isBonusNumberMatched == isBonusNumberMatched)
                .findFirst()
                .orElse(NOTHING);
    }
}
