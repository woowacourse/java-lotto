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
        this.prize = new Money(prize);
    }

    public static LottoRank valueOf(int matchCnt, boolean isBonusMatch) {
        if (SECOND.matchCount(matchCnt) || THIRD.matchCount(matchCnt)) {
            return determineSecondOrThird(isBonusMatch);
        }

        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank.matchCount(matchCnt))
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

    public int getMatchCount() {
        return correctCnt;
    }
}
