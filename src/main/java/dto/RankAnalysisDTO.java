package dto;

import java.util.List;

public class RankAnalysisDTO {
    private final List<RankResult> rankResults;
    private final long reward;
    private final double earningRate;

    private RankAnalysisDTO(List<RankResult> rankResults, long reward, double earningRate) {
        this.rankResults = rankResults;
        this.reward = reward;
        this.earningRate = earningRate;
    }

    public static RankAnalysisDTO of(List<RankResult> results, long reward, double earningRate) {
        return new RankAnalysisDTO(results, reward, earningRate);
    }

    public List<RankResult> getRankResults() {
        return rankResults;
    }

    public long getReward() {
        return reward;
    }

    public double getEarningRate() {
        return earningRate;
    }
}
