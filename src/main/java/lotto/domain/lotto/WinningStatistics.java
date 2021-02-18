package lotto.domain.lotto;

import java.util.Map;
import lotto.domain.number.PayOut;
import lotto.domain.rank.Rank;
import lotto.domain.rank.Ranks;

public class WinningStatistics {

    private final Ranks ranks;
    private final double yield;

    public WinningStatistics(Map<Rank, Long> gameResult, PayOut payOut) {
        this.ranks = new Ranks(gameResult);
        this.yield = ranks.getWinningPrice() / payOut.toInt();
    }

    public Map<Rank, Long> toMap() {
        return ranks.toMap();
    }

    public double getYield() {
        return yield;
    }
}