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
    FIRST(6, new Money(2_000_000_000)),
    SECOND(5, new Money(30_000_000)),
    THIRD(5, new Money(1_500_000)),
    FOURTH(4, new Money(50_000)),
    FIFTH(3, new Money(5_000)),
    NOTHING(0, new Money(0));

    private static final long FIVE = 5;

    private final long count;
    private final Money reward;

    Rank(long count, Money reward) {
        this.count = count;
        this.reward = reward;
    }

    public static Rank from(long count, boolean bonusBallMatch) {
        if (bonusBallMatch && count == FIVE) {
            return Rank.SECOND;
        }
        return Arrays.stream(Rank.values())
                .filter(item -> item.getCount() == count)
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
