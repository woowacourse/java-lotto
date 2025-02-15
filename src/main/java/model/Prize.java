package model;

import java.util.Arrays;
import java.util.EnumMap;

public enum Prize {

    FIRST_PLACE(6, 2_000_000_000, false),
    SECOND_PLACE(5, 30_000_000, true),
    THIRD_PLACE(5, 1_500_000, false),
    FOUR_PLACE(4, 50_000, false),
    FIFTH_PLACE(3, 5_000, false),
    LAST_PLACE(0, 0, false);

    private final Integer matchCount;
    private final Integer prizeMoney;
    private final boolean bonusMatch;

    Prize(final Integer matchCount, final Integer prizeMoney, final boolean bonusMatch) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.bonusMatch = bonusMatch;
    }

    public static EnumMap<Prize, Integer> initializeMap() {
        EnumMap<Prize, Integer> enumMap = new EnumMap<>(Prize.class);
        for (Prize prize : Prize.values()) {
            enumMap.put(prize, 0);
        }
        return enumMap;
    }

    public static Prize find(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(prize -> prize.matchCount == matchCount)
                .filter(prize -> !prize.bonusMatch || bonusMatch)
                .findFirst()
                .orElse(LAST_PLACE);
    }

    public Integer getPrizeMoney() {
        return prizeMoney;
    }
}
