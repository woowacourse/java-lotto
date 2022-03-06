package domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    OTHER(0, false, 0);

    private final int count;
    private final boolean bonus;
    private final int amount;

    Rank(int count, boolean bonus, int amount) {
        this.count = count;
        this.bonus = bonus;
        this.amount = amount;
    }

    public static Rank value(int count, boolean bonus) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isSameCount(count))
                .filter(rank -> !rank.equals(SECOND) || bonus)
                .findFirst()
                .orElse(OTHER);
    }

    private boolean isSameCount(int count) {
        return this.count == count;
    }

    public static List<Rank> getRanks() {
        return Arrays.stream(Rank.values())
                .sorted(Comparator.comparingLong(Rank::getAmount))
                .collect(Collectors.toList());
    }

    public long getAmount() {
        return amount;
    }

    public int getCount() {
        return count;
    }

    public boolean getBonus() {
        return bonus;
    }
}
