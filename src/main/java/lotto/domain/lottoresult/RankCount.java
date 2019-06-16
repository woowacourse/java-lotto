package lotto.domain.lottoresult;

import java.util.Objects;

public class RankCount {
    private final LottoRank rank;
    private final int count;

    public RankCount(LottoRank rank, int count) {
        this.rank = rank;
        this.count = count;
    }

    public boolean isWinningResult() {
        return !rank.equals(LottoRank.FAIL);
    }

    public int getCount() {
        return count;
    }

    public LottoRank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RankCount rankCount = (RankCount) o;
        return count == rankCount.count &&
                rank.equals(rankCount.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, count);
    }

    @Override
    public String toString() {
        return rank + " & " + count;
    }
}
