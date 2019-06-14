package lotto.domain.lottoresult;

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
}
