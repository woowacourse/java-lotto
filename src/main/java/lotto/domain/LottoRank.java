package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    RANK_FIFTH(3, false, 5000),
    RANK_FOURTH(4, false, 50000),
    RANK_THIRD(5, false, 1500000),
    RANK_SECOND(5, true, 30000000),
    RANK_FIRST(6, false, 2000000000);

    private final int correctCount;
    private final boolean isBonused;
    private final int prizeAmount;

    LottoRank(final int correctNumber, final boolean isBonused, final int prizeAmount) {
        this.correctCount = correctNumber;
        this.isBonused = isBonused;
        this.prizeAmount = prizeAmount;
    }

    public int getCorrectNumber() {
        return correctCount;
    }

    public boolean getIsBonused() {
        return isBonused;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }

    public static LottoRank valueOf(int targetCorrectCount, boolean isTargetBonused) {
        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank.getCorrectNumber() == targetCorrectCount && lottoRank.getIsBonused() == isTargetBonused)
                .findFirst()
                .orElse(null);
    }
}
