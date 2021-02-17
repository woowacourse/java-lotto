package lotto.domain;

public enum PrizeType {
    FIRST_PRIZE(new Money(2000000000), 6.0),
    SECOND_PRIZE(new Money(30000000), 5.5),
    THIRD_PRIZE(new Money(1500000), 5.0),
    FOURTH(new Money(50000), 4.0),
    FIFTH_PRIZE(new Money(5000), 3.0);

    private Money prizeMoney;
    private double matchCount;

    PrizeType(Money prizeMoney, double matchCount) {
        this.prizeMoney = prizeMoney;
        this.matchCount = matchCount;
    }

    public Money getPrizeMoney() {
        return prizeMoney;
    }

    public boolean isEqualToMatchCount(double matchCount) {
        return this.matchCount == matchCount;
    }
}
