package dto;

import model.winning.Rank;

public class RankResultDto {
    private final Rank rank;
    private final int winningCount;

    public RankResultDto(Rank rank, int winningCount) {
        this.rank = rank;
        this.winningCount = winningCount;
    }

    public int getRankPrize() {
        return rank.getPrizeMoney();
    }

    public int getMatchCount() {
        return rank.getMatchCount();
    }

    public int getWinningCount() {
        return winningCount;
    }

}
