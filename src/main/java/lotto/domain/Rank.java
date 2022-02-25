package lotto.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    FIRST(2_000_000_000, 6, false),
    SECOND(30_000_000, 5, true),
    THIRD(1_500_000, 5, false),
    FOURTH(50_000, 4, false),
    FIFTH(5_000, 3, false),
    NOTHING(0, 0, false);

    private static final int EXCLUDED_NUMBER = 0;

    private final int money;
    private final int count;
    private final boolean bonus;

    Rank(int money, int count, boolean bonus) {
        this.money = money;
        this.count = count;
        this.bonus = bonus;
    }

    // TODO: 정적 팩토리 메서드 공부하여 수정
    public static Rank getRank(int matchingCount, boolean bonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.count == matchingCount && rank.bonus == bonus)
                .findFirst()
                .orElse(NOTHING);
    }
    
    public int getMoney() {
        return this.money;
    }

    public int getCount() {
        return this.count;
    }
    
    public boolean isBonus() {
        return this.bonus;
    }

    public static List<Rank> getSortedRanks() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing(Rank::getMoney))
                .filter(rank -> rank.money != EXCLUDED_NUMBER)
                .collect(Collectors.toList());
    }
}
