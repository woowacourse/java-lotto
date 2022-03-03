package dto;

import domain.Rank;

public class RankDto {

    private final int rankNumber;
    private final int criteria;
    private final int reward;
    private final int hitCount;

    private RankDto(int rankNumber, int criteria, int reward, int hitCount) {
        this.rankNumber = rankNumber;
        this.criteria = criteria;
        this.reward = reward;
        this.hitCount = hitCount;
    }

    static RankDto from(Rank rank, int hitCount) {
        return new RankDto(rank.getRankNumber(), rank.getCriteria(), rank.getReward(), hitCount);
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

    public int getHitCount() {
        return hitCount;
    }
}