package lotto.service;

import static lotto.domain.MatchInfo.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lotto.domain.Cashier;
import lotto.domain.MatchInfo;
import lotto.domain.MatchCount;
import lotto.domain.Profit;

public class LottoService {
    private final static double PROFIT_BENCHMARK = 1.0;

    public Map<MatchInfo, Integer> convertToMap(List<MatchCount> matchCount) {
        Map<MatchInfo, Integer> map = new HashMap<>();

        for (MatchCount dto : matchCount) {
            MatchInfo statistic = getMatchInfo(dto.matchCount(), dto.bonus());
            map.put(statistic, map.getOrDefault(statistic, 0) + 1);
        }

        return map;
    }

    public Profit calculateProfit(Map<MatchInfo, Integer> map, Cashier cashier) {
        int sum = 0;

        for (MatchInfo statistics : map.keySet()) {
            int matchMoney = statistics.getMoney();
            int count = map.get(statistics);
            sum += matchMoney * count;
        }

        double result = (double)sum / cashier.money();
        return new Profit(result, isProfit(result));
    }

    private boolean isProfit(double result) {
        return result >= PROFIT_BENCHMARK;
    }
}
