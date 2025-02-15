package domain;

import java.util.Arrays;
import java.util.Comparator;

public enum LottoPrize {
    FIRST(2000000000, 6, 0),
    SECOND(30000000, 5, 1),
    THIRD(1500000, 5, 0),
    FOURTH(50000, 4, 0),
    FIFTH(5000, 3, 0),
    NOTHING(0, 0, 0);

    private final int money;
    private final int countMatched;
    private final int countBonusNumberMatched;

    LottoPrize(int money, int countMatched, int countBonusNumberMatched) {
        this.money = money;
        this.countMatched = countMatched;
        this.countBonusNumberMatched = countBonusNumberMatched;
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