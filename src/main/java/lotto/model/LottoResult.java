package lotto.model;

import java.util.Arrays;

public enum LottoResult {
    NONE(0, 0),
    THREE(3, 5_000),
    FOUR(4, 50_000),
    FIVE(5, 150_000),
    FIVE_BONUS(5, 30_000_000),
    SIX(6, 2_000_000_000);

    private final int prize;
    private final int correct;

    LottoResult(int correct, int prize) {
        this.correct = correct;
        this.prize = prize;
    }

    public int getPrize() {
        return prize;
    }

    public int getCorrect() {
        return correct;
    }

    public static LottoResult findLottoResult(int count, boolean bonusBall) {
        LottoResult lottoResult = Arrays.stream(LottoResult.values())
            .filter(x -> x.getCorrect() == count).findFirst().orElse(NONE);

        if (lottoResult == FIVE && bonusBall) {
            lottoResult = FIVE_BONUS;
        }

        return lottoResult;
    }
}
