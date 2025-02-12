package domain;

/*
1등 : 6개 일치 (2,000,000,000원)
2등 : 5개 일치, 보너스 볼 일치(30000000원)
3등 : 5개 일치 (1500000원)
4등 : 4개 일치 (50000원)
5등 : 3개 일치 (5000원)
 */
public enum LottoPrize {
    
    FIRST(
            (matchCount, isBonusMatch) -> matchCount == 6,
            2_000_000_000
    ),
    SECOND(
            (matchCount, isBonusMatch) -> matchCount == 5 && isBonusMatch,
            30_000_000
    ),
    THIRD(
            (matchCount, isBonusMatch) -> matchCount == 5 && !isBonusMatch,
            1_500_000
    ),
    FOURTH(
            (matchCount, isBonusMatch) -> matchCount == 4,
            50_000
    ),
    FIFTH(
            (matchCount, isBonusMatch) -> matchCount == 3,
            5_000
    ),
    NONE(
            (matchCount, isBonusMatch) -> matchCount < 3,
            0
    ),
    ;
    
    private final PrizeMatcher prizeMatcher;
    private final long prizeMoney;
    
    LottoPrize(PrizeMatcher prizeMatcher, long prizeMoney) {
        this.prizeMatcher = prizeMatcher;
        this.prizeMoney = prizeMoney;
    }
    
    public static LottoPrize match(int matchCount, boolean isBonusMatch) {
        for (LottoPrize lottoPrize : LottoPrize.values()) {
            boolean matches = lottoPrize.prizeMatcher.matches(matchCount, isBonusMatch);
            if (matches) {
                return lottoPrize;
            }
        }
        
        throw new IllegalStateException("해당하는 당첨 정보가 존재하지 않습니다.");
    }
    
    @FunctionalInterface
    private interface PrizeMatcher {
        
        boolean matches(int matchCount, boolean isBonusMatch);
    }
}
