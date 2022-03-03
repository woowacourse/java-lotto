package domain;

public class CorrectNumber {

    private final int count;
    private final boolean secondRank;

    private CorrectNumber(final int count, final boolean secondRank) {
        this.count = count;
        this.secondRank = secondRank;
    }

    public static CorrectNumber getCorrectNumber(final int count, final boolean bonus) {
        return new CorrectNumber(count, RankPrize.isSecondRank(count, bonus));
    }

    public boolean isInRank() {
        return RankPrize.isInRank(count);
    }

    public RankPrize findRankPrize() {
        return RankPrize.findByCount(this.count, this.secondRank);
    }
}
