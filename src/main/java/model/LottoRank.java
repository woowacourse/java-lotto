package model;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000);

    private static final int REQUIRED_BONUS_OVERLAPPED_COUNT = SECOND.overlappedCount;
    private final int overlappedCount;
    private final boolean requiredBonusNumber;
    private final int prizeMoney;

    LottoRank(int overlappedCount, boolean requiredBonusNumber, int prizeMoney) {
        this.overlappedCount = overlappedCount;
        this.requiredBonusNumber = requiredBonusNumber;
        this.prizeMoney = prizeMoney;
    }

    public int getOverlappedCount() {
        return overlappedCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public boolean isRequiredBonusNumber() {
        return requiredBonusNumber;
    }

    public static boolean requiredBonusNumber(int comparedOverlappedCount) {
        return comparedOverlappedCount == REQUIRED_BONUS_OVERLAPPED_COUNT;
    }

    public static LottoRank findByMatchCondition(
            int overlappedCount,
            boolean isBonusNumberOverlapped
    ) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> isMatchingRank(overlappedCount, isBonusNumberOverlapped, rank))
                .findFirst()
                .orElse(null);
    }

    private static boolean isMatchingRank(
            int overlappedCount,
            boolean isBonusNumberOverlapped, LottoRank rank
    ) {
        if (overlappedCount == REQUIRED_BONUS_OVERLAPPED_COUNT) {
            return isMatchingRankIncludingBonus(isBonusNumberOverlapped, overlappedCount, rank);
        }
        return rank.overlappedCount == overlappedCount;
    }

    private static boolean isMatchingRankIncludingBonus(
            boolean isBonusNumberOverlapped,
            int overlappedCount,
            LottoRank rank) {
        return rank.requiredBonusNumber == isBonusNumberOverlapped
                && rank.overlappedCount == overlappedCount;
    }
}
