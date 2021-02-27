package domain;

import java.util.Arrays;

/**
 * Rank.java
 * 로또 등수를 정의한 enum 클래스
 *
 * @author Kimun Kim, github.com/tributetothemoon
 * @author Daeun Lee, github.com/da-nyee
 */
public enum Rank {
    FIRST(6, false, Money.from(2_000_000_000)),
    SECOND(5, true, Money.from(30_000_000)),
    THIRD(5, false, Money.from(1_500_000)),
    FOURTH(4, false, Money.from(50_000)),
    FIFTH(3, false, Money.from(5_000)),
    NOTHING(0, false, Money.from(0));

    private final long count;
    private final boolean hasBonusBallMatch;
    private final Money reward;

    Rank(long count, boolean hasBonusBallMatch, Money reward) {
        this.count = count;
        this.reward = reward;
        this.hasBonusBallMatch = hasBonusBallMatch;
    }

    public static Rank from(long count, boolean hasBonusBallMatch) {
        return Arrays.stream(Rank.values())
                .filter(item -> item.getCount() == count)
                .filter(item -> item.hasBonusBallMatch == hasBonusBallMatch)
                .findAny()
                .orElse(NOTHING);
    }

    public long getCount() {
        return this.count;
    }

    public Money getReward() {
        return this.reward;
    }

    @Override
    public String toString() {
        return String.format("Count: %d, Money: %d", this.count, this.getReward().toLong());
    }
}
