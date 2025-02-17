package domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public enum LottoRank {
    BOOM(0, false, 0),
    FIFTH_PLACE(3, false, 5_000),
    FOURTH_PLACE(4, false, 50_000),
    THIRD_PLACE(5, false, 1_500_000),
    SECOND_PLACE(5, true, 30_000_000),
    FIRST_PLACE(6, false, 2_000_000_000);

    public final int winningCounter;
    public final boolean bonusChecker;
    public final int prize;

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

    public static Map<LottoRank, Integer> createLottoRankCounter() {
        Map<LottoRank, Integer> result = new LinkedHashMap<>();
        for (LottoRank lottoRank : LottoRank.values()) {
            result.put(lottoRank, 0);
        }
        return result;
    }

    public int multiplyPrice(int counter) {
        return prize * counter;
    }
}
