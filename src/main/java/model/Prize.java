package model;

import java.util.Arrays;
import java.util.EnumMap;

public enum Prize {

    match_none("", 0, 0),
    match_three("3개 일치 (5000원)", 3, 5_000),
    match_four("4개 일치 (50000원)", 4, 50_000),
    match_five("5개 일치 (1500000원)", 5, 1_500_000),
    match_five_and_bonus("5개 일치, 보너스 볼 일치 (30000000원)", 5, 30_000_000),
    match_six("6개 일치 (2000000000원)", 6, 2_000_000_000);

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
        if (matchCount == Prize.match_five_and_bonus.matchCount && matchesBonusNumber) {
            return Prize.match_five_and_bonus;
        }
        return Arrays.stream(Prize.values())
                .filter(o -> o.matchCount == matchCount)
                .findFirst()
                .orElse(Prize.match_none);
    }

    public String getComment() {
        return comment;
    }

    public Integer getPrizeAmount() {
        return prizeAmount;
    }
}
