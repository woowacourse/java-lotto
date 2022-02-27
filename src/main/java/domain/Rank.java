package domain;

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
