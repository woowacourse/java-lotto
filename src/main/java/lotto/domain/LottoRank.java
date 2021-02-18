package lotto.domain;

import java.util.Arrays;
import java.util.Comparator;

public enum LottoRank {
    FIRST_PLACE(6, 2_000_000_000),
    SECOND_PLACE(5, 30_000_000),
    THIRD_PLACE(5, 1_500_000),
    FOURTH_PLACE(4, 50_000),
    FIFTH_PLACE(3, 5_000),
    SIXTH_PLACE(0, 0);

    private final int matches;
    private final int reward;

    LottoRank(final int matches, final int reward) {
        this.matches = matches;
        this.reward = reward;
    }

    public static LottoRank matchLottoRank(final int matchCount, final boolean matchBonusNumber) {
        if (matchCount != 5) {
            return Arrays.stream(LottoRank.values())
                    .filter(value -> value.matches == matchCount)
                    .findFirst()
                    .orElse(SIXTH_PLACE);
        }

        if (matchBonusNumber) {
            return SECOND_PLACE;
        }
        return THIRD_PLACE;
    }

    public static Comparator<LottoRank> matchCountComparator = new Comparator<LottoRank>() {
        public int compare(LottoRank rank1, LottoRank rank2) {
            return rank1.getReward() - rank2.getReward();
        }
    };

    public int getMatches() {
        return matches;
    }

    public int getReward() {
        return reward;
    }
}