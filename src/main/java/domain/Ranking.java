package domain;

import java.util.Arrays;
import java.util.Objects;

public enum Ranking {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5000),
    NOTHING(0, 0);

    private static final int BONUS_CONSIDER_LIMIT = 5;

    private final int matching;
    private final int money;

    Ranking(int matching, int money) {
        this.matching = matching;
        this.money = money;
    }

    public static Ranking select(int matching, boolean bonusMatching) {
        return Arrays.stream(Ranking.values())
                .filter(ranking -> Objects.equals(matching, ranking.matching))
                .filter(ranking -> !ranking.equals(SECOND) || bonusMatching)
                .findFirst()
                .orElse(NOTHING);
    }

    public int getMoney() {
        return money;
    }

    public int getMatching() {
        return matching;
    }
}
