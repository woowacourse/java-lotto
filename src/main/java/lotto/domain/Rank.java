package lotto.domain;

import java.util.Arrays;

public enum Rank {

    FIRST(2000000000),
    ;

    private final long reward;

    Rank (final long reward) {
        this.reward = reward;
    }

    public static long calculateMoney(Rank currentRank, long count) {
        return currentRank.reward * count;
    }
}
