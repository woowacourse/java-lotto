package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Prize {
    FIRST_PLACE(6, false, 2000000000),
    SECOND_PLACE(5, true, 30000000),
    THIRD_PLACE(5, false, 1500000),
    FOURTH_PLACE(4, false, 50000),
    FIFTH_PLACE(3, false, 5000),
    SIXTH_PLACE(0, false, 0),
    ;

    public static final List<Prize> winningPlaces =
            List.of(FIFTH_PLACE, FOURTH_PLACE, THIRD_PLACE, SECOND_PLACE, FIRST_PLACE);

    private final int matchCount;
    private final boolean isBonusMatch;
    private final int prizeAmount;

    Prize(final int matchCount, final boolean isBonusMatch, final int prizeAmount) {
        this.matchCount = matchCount;
        this.isBonusMatch = isBonusMatch;
        this.prizeAmount = prizeAmount;
    }

    public static Prize getPrizePlace(int matchCount, boolean isBonusMatch) {
        return Arrays.stream(Prize.values())
                .filter(prize -> prize.matchCount == matchCount)
                .filter(prize -> {
                    if (matchCount == SECOND_PLACE.matchCount) {
                        return prize.isBonusMatch == isBonusMatch;
                    }
                    return true;
                }).findFirst().orElse(SIXTH_PLACE);
    }

    public static long calculateTotalPrize(List<Prize> prizes) {
        return prizes.stream()
                .mapToLong(prize -> prize.prizeAmount)
                .sum();
    }

    public static Map<Prize, Integer> count(List<Prize> prizes) {
        Map<Prize, Integer> prizeCount = new HashMap<>();
        Arrays.stream(Prize.values()).forEach(prize -> prizeCount.put(prize, Collections.frequency(prizes, prize)));
        return prizeCount;
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
