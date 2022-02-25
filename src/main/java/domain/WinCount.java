package domain;

public class WinCount {

    private static final int SECOND_RANK_UNIT = 2;

    private final int count;
    private boolean bonus;

    public WinCount(final int count, final boolean bonus) {
        this.count = count;
        this.bonus = bonus;
    }

    public boolean isThirdRankCount() {
        return this.count == RankPrice.THIRD.getCount();
    }

    public WinCount convertToSecondRankCount() {
        return new WinCount(this.count, this.bonus);
    }

    public boolean isInRank() {
        return this.count >= RankPrice.FIFTH.getCount();
    }

    public RankPrice findRankPrice() {
        return RankPrice.findByCount(this.count, this.bonus);
    }
}
