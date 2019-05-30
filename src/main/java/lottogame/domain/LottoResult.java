package lottogame.domain;

import java.util.Map;

public class LottoResult {
    private Map<Rank, Integer> result;

    LottoResult(Map<Rank, Integer> result) {
        this.result = result;
        this.result.remove(Rank.MISS);
    }

    public long getRateOfLotto(Money money) {
        double profits = 0;

        for (Rank rank : result.keySet()) {
            profits += rank.sumPrizeOf(result.get(rank));
        }

        return money.rateOf(profits);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Rank rank : result.keySet()) {
            stringBuilder.append(rank).append(result.get(rank)).append("개\n");
        }
        return stringBuilder.toString();
    }
}
