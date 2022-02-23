package domain;

import java.util.List;

public enum Rank {
    FIFTH(3,5000, 0),
    FOURTH(4,50000, 0),
    THIRD(5,1500000, 0, false),
    SECOND(5,30000000, 0, true),
    FIRST(6,2000000000, 0);

    private int criteria;
    private int reward;
    private int hitCount;
    private boolean hitBonusBall;

    Rank(int criteria, int reward, int hitCount) {
        this.criteria = criteria;
        this.reward = reward;
        this.hitCount = hitCount;
    }

    Rank(int criteria, int reward, int hitCount, boolean hitBonusBall) {
        this.criteria = criteria;
        this.reward = reward;
        this.hitCount = hitCount;
        this.hitBonusBall = hitBonusBall;
    }

    private static void hit(Rank rank) {
        rank.hitCount++;
    }

    public static void calculateResult(Result result) {
        for (Rank value : Rank.values()) {
            if (result.compare(value)) {
                hit(value);
            }
        }
    }

    public static void calculateAllResult(List<Result> results) {
        for (Result result : results) {
            calculateResult(result);
        }
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getCriteria() {
        return criteria;
    }

    public int getReward() {
        return reward;
    }

    public boolean getHitBonusBall() {
        return hitBonusBall;
    }
}
