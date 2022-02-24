package domain;

import java.util.List;

public enum Rank {
    FIFTH(3, 5000, 0),
    FOURTH(4, 50000, 0),
    THIRD(5, 1500000, 0, false),
    SECOND(5, 30000000, 0, true),
    FIRST(6, 2000000000, 0);

    private static final int DEFAULT_TOTAL_INCOME = 0;

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

    public static double calculateAllResult(List<Result> results) {
        for (Result result : results) {
            calculateResult(result);
        }
        return calculateTotalIncome();
    }

    private static double calculateTotalIncome() {
        double totalIncome = DEFAULT_TOTAL_INCOME;
        for (Rank value : Rank.values()) {
            totalIncome += value.reward * value.hitCount;
        }
        return totalIncome;
    }

    private static void calculateResult(Result result) {
        for (Rank value : Rank.values()) {
            judgeHit(result, value);
        }
    }

    private static void judgeHit(Result result, Rank value) {
        if (result.compare(value)) {
            value.hitCount++;
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
