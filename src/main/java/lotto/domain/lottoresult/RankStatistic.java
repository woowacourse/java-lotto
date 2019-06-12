package lotto.domain.lottoresult;

public class RankStatistic {
    private final LottoRank rank;
    private final int count;

    public RankStatistic(LottoRank rank, int count) {
        this.rank = rank;
        this.count = count;
    }

    public boolean isFailResult() {
        return rank.equals(LottoRank.FAIL);
    }

    public int reward() {
        return rank.getReward() * count;
    }

    public int getCount() {
        return count;
    }

    public LottoRank getRank() {
        return rank;
    }
}
