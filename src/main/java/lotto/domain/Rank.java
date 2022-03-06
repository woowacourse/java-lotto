package lotto.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    FIRST(new Money(2_000_000_000), 6),
    SECOND(new Money(30_000_000), 5),
    THIRD(new Money(1_500_000), 5),
    FOURTH(new Money(50_000), 4),
    FIFTH(new Money(5_000), 3),
    NOTHING(new Money(), 0);

    private final Money prizeMoney;
    private final int count;

    Rank(Money money, int count) {
        this.prizeMoney = money;
        this.count = count;
    }

    public static Rank of(int matchingCount, boolean isBonus) {
        return Arrays.stream(values())
            .filter(rank -> rank.count == matchingCount && (!rank.equals(SECOND) || isBonus))
            .findFirst()
            .orElse(NOTHING);
    }

    public static List<Rank> getSortedRanks() {
        return Arrays.stream(values())
            .sorted(Comparator.comparing(Rank::getPrizeMoney))
            .filter(rank -> rank != NOTHING)
            .collect(Collectors.toUnmodifiableList());
    }

    public Money getPrizeMoney() {
        return this.prizeMoney;
    }

    public int getCount() {
        return this.count;
    }
}
