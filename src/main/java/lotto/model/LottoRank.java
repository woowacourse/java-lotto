package lotto.model;

import java.util.Arrays;
import java.util.List;

public enum LottoRank {
    FIFTH(3, 5000),
    FOURTH(4, 50000),
    THIRD(5, 150000),
    SECOND(5, 30000000),
    FIRST(6, 2000000000),
    NOT_FOUND(0, 0);

    private final int rank;
    private final int prize;

    LottoRank(int rank, int prize) {
        this.rank = rank;
        this.prize = prize;
    }

    public double prizeResult(int count) {
        return count * this.prize;
    }

    public static LottoRank valueOfMatchNumber(int matchNumber) {
        return Arrays.asList(LottoRank.values())
                .stream()
                .filter(value -> value.rank == matchNumber)
                .findAny()
                .orElse(NOT_FOUND);
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

    public static boolean checkThirdWinner(int count) {
        return count == THIRD.rank;
    }
}
