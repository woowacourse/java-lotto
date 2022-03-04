package dto;

import domain.HitResult;
import domain.Rank;

public class RankDto {

    private final int rankNumber;
    private final int criteria;
    private final double reward;
    private final int hitCount;

    public RankDto(int rankNumber, int criteria, double reward, int hitCount) {
        this.rankNumber = rankNumber;
        this.criteria = criteria;
        this.reward = reward;
        this.hitCount = hitCount;
    }

    public static RankDto from(Rank rank, HitResult hitResult) {
        return new RankDto(rank.getRankNumber(), rank.getCriteria(), rank.getReward(), hitResult.get(rank));
    }

    public int getRankNumber() {
        return rankNumber;
    }

    public int getCriteria() {
        return criteria;
    }

    public int getReward() {
        return (int) reward;
    }

    public int getHitCount() {
        return hitCount;
    }
}
