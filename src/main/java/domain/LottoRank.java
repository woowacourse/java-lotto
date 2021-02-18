package domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, 2000),
    SECOND(5, 3000),
    THIRD(5, 3000),
    FOURTH(4, 3000),
    FIFTH(3, 3000),
    MISS(0, 0);

    private final int correctCnt;
    private final int prize;

    LottoRank(int correctCnt, int prize) {
        this.correctCnt = correctCnt;
        this.prize = prize;
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
}
