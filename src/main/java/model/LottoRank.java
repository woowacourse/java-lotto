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
    private final int winningAmount;

    LottoRank(int overlappedCount, boolean requiredBonusNumber, int winningAmount) {
        this.overlappedCount = overlappedCount;
        this.requiredBonusNumber = requiredBonusNumber;
        this.winningAmount = winningAmount;
    }

    public static boolean requiredBonusNumber(int comparedOverlappedCount) {
        return comparedOverlappedCount == REQUIRED_BONUS_OVERLAPPED_COUNT;
    }

    public static LottoRank findByMatchCondition(
            int overlappedCount,
            boolean isBonusNUmberOverlapped
    ) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.overlappedCount == overlappedCount)
                .filter(rank -> rank.requiredBonusNumber == isBonusNUmberOverlapped)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 조건에 맞는 등수가 없습니다."));
    }
}
