package lotto.service;

import static lotto.domain.MatchStatistics.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lotto.domain.Amount;
import lotto.domain.MatchStatistics;
import lotto.domain.MatchCount;
import lotto.domain.Profit;

public class LottoService {
    private final static double PROFIT_BENCHMARK = 1.0;

    public Map<MatchStatistics, Integer> convertToMap(List<MatchCount> matchCount) {
        Map<MatchStatistics, Integer> map = new HashMap<>();

        for (MatchCount dto : matchCount) {
            MatchStatistics statistic = getMatchStatistics(dto.matchCount(), dto.bonus());
            map.put(statistic, map.getOrDefault(statistic, 0) + 1);
        }

        return map;
    }

    public Profit calculateProfit(Map<MatchStatistics, Integer> map, Amount amount) {
        int sum = 0;

        for (MatchStatistics statistics : map.keySet()) {
            int matchMoney = statistics.getMoney();
            int count = map.get(statistics);
            sum += matchMoney * count;
        }

        double result = (double)sum / amount.money();
        return new Profit(result, isProfit(result));
    }

    private boolean isProfit(double result) {
        return result >= PROFIT_BENCHMARK;
    }
}
