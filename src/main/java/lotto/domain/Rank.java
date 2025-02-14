package lotto.domain;

import java.util.EnumMap;

public enum Rank {

    FIRST_PRIDE(2000000000),
    SECOND_PRIDE(30000000),
    THIRD_PRIDE(1500000),
    FOURTH_PRIDE(50000),
    FIFTH_PRIDE(5000),
    BOOM(0);

    private final int prize;

    Rank(int prize) {
        this.prize = prize;
    }

    public static Rank checkPrize(int matchCount, boolean matchBonus) {
        if (matchCount == 6) {
            return FIRST_PRIDE;
        }
        if (matchCount == 5 && matchBonus) {
            return SECOND_PRIDE;
        }
        if (matchCount == 5) {
            return THIRD_PRIDE;
        }
        if (matchCount == 4) {
            return FOURTH_PRIDE;
        }
        if (matchCount == 3) {
            return FIFTH_PRIDE;
        }
        return BOOM;
    }

    public static EnumMap<Rank, Integer> makeDefaultMap() {

        EnumMap<Rank, Integer> rankIntegerEnumMap = new EnumMap<>(Rank.class);

        for (Rank rank : Rank.values()) {
            rankIntegerEnumMap.put(rank, 0);
        }

        return rankIntegerEnumMap;
    }

    public static int calculateTotalPrize(EnumMap<Rank, Integer> lottoResult) {

        int totalPrize = 0;
        for (Rank rank : lottoResult.keySet()) {
            totalPrize += rank.prize * lottoResult.get(rank);
        }
        return totalPrize;
    }

    public int getPrize() {
        return prize;
    }
}
