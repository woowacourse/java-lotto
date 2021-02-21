package lottogame.domain.dto;

import lottogame.domain.Money;
import lottogame.domain.Rank;

import java.util.*;

public class LottoResults {
    private final List<LottoResult> LottoResults;
    private final Map<Rank, Integer> result = new LinkedHashMap<>();
    private int totalPrizeMoney = 0;
    private float profit;

    public LottoResults(List<LottoResult> results) {
        LottoResults = new ArrayList<>(results);
    }

    public void makeStatistics(Money money) {
        Arrays.stream(Rank.values())
                .filter(rank -> !rank.isNotFound())
                .forEach(rank -> calculatePrize(rank));
        calculateProfit(money);
    }

    private void calculatePrize(Rank rank) {
        int count = (int) LottoResults
                .stream()
                .filter(lottoResult -> lottoResult.equals(rank))
                .count();
        totalPrizeMoney += (count * rank.getMoney());
        result.put(rank, count);
    }

    public Map<Rank, Integer> values() {
        return Collections.unmodifiableMap(result);
    }

    public void calculateProfit(Money money) {
        profit = money.divide(totalPrizeMoney);
    }

    public float getProfit() {
        return this.profit;
    }
}
