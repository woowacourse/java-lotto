package domain;

import java.util.Arrays;

public enum LottoRanking {

    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    LOSING(0, false, 0),
    ;

    private final int correctCount;
    private final boolean isNeedBonus;
    private final int prize;

    LottoRanking(int correctCount, boolean isNeedBonus, int prize) {
        this.correctCount = correctCount;
        this.isNeedBonus = isNeedBonus;
        this.prize = prize;
    }

    public static LottoRanking from(int correctCount, boolean isCorrectBonusNumber) {
        return Arrays.stream(LottoRanking.values())
                .filter(ranking -> ranking.correctCount == correctCount)
                .filter(ranking -> !ranking.isNeedBonus || isCorrectBonusNumber)
                .findFirst()
                .orElse(LOSING);
    }

    public int getPrize() {
        return prize;
    }

    public int getCorrectCount() {
        return correctCount;
    }
}
