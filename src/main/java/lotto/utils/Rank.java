package lotto.utils;

import java.util.Arrays;
import java.util.List;
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

        CAN_BE_WHATEVER(true, false),
        MUST_BE_FALSE(false),
        MUST_BE_TRUE(true);

        private final List<Boolean> states;

        BonusMatched(final boolean state1, final boolean state2) {
            this.states = List.of(state1, state2);
        }

        BonusMatched(final boolean state) {
            this.states = List.of(state);
        }

        private boolean matches(final boolean bonusMatched) {
            return states.stream()
                    .anyMatch(state -> state == bonusMatched);
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

    public boolean isTrueThatBonusMatchMustBeTrue() {
        return bonusMatched.equals(BonusMatched.MUST_BE_TRUE);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }

}
