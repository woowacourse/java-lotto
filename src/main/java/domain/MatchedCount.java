package domain;

public class MatchedCount {

    private final int count;

    public MatchedCount(final int count) {
        this.count = count;
    }

    public boolean isInRank() {
        return this.count >= RankPrice.FIFTH.getCount();
    }

    public RankPrice findRankPrice(final boolean isBonusMatched) {
        return RankPrice.findByCount(this.count, isBonusMatched);
    }
}
