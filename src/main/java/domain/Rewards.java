package domain;

import java.util.Arrays;

public enum Rewards {
    FIRST_REWARD(6, 0, 2000000000),
    SECOND_REWARD(5, 1, 30000000),
    THIRD_REWARD(5, 0, 1500000),
    FORTH_REWARD(4, 0, 50000),
    FIFTH_REWARD(3, 0, 5000),
    NO_REWARD(0, 0, 0);

    private final int winningCount;
    private final int bonusCount;
    private final int reward;

    Rewards(int winningCount, int bonusCount, int reward) {
        this.winningCount = winningCount;
        this.bonusCount = bonusCount;
        this.reward = reward;
    }

    public static Rewards findReward(int winningCount, int bonusCount) {
        return Arrays.stream(Rewards.values())
                .filter(reward -> findRanking(reward, winningCount, bonusCount))
                .findFirst()
                .orElseThrow();
    }

    public int getReward() {
        return reward;
    }

    private static boolean findRanking(Rewards reward, int winningCount, int bonusCount) {
        return (reward.winningCount == winningCount && reward.bonusCount == bonusCount);
    }

}
