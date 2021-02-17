package lotto.domain;

import java.util.Arrays;

public enum PrizeType {
    NO_PRIZE(new Money(0), 0),
    FIFTH_PRIZE(new Money(5000), 3),
    FOURTH_PRIZE(new Money(50000), 4),
    THIRD_PRIZE(new Money(1500000), 5),
    SECOND_PRIZE(new Money(30000000), 5),
    FIRST_PRIZE(new Money(2000000000), 6);

    public static final int BONUS_CHECK_PIVOT = 5;
    private Money prizeMoney;
    private int matchCount;

    PrizeType(Money prizeMoney, int matchCount) {
        this.prizeMoney = prizeMoney;
        this.matchCount = matchCount;
    }

    public Money getPrizeMoney() {
        return prizeMoney;
    }

    public double getMatchCount(){
        return matchCount;
    }

    public boolean isEqualToMatchCount(double matchCount) {
        return this.matchCount == matchCount;
    }

    private static boolean isMatchCountEqualsPivot(int matchCount) {
        return matchCount == BONUS_CHECK_PIVOT;
    }

    public static PrizeType getPrizeType(int matchCount, boolean isBonusBall){
        if(isMatchCountEqualsPivot(matchCount) && isBonusBall){
            return SECOND_PRIZE;
        }
        return Arrays.stream(values()).filter(s->s.matchCount == matchCount).findFirst().orElse(NO_PRIZE);
    }
}
