package lotto.domain.lottos.rank;

import java.util.Arrays;

public enum LottoRank {
    SIXTH_PLACE(0, false, 0),
    FIFTH_PLACE(3, false, 5_000),
    FOURTH_PLACE(4, false, 50_000),
    THIRD_PLACE(5, false, 1_500_000),
    SECOND_PLACE(5, true, 30_000_000),
    FIRST_PLACE(6, false, 2_000_000_000);

    private final int matches;
    private final boolean matchBonusNumber;
    private final long reward;

    LottoRank(final int matches, final boolean matchBonusNumber, final long reward) {
        this.matches = matches;
        this.matchBonusNumber = matchBonusNumber;
        this.reward = reward;
    }

    public static LottoRank matchLottoRank(final int matchCount, final boolean matchBonusNumber) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.isMatchRank(matchCount, matchBonusNumber))
                .findFirst()
                .orElse(SIXTH_PLACE);
    }

    private boolean isMatchRank(int matchCount, boolean matchBonusNumber) {
        return this.matches == matchCount && this.matchBonusNumber == matchBonusNumber;
    }

    public int getMatches() {
        return matches;
    }

    public long getReward() {
        return reward;
    }
}