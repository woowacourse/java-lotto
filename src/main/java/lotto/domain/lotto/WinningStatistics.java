package lotto.domain.lotto;

import lotto.domain.rank.Ranks;

public class WinningStatistics {

    private final Ranks ranks;
    private final Yield yield;

    public WinningStatistics(Ranks ranks, Yield yield) {
        this.ranks = new Ranks(ranks.unwrap());
        this.yield = new Yield(yield.unwrap());
    }

    public Ranks getRanks() {
        return new Ranks(ranks);
    }

    public Yield getYield() {
        return new Yield(yield);
    }
}