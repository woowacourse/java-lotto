package lotto.dto;

public class RankResult {

    private final int matchCount;
    private final long reward;
    private final int rewardCount;

    public RankResult(int matchCount, long reward, int rewardCount) {
        this.matchCount = matchCount;
        this.reward = reward;
        this.rewardCount = rewardCount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getReward() {
        return reward;
    }

    public int getRewardCount() {
        return rewardCount;
    }
}
