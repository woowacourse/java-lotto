package lotto.domain;

public enum Rank {

    FIRST(2000000000),
    SECOND(30000000),
    THIRD(1500000),
    FOURTH(50000),
    FIFTH(5000),
    ;

    private final long reward;

    Rank (final long reward) {
        this.reward = reward;
    }

    public static long calculateMoney(Rank currentRank, long count) {
        return currentRank.reward * count;
    }
}
