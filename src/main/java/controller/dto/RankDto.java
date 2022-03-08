package controller.dto;

import domain.Rank;

public class RankDto {
    private final int count;
    private final int winningPrice;
    private final boolean hasBonusBall;

    public RankDto(int count, int winningPrice, boolean hasBonusBall) {
        this.count = count;
        this.winningPrice = winningPrice;
        this.hasBonusBall = hasBonusBall;
    }


    public static RankDto from(Rank rank) {
        return new RankDto(
                rank.getCount(),
                rank.getWinningPrice(),
                rank.hasBonusBall()
        );
    }

    public int getCount() {
        return count;
    }

    public int getWinningPrice() {
        return winningPrice;
    }

    public boolean hasBonusBall() {
        return hasBonusBall;
    }

}
