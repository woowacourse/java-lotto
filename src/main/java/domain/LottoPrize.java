package domain;

import java.util.Arrays;
import java.util.Comparator;

public enum LottoPrize {
    FIRST(2000000000, 6, 0, 1),
    SECOND(30000000, 5, 1, 2),
    THIRD(1500000, 5, 0, 3),
    FOURTH(50000, 4, 0, 4),
    FIFTH(5000, 3, 0, 5),
    NOTHING(0, 0, 0, Integer.MAX_VALUE);

    private final int money;
    private final int countMatched;
    private final int countBonusNumberMatched;
    private final int rank;

    LottoPrize(int money, int countMatched, int countBonusNumberMatched, int rank) {
        this.money = money;
        this.countMatched = countMatched;
        this.countBonusNumberMatched = countBonusNumberMatched;
        this.rank = rank;
    }

    public static LottoPrize getLottoPrize(int countMatched, boolean isBonusNumberMatched) {
        int countBonusNumberMatched = Boolean.compare(isBonusNumberMatched, false);
        return Arrays.stream(LottoPrize.values()).filter(lottoPrize ->
                        lottoPrize.countMatched == countMatched
                                && lottoPrize.countBonusNumberMatched <= countBonusNumberMatched)
                .max(Comparator.comparing(LottoPrize::getMoney))
                .orElse(LottoPrize.NOTHING);
    }

    public int getMoney() {
        return money;
    }

    public int getCountMatched() {
        return countMatched;
    }

    public int getCountBonusNumberMatched() {
        return countBonusNumberMatched;
    }
}