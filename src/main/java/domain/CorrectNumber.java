package domain;

public class CorrectNumber {

    private static final int RANK_SECOND = RankPrize.SECOND.getCount();
    private final int count;
    private final boolean secondRank;

    private CorrectNumber(final int count, final boolean secondRank) {
        this.count = count;
        this.secondRank = secondRank;
    }

    public static CorrectNumber getCorrectNumber(final int count, final boolean bonus) {
        return new CorrectNumber(count, isSecondRank(count, bonus));
    }

    private static boolean isSecondRank(final int count, final boolean bonus) {
        return (count == RANK_SECOND) && bonus;
    }

    public boolean isInRank() {
        return this.count >= RankPrize.FIFTH.getCount();
    }

    public RankPrize findRankPrize() {
        return RankPrize.findByCount(this.count, this.secondRank);
    }
}
