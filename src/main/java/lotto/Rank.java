package lotto;

import java.util.Arrays;

public enum Rank {

    MATCH_ZERO_NUMBERS(0, 0),
    MATCH_THREE_NUMBERS(3, 5000),
    MATCH_FOUR_NUMBERS(4, 50000),
    MATCH_FIVE_NUMBERS(5, 1500000),
    MATCH_FIVE_AND_BONUS_NUMBERS(5, 30000000),
    MATCH_SIX_NUMBERS(6, 2000000000);

    private final int matchCount;
    private final int reward;

    Rank(final int matchCount, final int reward) {
        this.matchCount = matchCount;
        this.reward = reward;
    }

    public static Rank matchResult(int total) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchCount == total)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("해당 등수가 존재하지 않습니다."));
    }

    public double calculateTotalReward(Integer count) {
        return reward * count;
    }
}

