package domain;

public class CorrectNumber {

    private final int count;
    private boolean bonus;

    public CorrectNumber(final int count, final boolean bonus) {
        this.count = count;
        this.bonus = bonus;
    }

    public boolean isThirdRankCount() {
        return this.count == RankPrice.THIRD.getCount();
    }

    public CorrectNumber convertToSecondRankCount() {
        return new CorrectNumber(this.count, true);
    }

    public boolean isInRank() {
        return this.count >= RankPrice.FIFTH.getCount();
    }

    public RankPrice findRankPrice() {
        return RankPrice.findByCount(this.count, this.bonus);
    }
}
