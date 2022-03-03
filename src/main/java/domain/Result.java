package domain;

public class Result {

    private final int hitCount;
    private final boolean isHitBonusBall;

    public Result(int hitCount, boolean isHitBonusBall) {
        this.hitCount = hitCount;
        this.isHitBonusBall = isHitBonusBall;
    }

    public boolean isWhatRank(Rank rank) {
        return this.hitCount == rank.getCriteria();
    }

    public boolean isSecondRank() {
        return Rank.isSecondRank(hitCount, isHitBonusBall);
    }
}