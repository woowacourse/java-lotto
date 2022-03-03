package dto;

import model.rank.Rank;

public class RankResultDto {
    private final Rank rank;
    private final int winningCount;

    public RankResultDto(final Rank rank, final int winningCount) {
        this.rank = rank;
        this.winningCount = winningCount;
    }

    public int getRankPrize() {
        return rank.getPrize();
    }

    public int getMatchCount() {
        return rank.getMatchCount();
    }

    public int getWinningCount() {
        return winningCount;
    }

    public Rank getRank() {
        return rank;
    }

}
