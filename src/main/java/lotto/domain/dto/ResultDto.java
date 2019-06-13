package lotto.domain.dto;

import lotto.domain.Rank;

import java.util.Map;

public class ResultDto {
    private final Map<Rank, Integer> winners;
    private final double yield;
    private final long winPrize;

    public ResultDto(Map<Rank, Integer> winners, double yield, long winPrize) {
        this.winners = winners;
        this.yield = yield;
        this.winPrize = winPrize;
    }

    public int getWinnersCountByRank(Rank rank) {
        return winners.get(rank);
    }

    public double getYield() {
        return yield;
    }

    public long getWinPrize() {
        return winPrize;
    }
}
