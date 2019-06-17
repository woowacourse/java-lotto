package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    LOSE(2, false, 0);

    private final int match;
    private final boolean bonus;
    private final int money;

    Rank(int match, boolean bonus, int money) {
        this.match = match;
        this.bonus = bonus;
        this.money = money;
    }

    public static Rank rank(int match, boolean bonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.match == match)
                .filter(rank -> rank.bonus == bonus)
                .findAny()
                .orElse(LOSE);
    }

    public int money() {
        return money;
    }

    public int matchCount() {
        return match;
    }

    public boolean hasBonus() {
        return bonus;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "match=" + match +
                ", bonus=" + bonus +
                ", money=" + money +
                '}';
    }
}
