package domain;

public class MatchedCount {

    private final int count;

    public MatchedCount(final int count) {
        this.count = count;
    }

    public boolean isInRank() {
        return this.count >= RankPrize.FIFTH.getCount();
    }

    public RankPrize findRankPrice(final boolean isBonusMatched) {
        return RankPrize.findByCount(this.count, isBonusMatched);
    }
}
