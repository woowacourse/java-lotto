package domain;

public class CorrectNumber {

    private final int count;
    private boolean bonus;

    public CorrectNumber(final int count, final boolean bonus) {
        this.count = count;
        this.bonus = bonus;
    }

    public boolean isThirdRankCount() {
        return this.count == RankPrize.THIRD.getCount();
    }

    public CorrectNumber convertToSecondRankCount() {
        return new CorrectNumber(this.count, true);
    }

    public boolean isInRank() {
        return this.count >= RankPrize.FIFTH.getCount();
    }

    public RankPrize findRankPrize() {
        return RankPrize.findByCount(this.count, this.bonus);
    }
}
