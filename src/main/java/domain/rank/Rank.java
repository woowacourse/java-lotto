package domain.rank;

import java.util.Arrays;
import java.util.Objects;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5000),
    NOTHING(0, 0);

    private final int matching;
    private final int money;

    Rank(int matching, int money) {
        this.matching = matching;
        this.money = money;
    }

    public static Rank select(int matching, boolean bonusMatching) {
        return Arrays.stream(Rank.values())
                .filter(rank -> Objects.equals(matching, rank.matching))
                .filter(rank -> !rank.equals(SECOND) || bonusMatching)
                .findFirst()
                .orElse(NOTHING);
    }

    public int getMoney() {
        return money;
    }

    public int getMatching() {
        return matching;
    }

    public boolean equalsNothing() {
        return equals(NOTHING);
    }
}
