package lotto.domain.result;

import java.util.Objects;

public class LottoResult {
    private final int numberOfMatch;
    private final boolean isBonusMatch;

    public LottoResult(int numberOfMatch, boolean isBonusMatch) {
        this.numberOfMatch = numberOfMatch;
        this.isBonusMatch = isBonusMatch;
    }

    public boolean isEqualToMatchCount(int matchCount) {
        return this.numberOfMatch == matchCount;
    }

    public boolean isBonusMatch() {
        return isBonusMatch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResult that = (LottoResult) o;
        return numberOfMatch == that.numberOfMatch &&
                isBonusMatch == that.isBonusMatch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfMatch, isBonusMatch);
    }
}
