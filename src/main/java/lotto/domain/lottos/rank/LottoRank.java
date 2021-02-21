package lotto.domain.lottos.rank;

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
                .filter(rank -> rank.isMatchRank(matchCount))
                .filter(rank -> !rank.equals(SECOND_PLACE) || matchBonusNumber)
                .findFirst()
                .orElse(SIXTH_PLACE);
    }

    private boolean isMatchRank(int matchCount) {
        return this.matches == matchCount;
    }

    public int getMatches() {
        return matches;
    }

    public int getReward() {
        return reward;
    }
}