package lotto.domain.winning;

import java.util.Arrays;
import java.util.Optional;

public enum Rank {

    FIFTH_GRADE(3, false, false, 5_000L),
    FOURTH_GRADE(4, false, false, 50_000L),
    THIRD_GRADE(5, true, false, 1_500_000L),
    SECOND_GRADE(5, true, true, 30_000_000L),
    FIRST_GRADE(6, false, false, 2_000_000_000L);

    private final int matchCount;
    private final boolean bonusRequired;
    private final boolean bonusMatched;
    private final long prizeMoney;

    Rank(final int matchCount, final boolean bonusRequired, final boolean bonusMatched, final long prizeMoney) {
        this.matchCount = matchCount;
        this.bonusRequired = bonusRequired;
        this.bonusMatched = bonusMatched;
        this.prizeMoney = prizeMoney;
    }

    public static Optional<Rank> of(final int matchCount, final boolean bonusMatched) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matches(matchCount, bonusMatched))
                .findAny();
    }

    private boolean matches(final int matchCount, final boolean bonusMatched) {
        return matchesCount(matchCount) && matchesBonus(bonusMatched);
    }

    private boolean matchesCount(final int matchCount) {
        return matchCount == this.matchCount;
    }

    private boolean matchesBonus(final boolean bonusMatched) {
        if (this.bonusRequired) {
            return bonusMatched == this.bonusMatched;
        }
        return true;
    }


    public int getMatchCount() {
        return matchCount;
    }

    public boolean getBonusMatched() {
        return bonusMatched;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }

}
