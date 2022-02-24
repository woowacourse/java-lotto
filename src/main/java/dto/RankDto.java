package dto;

import domain.Rank;

public class RankDto {

    private final int rankNumber;
    private final int criteria;
    private final int reward;
    private final int hitCount;

<<<<<<< HEAD
    private RankDto(int rankNumber, int criteria, int reward, int hitCount) {
=======
    public RankDto(int rankNumber, int criteria, int reward, int hitCount) {
>>>>>>> 2821995 (feat: 결과 출력 기능 구현)
        this.rankNumber = rankNumber;
        this.criteria = criteria;
        this.reward = reward;
        this.hitCount = hitCount;
    }

<<<<<<< HEAD
<<<<<<< HEAD
    static RankDto from(Rank rank, int hitCount) {
        return new RankDto(rank.getRankNumber(), rank.getCriteria(), rank.getReward(), hitCount);
=======
    public static RankDto from(Rank rank, int rankNumber){
=======
    public static RankDto from(Rank rank, int rankNumber) {
>>>>>>> 94c4d43 (style: 코드 포멧팅)
        return new RankDto(rankNumber, rank.getCriteria(), rank.getReward(), rank.getHitCount());
>>>>>>> 2821995 (feat: 결과 출력 기능 구현)
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
