package domain;

import java.util.Arrays;

public enum Rewards {
    FIRST_REWARD(1, 6, 0, 2000000000,0 ),
    SECOND_REWARD(2,5, 1,  30000000, 0),
    THIRD_REWARD(3, 5, 0, 1500000, 0),
    FORTH_REWARD(4, 4, 0, 50000, 0),
    FIFTH_REWARD(5, 3, 0, 5000, 0),
    NO_REWARD(-1, 0, 0, 0, 0);


    private final int ranking;
    private final int winningCount;
    private final int bonusCount;
    private final int reward;
    private int count;

    Rewards(int ranking, int winningCount, int bonusCount, int reward, int count) {
        this.ranking= ranking;
        this.winningCount = winningCount;
        this.bonusCount = bonusCount;
        this.reward = reward;
        this.count = count;
    }

    public static Rewards findReward(int winningCount, int bonusCount) {
        return Arrays.stream(Rewards.values())
                .filter(x -> x.winningCount == winningCount && x.bonusCount == bonusCount)
                .findFirst()
                //.orElseGet(null);
                .get();
    }

    public static int getRanking(Rewards rewards) {
        return rewards.ranking;
    }

    public static void addCount(Rewards rewards) {
        rewards.count++;

    }

    public static int calculateYield(Rewards rewards) {
        return rewards.count * rewards.reward;
    }

    public static int getCount(Rewards rewards) {
        return rewards.count;
    }
}
