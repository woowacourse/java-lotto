package lotto.domain.lottoresult;

import lotto.domain.Rank;
import lotto.domain.lottoticket.LottoTickets;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private Map<Rank, Integer> results;

    private LottoResult() {
        results = initResults();
    }

    private static Map<Rank, Integer> initResults() {
        Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            rankCounts.put(rank, 0);
        }
        return rankCounts;
    }

    public static LottoResult of(LottoTickets lottoTickets, WinningLotto winningLotto) {
        return lottoTickets.countRanksWith(winningLotto, new LottoResult());
    }

    public static LottoResult getInitialInstance() {
        return new LottoResult();
    }

    public long sumAllPrize() {
        long sumOfPrize = 0;
        for (Rank rank : results.keySet()) {
            sumOfPrize += rank.calculatePrize(results.get(rank));
        }
        return sumOfPrize;
    }

    public Integer getCountsBy(Rank key) {
        return results.get(key);
    }

    public void increaseOneCountOn(Rank rank) {
        results.put(rank, results.get(rank) + 1);
    }
}
