package lotto.domain;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum LottoRank {
    RANK_FIFTH(3, false, 5_000),
    RANK_FOURTH(4, false, 50_000),
    RANK_THIRD(5, false, 1_500_000),
    RANK_SECOND(5, true, 30_000_000),
    RANK_FIRST(6, false, 2_000_000_000);

    private final int correctCount;
    private final boolean bonused;
    private final int prizeAmount;

    LottoRank(final int correctNumber, final boolean bonused, final int prizeAmount) {
        this.correctCount = correctNumber;
        this.bonused = bonused;
        this.prizeAmount = prizeAmount;
    }

    public int getCorrectNumber() {
        return correctCount;
    }

    public boolean isBonused() {
        return bonused;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }

    public static void addLottoResult(LottoResult lottoResult, long targetCorrectCount, boolean targetBonused) {
        try {
            lottoResult.addWinningLotto(
                    Arrays.stream(LottoRank.values())
                    .filter(rank -> rank.isSameCorrectCount(targetCorrectCount) && rank.isSameBonus(targetBonused))
                    .findFirst()
                    .orElseThrow());
        } catch (NoSuchElementException e) {
        }
    }

    private boolean isSameCorrectCount(long targetCorrectCount) {
        return correctCount == targetCorrectCount;
    }

    private boolean isSameBonus(boolean targetBonused) {
        return bonused == targetBonused;
    }
}
