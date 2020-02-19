package lotto.domain;

import java.util.Objects;

public class LottoResult {
    private final int matchAmount;
    private final boolean isBonusMatch;

    public LottoResult(int matchAmount, boolean isBonusMatch) {
        this.matchAmount = matchAmount;
        this.isBonusMatch = isBonusMatch;
    }

    public int getMatchAmount() {
        return matchAmount;
    }

    public boolean isBonusMatch() {
        return isBonusMatch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResult that = (LottoResult) o;
        return matchAmount == that.matchAmount &&
                isBonusMatch == that.isBonusMatch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchAmount, isBonusMatch);
    }
}
