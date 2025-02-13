package domain;

import java.util.Arrays;
import java.util.List;

public enum Prize {
    FIRST_PLACE(6, false, 2000000000),
    SECOND_PLACE(5, true, 30000000),
    THIRD_PLACE(5, false, 1500000),
    FOURTH_PLACE(4, false, 50000),
    FIFTH_PLACE(3, false, 5000),
    SIXTH_PLACE(0, false, 0),
    ;

    public final int matchCount;
    public final boolean isBonusMatch;
    public final int prizeAmount;

    Prize(final int matchCount, final boolean isBonusMatch, final int prizeAmount) {
        this.matchCount = matchCount;
        this.isBonusMatch = isBonusMatch;
        this.prizeAmount = prizeAmount;
    }

    public static Prize getPrizePlace(int matchCount, boolean isBonusMatch) {
        return Arrays.stream(Prize.values())
                .filter(p -> p.matchCount == matchCount)
                .filter(prize -> {
                    if (matchCount == 5) {
                        return prize.isBonusMatch == isBonusMatch;
                    }
                    return true;
                }).findFirst().orElse(SIXTH_PLACE);
    }

    public static double calculateEarningRate(List<Prize> prizes, int purchasedAmount) {
        return (double) calculateTotalPrize(prizes) / purchasedAmount;
    }

    private static long calculateTotalPrize(List<Prize> prizes) {
        return prizes.stream()
                .mapToLong(p -> p.prizeAmount)
                .sum();
    }
}
