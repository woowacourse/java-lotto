package domain;

import java.util.Arrays;

public enum Rewards {
    FIRST_REWARD(6, 0, 2000000000, 0),
    SECOND_REWARD(5, 1, 30000000, 0),
    THIRD_REWARD(5, 0, 1500000, 0),
    FORTH_REWARD(4, 0, 50000, 0),
    FIFTH_REWARD(3, 0, 5000, 0),
    NO_REWARD(0, 0, 0, 0);

    private final int winningCount;
    private final int bonusCount;
    private final int reward;
    private int count;

    Rewards(int winningCount, int bonusCount, int reward, int count) {
        this.winningCount = winningCount;
        this.bonusCount = bonusCount;
        this.reward = reward;
        this.count = count;
    }

    public static Rewards findReward(int winningCount, int bonusCount) {
        return Arrays.stream(Rewards.values())
                .filter(reward -> findRanking(reward, winningCount, bonusCount))
                .findFirst()
                .orElseThrow();
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

    private static boolean findRanking(Rewards reward, int winningCount, int bonusCount) {
        return (reward.winningCount == winningCount && reward.bonusCount == bonusCount);
    }
}
