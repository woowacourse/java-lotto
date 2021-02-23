package lotto.domain.rating;

import java.util.Objects;

public class RatingResult {

    private static final int SECOND_MATCH_COUNT = 5;
    private final int matchCount;
    private boolean hasBonusBall = false;

    public RatingResult(final int matchCount, final boolean hasBonusBall) {
        this.matchCount = matchCount;

        if (matchCount == SECOND_MATCH_COUNT) {
            this.hasBonusBall = hasBonusBall;
        }
    }

    public int getMatchCount() {
        return matchCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RatingResult that = (RatingResult) o;
        return matchCount == that.matchCount && hasBonusBall == that.hasBonusBall;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchCount, hasBonusBall);
    }
}
