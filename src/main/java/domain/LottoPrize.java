package domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;

/*
1등 : 6개 일치 (2,000,000,000원)
2등 : 5개 일치, 보너스 볼 일치(30000000원)
3등 : 5개 일치 (1500000원)
4등 : 4개 일치 (50000원)
5등 : 3개 일치 (5000원)
 */
public enum LottoPrize {

    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000),
    NONE(0, false, 0),
    ;

    private final int minMatchCount;
    private final boolean isBonusMatch;
    private final long prizeMoney;

    LottoPrize(final int minMatchCount, final boolean isBonusMatch, final long prizeMoney) {
        this.minMatchCount = minMatchCount;
        this.isBonusMatch = isBonusMatch;
        this.prizeMoney = prizeMoney;
    }

    public static LottoPrize match(final int matchCount, final boolean isBonusMatch) {
        return Arrays.stream(LottoPrize.values())
            .filter(prize -> prize.minMatchCount == matchCount)
            .filter(prize -> isSecondPrize(isBonusMatch, prize))
            .sorted(Comparator.comparingLong(LottoPrize::getPrizeMoney).reversed())
            .findFirst()
            .orElse(LottoPrize.NONE);
    }

    public static Map<LottoPrize, Integer> getInitailizedEnumMap() {
        Map<LottoPrize, Integer> enumMap = new EnumMap<>(LottoPrize.class);
        for (LottoPrize value : LottoPrize.values()) {
            enumMap.put(value, 0);
        }
        return enumMap;
    }

    public int getMinMatchCount() {
        return minMatchCount;
    }

    public boolean getBonusMatch() {
        return isBonusMatch;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }

    private static boolean isSecondPrize(final boolean isBonusMatch, final LottoPrize prize) {
        if (prize == LottoPrize.SECOND) {
            return isBonusMatch;
        }
        return true;
    }
}
