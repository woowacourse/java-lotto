package domain;

public class MatchedCount {

    private static final int SECOND_RANK_UNIT = 2;

    private final int count;

    public MatchedCount(final int count) {
        this.count = count;
    }

    public boolean isThirdRankCount() {
        return this.count == RankPrice.THIRD.getCount();
    }

    public MatchedCount convertToSecondRankCount() {
        return new MatchedCount(this.count + SECOND_RANK_UNIT);
    }

    public boolean isInRank() {
        return this.count >= RankPrice.FIFTH.getCount();
    }

    public RankPrice findRankPrice() {
        return RankPrice.findByCount(this.count);
    }
}
