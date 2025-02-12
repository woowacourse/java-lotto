package lotto.domain;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Rank {

    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private int matchCount;
    private boolean matchBonus;
    private long price;

    Rank(int matchCount, boolean matchBonus, long price) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.price = price;
    }

    public static Rank of(int matchCount, boolean matchBonus) {
        Rank found = Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(NONE);

        if (found == SECOND && !matchBonus) {
            return THIRD;
        }

        return found;
    }
}
