package lotto.service;

import static lotto.domain.MatchRank.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lotto.domain.Money;
import lotto.domain.MatchRank;
import lotto.dto.MatchCountDto;
import lotto.dto.Profit;

public class LottoService {
    private final static double PROFIT_BENCHMARK = 1.0;

    public Map<MatchRank, Integer> convertToMap(List<MatchCountDto> matchCount) {
        Map<MatchRank, Integer> map = new HashMap<>();

        for (MatchCountDto dto : matchCount) {
            MatchRank statistic = getMatchRank(dto.matchCount(), dto.bonus());
            map.put(statistic, map.getOrDefault(statistic, 0) + 1);
        }

        return map;
    }

    public Profit calculateProfit(Map<MatchRank, Integer> map, Money money) {
        int sum = 0;

        for (MatchRank statistics : map.keySet()) {
            int matchMoney = statistics.getMoney();
            int count = map.get(statistics);
            sum += matchMoney * count;
        }

        double result = (double)sum / money.getMoney();
        return new Profit(result, isProfit(result));
    }

    private boolean isProfit(double result) {
        return result >= PROFIT_BENCHMARK;
    }
}
