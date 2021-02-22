package lottogame.domain.statistic;

import lottogame.domain.Rank;

import java.util.Objects;

public class LottoResult {
    private Rank rank;

    public LottoResult(int count, boolean bonus) {
        rank = Rank.of(count, bonus);
    }

    public boolean equals(Rank rank) {
        return rank.equals(this.rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResult that = (LottoResult) o;
        return rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank);
    }
}
