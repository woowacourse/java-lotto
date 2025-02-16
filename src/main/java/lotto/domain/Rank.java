package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;

public enum Rank {
    FIRST_PRIDE(6, false, 2_000_000_000),
    SECOND_PRIDE(5, true, 30_000_000),
    THIRD_PRIDE(5, false, 1_500_000),
    FOURTH_PRIDE(4, false, 50_000),
    FIFTH_PRIDE(3, false, 5_000),
    BOOM(0, false, 0);

    private final int matchCount;
    private final boolean matchBonus;
    private final int prize;

    Rank(int matchCount, boolean matchBonus, int prize) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
    }

    public static Rank checkPrize(int matchCount, boolean matchBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount && (!rank.matchBonus || matchBonus))
                .findFirst()
                .orElse(BOOM);
    }

    public static EnumMap<Rank, Integer> makeDefaultMap() {
        EnumMap<Rank, Integer> rankIntegerEnumMap = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            rankIntegerEnumMap.put(rank, 0);
        }
        return rankIntegerEnumMap;

    }

    public static int calculateTotalPrize(EnumMap<Rank, Integer> lottoResult) {
        return lottoResult.entrySet().stream()
                .mapToInt(entry -> entry.getKey().prize * entry.getValue())
                .sum();
    }

    public int getPrize() {
        return prize;
    }
}
