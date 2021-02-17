package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST_PLACE(6, 2000000000),
    SECOND_PLACE(5, 30000000),
    THIRD_PLACE(5, 1500000),
    FOURTH_PLACE(4, 50000),
    FIFTH_PLACE(3, 5000),
    SIXTH_PLACE(0, 0);

    private int matches;
    private int reward;

    LottoRank(int matches, int reward) {
        this.matches = matches;
        this.reward = reward;
    }

    public static LottoRank matchLottoRank(int matchCount, boolean matchBonusNumber) {
        if (matchCount < 3) {
            return SIXTH_PLACE;
        }

        if (matchCount != 5) {
            return Arrays.stream(LottoRank.values())
                    .filter(value -> value.matches == matchCount)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }

        if (matchBonusNumber) {
            return SECOND_PLACE;
        }
        return THIRD_PLACE;
    }

    public int getMatches() {
        return matches;
    }

    public int getReward() {
        return reward;
    }
}