package domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, new Money("2000000000")),
    SECOND(5, new Money("30000000")),
    THIRD(5, new Money("1500000")),
    FOURTH(4, new Money("50000")),
    FIFTH(3, new Money("5000")),
    NOTHING(0, new Money("0"));

    int count;
    Money reward;

    Rank(int count, Money reward) {
        this.count = count;
        this.reward = reward;
    }

    public static Rank from(int count, boolean bonusBallMatch) {
        if (bonusBallMatch) {
            return Rank.SECOND;
        }
        return Arrays.stream(Rank.values())
                .filter(item -> item.getCount() == count)
                .findAny()
                .orElse(NOTHING);
    }

    public int getCount() {
        return this.count;
    }

    public Money getReward() {
        return this.reward;
    }
}
