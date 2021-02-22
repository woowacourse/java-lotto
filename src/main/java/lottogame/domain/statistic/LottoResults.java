package lottogame.domain.statistic;

import lottogame.domain.Money;
import lottogame.domain.Rank;
import lottogame.domain.dto.LottoResultDto;

import java.util.*;

public class LottoResults {
    private final List<LottoResult> LottoResults;
    private int totalPrizeMoney = 0;

    public LottoResults(List<LottoResult> results) {
        LottoResults = new ArrayList<>(results);
    }

    public LottoResultDto makeStatistics(Money money) {
        Map<Rank, Integer> result = new LinkedHashMap<>();
        Arrays.stream(Rank.values())
                .filter(rank -> !rank.isNotFound())
                .forEach(rank -> calculatePrize(rank, result));
        float profit = calculateProfit(money);
        return new LottoResultDto(result, profit);
    }

    private void calculatePrize(Rank rank, Map<Rank, Integer> result) {
        int count = (int) LottoResults
                .stream()
                .filter(lottoResult -> lottoResult.equals(rank))
                .count();
        totalPrizeMoney += (count * rank.getMoney());
        result.put(rank, count);
    }

    private float calculateProfit(Money money) {
        return money.divide(totalPrizeMoney);
    }
}
