package domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private final int correctCnt;
    private final Money prize;

    LottoRank(int correctCnt, long prize) {
        this.correctCnt = correctCnt;
        this.prize = Money.valueOf(prize);
    }

    public static LottoRank isMatch(int correctCnt, boolean isBonusMatch) {
        if (SECOND.matchCount(correctCnt) || THIRD.matchCount(correctCnt)) {
            return determineSecondOrThird(isBonusMatch);
        }

        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank.matchCount(correctCnt))
                .findAny()
                .orElse(MISS);
    }

    private static LottoRank determineSecondOrThird(boolean isBonusMatch) {
        if (isBonusMatch) {
            return SECOND;
        }

        return THIRD;
    }

    private boolean matchCount(int countOfMatch) {
        return this.correctCnt == countOfMatch;
    }

    public Money getPrize() {
        return prize;
    }

    public int getCorrectCnt() {
        return correctCnt;
    }

    public Money getTotalPrize(Long countOfLotto) {
        return prize.multiply(countOfLotto);
    }
}
