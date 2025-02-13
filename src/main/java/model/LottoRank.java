package model;

import java.util.Arrays;

public enum LottoRank {
    FIRST(1, 6, false, 2_000_000_000),
    SECOND(2, 5, true, 30_000_000),
    THIRD(3, 5, false, 1_500_000),
    FOURTH(4, 4, false, 50_000),
    FIFTH(5, 3, false, 5_000);

    private static final int REQUIRED_BONUS_OVERLAPPED_COUNT = SECOND.overlappedCount;

    private final int rankOrder;
    private final int overlappedCount;
    private final boolean requiredBonusNumber;
    private final int prizeMoney;

    LottoRank(int rankOrder, int overlappedCount, boolean requiredBonusNumber, int prizeMoney) {
        this.rankOrder = rankOrder;
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

    public int getRankOrder() {
        return rankOrder;
    }

    public boolean isRequiredBonusNumber() {
        return requiredBonusNumber;
    }

    public static boolean requiredBonusNumber(int comparedOverlappedCount) {
        return comparedOverlappedCount == REQUIRED_BONUS_OVERLAPPED_COUNT;
    }

    public static LottoRank findByMatchCondition(
            int overlappedCount,
            boolean isBonusNUmberOverlapped
    ) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> {
                    if (overlappedCount == REQUIRED_BONUS_OVERLAPPED_COUNT) {
                        return rank.isRequiredBonusNumber() && rank.overlappedCount == overlappedCount;
                    }
                    return rank.overlappedCount == overlappedCount;
                })
                .findFirst()
                .orElse(null);
    }
}
