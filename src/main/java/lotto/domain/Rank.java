package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Rank {
    FIRST_PLACE(6, false, 2000000000),
    SECOND_PLACE(5, true, 30000000),
    THIRD_PLACE(5, false, 1500000),
    FOURTH_PLACE(4, false, 50000),
    FIFTH_PLACE(3, false, 5000),
    SIXTH_PLACE(0, false, 0),
    ;

    public static final List<Rank> winningPlaces =
            List.of(FIFTH_PLACE, FOURTH_PLACE, THIRD_PLACE, SECOND_PLACE, FIRST_PLACE);

    private final int matchCount;
    private final boolean isBonusMatch;
    private final int prizeAmount;

    Rank(final int matchCount, final boolean isBonusMatch, final int prizeAmount) {
        this.matchCount = matchCount;
        this.isBonusMatch = isBonusMatch;
        this.prizeAmount = prizeAmount;
    }

    public static Rank getPlace(int matchCount, boolean isBonusMatch) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchCount == matchCount)
                .filter(rank -> {
                    if (matchCount == SECOND_PLACE.matchCount) {
                        return rank.isBonusMatch == isBonusMatch;
                    }
                    return true;
                }).findFirst().orElse(SIXTH_PLACE);
    }

    public static long calculateTotalPrize(List<Rank> ranks) {
        return ranks.stream()
                .mapToLong(rank -> rank.prizeAmount)
                .sum();
    }

    public static Map<Rank, Integer> count(List<Rank> ranks) {
        Map<Rank, Integer> rankCount = new HashMap<>();
        Arrays.stream(Rank.values()).forEach(rank -> rankCount.put(rank, Collections.frequency(ranks, rank)));
        return rankCount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusMatch() {
        return isBonusMatch;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }
}
