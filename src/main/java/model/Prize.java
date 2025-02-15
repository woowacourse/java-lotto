package model;

import java.util.Arrays;
import java.util.EnumMap;

public enum Prize {

    MATCH_NONE(0, 0),
    MATCH_THREE(3, 5_000),
    MATCH_FOUR( 4, 50_000),
    MATCH_FIVE( 5, 1_500_000),
    MATCH_FIVE_AND_BONUS( 5, 30_000_000),
    MATCH_SIX(6, 2_000_000_000);

    private final int matchCount;
    private final int prizeAmount;

    Prize(final int matchCount, final int prizeAmount) {
        this.matchCount = matchCount;
        this.prizeAmount = prizeAmount;
    }

    public static EnumMap<Prize, Integer> initializeMap() {
        EnumMap<Prize, Integer> enumMap = new EnumMap<>(Prize.class);
        for (Prize prize : Prize.values()) {
            enumMap.put(prize, 0);
        }
        return enumMap;
    }

    public static Prize find(int matchCount, boolean matchesBonusNumber) {
        if (matchCount == Prize.MATCH_FIVE_AND_BONUS.matchCount && matchesBonusNumber) {
            return Prize.MATCH_FIVE_AND_BONUS;
        }
        return Arrays.stream(Prize.values())
                .filter(o -> o.matchCount == matchCount)
                .findFirst()
                .orElse(Prize.MATCH_NONE);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }
}
