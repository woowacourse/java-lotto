package domain;

import java.util.List;

public enum Rank {
    FIFTH(5, 3, 5000, false),
    FOURTH(4, 4, 50000, false),
    THIRD(3, 5, 1500000, false),
    SECOND(2, 5, 30000000, true),
    FIRST(1, 6, 2000000000, false);

    private static final double DEFAULT_TOTAL_INCOME = 0;

    private int rankNumber;
    private int criteria;
    private double reward;
    private boolean hitBonusBall;

    Rank(int rankNumber, int criteria, double reward, boolean hitBonusBall) {
        this.rankNumber = rankNumber;
        this.criteria = criteria;
        this.reward = reward;
        this.hitBonusBall = hitBonusBall;
    }

    public static double calculateAllResult(List<Result> results, HitResult hitResult) {
        for (Result result : results) {
            calculateResult(result, hitResult);
        }
        return calculateTotalIncome(hitResult);
    }

    private static double calculateTotalIncome(HitResult hitResult) {
        double totalIncome = DEFAULT_TOTAL_INCOME;
        for (Rank value : Rank.values()) {
            totalIncome += value.reward * hitResult.get(value);
        }
        return totalIncome;
    }

    private static void calculateResult(Result result, HitResult hitResult) {
        for (Rank value : Rank.values()) {
            judgeHit(result, value, hitResult);
        }
    }

    private static void judgeHit(Result result, Rank value, HitResult hitResult) {
        if (result.compare(value)) {
            hitResult.increase(value);
        }
    }

    public int getRankNumber() {
        return rankNumber;
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
