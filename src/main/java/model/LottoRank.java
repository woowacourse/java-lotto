package model;

import java.util.Arrays;
import java.util.Objects;

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
    private final int winningAmount;

    LottoRank(int rankOrder, int overlappedCount, boolean requiredBonusNumber, int winningAmount) {
        this.rankOrder = rankOrder;
        this.overlappedCount = overlappedCount;
        this.requiredBonusNumber = requiredBonusNumber;
        this.winningAmount = winningAmount;
    }

    public int getOverlappedCount() {
        return overlappedCount;
    }

    public int getWinningAmount() {
        return winningAmount;
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

    public static LottoRank findByOverlappedCountAndBonusNumber(
            int overlappedCount,
            boolean isBonusNumberOverlapped
    ) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> {
                    if (requiredBonusNumber(overlappedCount)) {
                        return Objects.equals(rank.isRequiredBonusNumber(), isBonusNumberOverlapped)
                                && Objects.equals(rank.overlappedCount, overlappedCount);
                    }
                    return Objects.equals(rank.overlappedCount, overlappedCount);
                })
                .findFirst()
                .orElse(null);
    }
}
