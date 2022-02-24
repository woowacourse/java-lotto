package lotto.domain;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    FIRST(2000000000, 6, false),
    SECOND(30000000, 5, true),
    THIRD(1500000, 5, false),
    FOURTH(50000, 4, false),
    FIFTH(5000, 3, false),
    NOTHING(0, 0, false);

    private static final int EXCLUDED_NUMBER = 0;

    private final int money;
    private final int count;
    private final boolean isBonus;

    Rank(int money, int count, boolean isBonus) {
        this.money = money;
        this.count = count;
        this.isBonus = isBonus;
    }

    public static Rank getRank(int matchingCount, boolean isBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.count == matchingCount)
                .filter(rank -> rank.isBonus == isBonus)
                .findFirst()
                .orElse(NOTHING);
    }
    
    public int getMoney() {
        return this.money;
    }

    public int getCount() {
        return this.count;
    }
    
    public boolean getIsBonus() {
        return this.isBonus;
    }

    public static List<Rank> getSortedRanks() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing(Rank::getMoney))
                .filter(rank -> rank.money != EXCLUDED_NUMBER)
                .collect(Collectors.toList());
    }
}
