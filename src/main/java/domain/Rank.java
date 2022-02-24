package domain;

<<<<<<< HEAD
import java.util.Arrays;

public enum Rank {
    FIFTH(5, 3, 5000),
    FOURTH(4, 4, 50000),
    THIRD(3, 5, 1500000, false),
    SECOND(2, 5, 30000000, true),
    FIRST(1, 6, 2000000000),
    NONE(0, 0, 0);

    private final int rankNumber;
    private final int criteria;
    private final int reward;
    private boolean hitBonusBall;

    Rank(int rankNumber, int criteria, int reward) {
        this.rankNumber = rankNumber;
        this.criteria = criteria;
        this.reward = reward;
    }

    Rank(int rankNumber, int criteria, int reward, boolean hitBonusBall) {
        this(rankNumber, criteria, reward);
        this.hitBonusBall = hitBonusBall;
    }

    public static Rank judgeResult(Result result) {
        return Arrays.stream(Rank.values())
                .filter(result::isWhatRank)
                .findFirst()
                .orElse(NONE);
    }

    public int getRankNumber() {
        return rankNumber;
=======
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
>>>>>>> 20bb1bf (feat: 2,3등은 보너스볼과 일치하는 숫자의 갯수를 기준으로, 나머지 등수는 일치하는 숫자의 갯수만으로 등수를 판정하는 로직 구현)
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
