package lottogame.domain.stats;

import lottogame.domain.Money;
import lottogame.domain.Rank;

import java.util.*;

public class LottoResults {
    private List<LottoResult> resultGroup = new ArrayList<>();
    private Map<Rank, Integer> result;
    private int totalWinningAmount = 0;
    private float profit;

    public void add(LottoResult lottoResult) {
        resultGroup.add(lottoResult);
    }

    public void matchedLottos() {
        result = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            if (rank.isNotFound()) continue;
            int count = (int) resultGroup
                    .stream()
                    .filter(lottoResult -> lottoResult.equals(rank))
                    .count();
            totalWinningAmount += (count * rank.getMoney());
            result.put(rank, count);
        }
    }

    public Map<Rank, Integer> values() {
        return this.result;
    }

    public void calculateProfit(Money money) {
        profit = (float) totalWinningAmount / money.getMoney();
    }

    public float getProfit() {
        return this.profit;
    }
}
