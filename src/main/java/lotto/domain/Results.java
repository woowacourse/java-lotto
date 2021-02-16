package lotto.domain;

public enum Results {
    FIRST(2000000000),
    SECOND(30000000),
    THIRD(1500000),
    FOURTH(50000),
    FIFTH(5000),
    NONE(0);

    private final int prize;

    Results(int prize) {
        this.prize = prize;
    }

    public int getPrize() {
        return prize;
    }
}
