package model;

import java.util.Arrays;
import java.util.EnumMap;

public enum Prize {

    match_none("", 0, 0, false),
    match_three("3개 일치 (5000원)", 3, 5_000, false),
    match_four("4개 일치 (50000원)", 4, 50_000, false),
    match_five("5개 일치 (1500000원)", 5, 1_500_000, false),
    match_five_and_bonus("5개 일치, 보너스 볼 일치(30000000원)", 5, 30_000_000, true),
    match_six("6개 일치 (2000000000원)", 6, 2_000_000_000, false);

    private String comment;
    private Integer matchCount;
    private Integer prizeAmount;
    private boolean matchesBonus;

    Prize(String comment, Integer matchCount, Integer prizeAmount, boolean matchesBonus) {
        this.comment = comment;
        this.matchCount = matchCount;
        this.prizeAmount = prizeAmount;
        this.matchesBonus = matchesBonus;
    }

    public static EnumMap<Prize, Integer> prizeIntegerEnumMap() {
        return new EnumMap<>(Prize.class);
    }

    public static Prize find(int matchCount, boolean matchesBonusNumber) { // 5
        if (matchCount == 5 && matchesBonusNumber) {
            return Prize.match_five_and_bonus;
        } else {
            return Arrays.stream(Prize.values())
                    .filter(o -> o.matchCount == matchCount)
                    .findFirst()
                    .orElse(Prize.match_none);
        }

    }
}
