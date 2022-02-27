package lotto.controller.dto;

import lotto.domain.Rank;

public class RankDto {

    private final int prizeMoney;
    private final int count;
    private final boolean isBonus;
    private final long amount;

    private RankDto(Rank rank, long amount) {
        this.prizeMoney = rank.getPrizeMoney();
        this.count = rank.getCount();
        this.isBonus = rank.isBonus();
        this.amount = amount;
    }

    public static RankDto from(Rank rank, long amount) {
        return new RankDto(rank, amount);
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getCount() {
        return count;
    }

    public boolean isBonus() {
        return isBonus;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "RankDto{" +
                "prizeMoney=" + prizeMoney +
                ", count=" + count +
                ", isBonus=" + isBonus +
                ", amount=" + amount +
                '}';
    }
}
