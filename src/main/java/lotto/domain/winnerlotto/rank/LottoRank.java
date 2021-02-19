package lotto.domain.winnerlotto.rank;

import java.util.Arrays;

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
        return Arrays.stream(LottoRank.values())
                .filter(rank -> isMatchRank(rank, matchCount, matchBonusNumber))
                .findFirst()
                .orElse(SIXTH_PLACE);
    }

    private static boolean isMatchRank(LottoRank rank, int matchCount, boolean matchBonusNumber) {
        if (matchCount == 5 && SECOND_PLACE.equals(rank)) {
            return matchBonusNumber;
        }

        return matchCount == rank.matches;
    }

    public int getMatches() {
        return matches;
    }

    public int getReward() {
        return reward;
    }
}