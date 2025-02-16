package domain;

import java.util.Arrays;

public enum LottoRank {
    BOOM(0, false, 0),
    FIFTH_PLACE(3, false, 5_000),
    FOURTH_PLACE(4, false, 50_000),
    THIRD_PLACE(5, false, 1_500_000),
    SECOND_PLACE(5, true, 30_000_000),
    FIRST_PLACE(6, false, 2_000_000_000);

    public int winningCounter;
    public boolean bonusChecker;
    public int prize;

    LottoRank(int winningCounter, boolean bonusChecker, int prize) {
        this.winningCounter = winningCounter;
        this.bonusChecker = bonusChecker;
        this.prize = prize;
    }

    public static LottoRank findLottoRank(int winningCounter, boolean bonusChecker) {
        return Arrays.stream(values())
            .filter(lottoMatch -> lottoMatch.winningCounter == winningCounter
                && (winningCounter != SECOND_PLACE.winningCounter
                || lottoMatch.bonusChecker == bonusChecker))
            .findFirst()
            .orElse(BOOM);
    }

    public int multiplyPrice(int counter) {
        return prize * counter;
    }

    @Override
    public String toString() {
        String result = "";
        result += String.valueOf(winningCounter) + "개 일치 ";

        if (bonusChecker) {
            result += ", 보너스 볼 일치 ";
        }

        result += "(" + prize + ")";
        return result;
    }
}
