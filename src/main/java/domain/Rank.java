package domain;

import java.util.List;

public enum Rank {
    FIFTH(5, 3, 5000, 0, false),
    FOURTH(4, 4, 50000, 0, false),
    THIRD(3, 5, 1500000, 0, false),
    SECOND(2, 5, 30000000, 0, true),
    FIRST(1, 6, 2000000000, 0, false);

    private static final double DEFAULT_TOTAL_INCOME = 0;

    private int rankNumber;
    private int criteria;
    private double reward;
    private int hitCount;
    private boolean hitBonusBall;

    Rank(int rankNumber, int criteria, double reward, int hitCount, boolean hitBonusBall) {
        this.rankNumber = rankNumber;
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

    public int getRankNumber() {
        return rankNumber;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getCriteria() {
        return criteria;
    }

    public double getReward() {
        return reward;
    }

    public boolean getHitBonusBall() {
        return hitBonusBall;
    }
}
