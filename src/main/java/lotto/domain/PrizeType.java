package lotto.domain;

public enum PrizeType {
    FIRST_PRIZE(2000000000, 6.0),
    SECOND_PRIZE(30000000, 5.5),
    THIRD_PRIZE(1500000, 5.0),
    FOURTH(50000, 4.0),
    FIFTH_PRIZE(5000, 3.0);

    private long prizeMoney;
    private double matchCount;

    PrizeType(long prizeMoney, double matchCount) {
        this.prizeMoney = prizeMoney;
        this.matchCount = matchCount;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }

    public boolean isEqualToMatchCount(double matchCount) {
        return this.matchCount == matchCount;
    }
}
