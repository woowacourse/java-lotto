package model;

import java.util.Arrays;
import java.util.EnumMap;

public enum Prize {

    MATCH_NONE("", 0, 0),
    MATCH_THREE("3개 일치 (5000원)", 3, 5_000),
    MATCH_FOUR("4개 일치 (50000원)", 4, 50_000),
    MATCH_FIVE("5개 일치 (1500000원)", 5, 1_500_000),
    MATCH_FIVE_AND_BONUS("5개 일치, 보너스 볼 일치 (30000000원)", 5, 30_000_000),
    MATCH_SIX("6개 일치 (2000000000원)", 6, 2_000_000_000);

    private final String comment;
    private final Integer matchCount;
    private final Integer prizeAmount;

    Prize(final String comment, final Integer matchCount, final Integer prizeAmount) {
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public Integer getPrizeAmount() {
        return prizeAmount;
    }
}
