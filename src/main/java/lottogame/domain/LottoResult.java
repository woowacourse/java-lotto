package lottogame.domain;

import java.util.Map;

public class LottoResult {
    private Map<Rank, Integer> result;

    LottoResult(Map<Rank, Integer> result) {
        this.result = result;
        this.result.remove(Rank.MISS);
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }

    public long getRateOfLotto(Money money) {
        double profits = 0;

        for (Rank rank : result.keySet()) {
            profits += rank.sumPrizeOf(result.get(rank));
        }

        return money.rateOf(profits);
    }

    public int getNumberOfMatch(Rank rank) {
        return result.get(rank);
    }
}
