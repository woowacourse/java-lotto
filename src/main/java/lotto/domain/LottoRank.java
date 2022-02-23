package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    RANK_FIRST(6, false, 2000000000),
    RANK_SECOND(5, true, 30000000),
    RANK_THIRD(5, false, 1500000),
    RANK_FOURTH(4, false, 50000),
    RANK_FIFTH(3, false, 5000);

    private final int correctNumber;
    private final boolean isBonused;
    private final int prizeAmount;

    LottoRank(final int correctNumber, final boolean isBonused, final int prizeAmount) {
        this.correctNumber = correctNumber;
        this.isBonused = isBonused;
        this.prizeAmount = prizeAmount;
    }

    public int getCorrectNumber() {
        return correctNumber;
    }

    public boolean getIsBonused() {
        return isBonused;
    }

    public static LottoRank valueOf(int sameCount, boolean isUsed) {
        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank.getCorrectNumber() == sameCount && lottoRank.getIsBonused() == isUsed)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("죄송합니다ㅠㅠ"));
    }
}
