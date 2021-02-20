package domain;

import java.util.Arrays;

/**
 * Rank.java
 * 로또 등수를 정의한 enum 클래스
 *
 * @author Kimun Kim, github.com/tributetothemoon스
 * @author Daeun Lee, github.com/da-nyee
 */
public enum Rank {
    FIRST(6, new Money("2000000000")),
    SECOND(5, new Money("30000000")),
    THIRD(5, new Money("1500000")),
    FOURTH(4, new Money("50000")),
    FIFTH(3, new Money("5000")),
    NOTHING(0, new Money("0"));

    private static final long FIVE = 5;

    long count;
    Money reward;

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
