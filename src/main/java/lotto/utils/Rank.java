package lotto.utils;

import java.util.Arrays;
import java.util.Optional;

public enum Rank {

    FIFTH_GRADE(BonusMatched.CAN_BE_WHATEVER, 3, 5_000L),
    FOURTH_GRADE(BonusMatched.CAN_BE_WHATEVER, 4, 50_000L),
    THIRD_GRADE(BonusMatched.MUST_BE_FALSE, 5, 1_500_000L),
    SECOND_GRADE(BonusMatched.MUST_BE_TRUE, 5, 30_000_000L),
    FIRST_GRADE(BonusMatched.CAN_BE_WHATEVER, 6, 2_000_000_000L);

    private final BonusMatched bonusMatched;
    private final int matchCount;
    private final long prizeMoney;

    private enum BonusMatched {

        CAN_BE_WHATEVER(Optional.empty()),
        MUST_BE_FALSE(Optional.of(Boolean.FALSE)),
        MUST_BE_TRUE(Optional.of(Boolean.TRUE));

        private final Optional<Boolean> matchState;

        BonusMatched(final Optional<Boolean> matchState) {
            this.matchState = matchState;
        }

        private boolean matches(final boolean bonusMatched) {
            return matchState.map(matchState -> matchState == bonusMatched)
                    .orElse(true);
        }

        boolean getState() {
            return matchState.orElse(false);
        }

    }

    Rank(final BonusMatched bonusMatched, final int matchCount, final long prizeMoney) {
        this.bonusMatched = bonusMatched;
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
    }

    public static Optional<Rank> of(final int matchCount, final boolean bonusMatched) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .filter(rank -> rank.bonusMatched.matches(bonusMatched))
                .findAny();
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean getBonusMatched() {
        return bonusMatched.getState();
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }

}
