package lotto.dto;

import lotto.domain.Rank;

public class RankDto {

    private final String matchStatus;
    private final int reward;

    public RankDto(String matchStatus, int reward) {
        this.matchStatus = matchStatus;
        this.reward = reward;
    }

    public static RankDto from(Rank rank) {
        return new RankDto(
                rank.getMatchStatus(),
                rank.getReward()
        );
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public int getReward() {
        return reward;
    }
}

