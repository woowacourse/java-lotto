package domain;

public class Result {

    private static final int HIT_FIVE_RANK = 5;

    private final int hitCount;
    private final boolean isHitBonusBall;

    public Result(int hitCount, boolean isHitBonusBall) {
        this.hitCount = hitCount;
        this.isHitBonusBall = isHitBonusBall;
    }

    public boolean isWhatRank(Rank rank) {
        if (rank.getCriteria() == HIT_FIVE_RANK) {
            return this.hitCount == rank.getCriteria() && this.isHitBonusBall == rank.getHitBonusBall();
        }
        return this.hitCount == rank.getCriteria();
    }
}
