package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    OTHER(0,false, 0),
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000);

    private final int count;
    private final boolean bonus;
    private final int amount;

    Rank(int count, boolean bonus, int amount) {
        this.count = count;
        this.bonus = bonus;
        this.amount = amount;
    }

    public static Rank value(int count, boolean bonus) {
        for (Rank rank : Rank.values()) {
            if (rank.count == count && rank.bonus == bonus) {
                return rank;
            }
        }
        return OTHER;
    }

    public static List<Rank> getRanks() {
        return Arrays.stream(Rank.values())
            .filter(Rank::isNotOther)
            .collect(Collectors.toUnmodifiableList());
    }

    private static boolean isNotOther(Rank rank) {
        return rank != OTHER;
    }

    public static boolean isThirdCount(int count) {
        return count == THIRD.count;
    }

    public int getAmount() {
        return amount;
    }

    public int getCount() {
        return count;
    }

    public boolean isBonus() {
        return bonus;
    }
}
