package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    NO_MATCH(0, 0);

    public static final int SECOND_AND_THIRD_CORRECT_COUNT = 5;
    private final int correct;
    private final int prize;

    LottoRank(int correct, int prize) {
        this.correct = correct;
        this.prize = prize;
    }

    public static LottoRank valueOf(long numOfMatch, boolean isMatchBonus) {
        if (numOfMatch == SECOND_AND_THIRD_CORRECT_COUNT && isMatchBonus) {
            return SECOND;
        }

        return correctOf(numOfMatch);
    }

    private static LottoRank correctOf(long numOfMatch) {
        return Arrays.stream(LottoRank.values())
            .filter(lottoRank -> lottoRank != LottoRank.SECOND)
            .filter(lottoRank -> lottoRank.getCorrect() == numOfMatch)
            .findFirst()
            .orElse(NO_MATCH);
    }

    public int getCorrect() {
        return correct;
    }

    public int getPrize() {
        return prize;
    }
}
