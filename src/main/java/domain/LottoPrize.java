package domain;

import dto.LottoMatchResult;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Optional;

/*
1등 : 6개 일치 (2,000,000,000원)
2등 : 5개 일치, 보너스 볼 일치(30000000원)
3등 : 5개 일치 (1500000원)
4등 : 4개 일치 (50000원)
5등 : 3개 일치 (5000원)
 */
public enum LottoPrize {
    
    FIFTH(3, null, 5_000),
    FOURTH(4, null, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, null, 2_000_000_000),
    ;
    
    private final int minMatchCount;
    private final Boolean isBonusMatch;
    private final long prizeMoney;
    
    LottoPrize(int minMatchCount, Boolean isBonusMatch, long prizeMoney) {
        this.minMatchCount = minMatchCount;
        this.isBonusMatch = isBonusMatch;
        this.prizeMoney = prizeMoney;
    }
    
    public static Optional<LottoPrize> match(LottoMatchResult matchResult) {
        return Arrays.stream(LottoPrize.values())
                .filter(prize -> prize.minMatchCount == matchResult.matchCount())
                .filter(prize -> {
                    if (prize.isBonusMatch != null) {
                        return prize.isBonusMatch == matchResult.isBonusMatch();
                    }
                    return true;
                }).sorted(Comparator.comparingInt(prize -> (int) prize.prizeMoney))
                .findFirst();
    }
    
    public static EnumMap<LottoPrize, Integer> getInitailizedEnumMap() {
        EnumMap<LottoPrize, Integer> enumMap = new EnumMap<>(LottoPrize.class);
        for (LottoPrize value : LottoPrize.values()) {
            enumMap.put(value, 0);
        }
        return enumMap;
    }
    
    public int getMinMatchCount() {
        return minMatchCount;
    }
    
    public Boolean getBonusMatch() {
        return isBonusMatch;
    }
    
    public long getPrizeMoney() {
        return prizeMoney;
    }
}
