package lotto.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum LottoRank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 150000),
    FOURTH(4, 50000),
    FIFTH(3, 5000);

    private static final int INITIAL_NUMBER = 0;
    private final int rank;
    private final int prize;

    LottoRank(int rank, int prize) {
        this.rank = rank;
        this.prize = prize;
    }

    public double prizeResult(int count) {
        return count * this.prize;
    }

    public static LottoRank of(int matchNumber, boolean hasBonusNumber) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.isSameMatchCount(matchNumber))
                .filter(rank -> !rank.equals(SECOND) || hasBonusNumber)
                .findFirst()
                .orElse(null);
    }

    private boolean isSameMatchCount(int matchNumber) {
        return this.rank == matchNumber;
    }

    public int getRank() {
        return rank;
    }

    public int getPrize() {
        return prize;
    }

    public static boolean checkNoPrize(int count) {
        return count < FIFTH.rank;
    }

    public static Map<LottoRank, Integer> makeLottoResult() {
        Map<LottoRank, Integer> lottoResult = new HashMap<>();
        for (LottoRank lottoRank : LottoRank.values()) {
            lottoResult.put(lottoRank, INITIAL_NUMBER);
        }
        return lottoResult;
    }
}
