package lotto.service;

import static lotto.domain.MatchStatistics.*;

import java.util.HashMap;
import java.util.List;

import lotto.domain.Amount;
import lotto.domain.MatchStatistics;
import lotto.dto.MatchCountDto;
import lotto.dto.Profit;

public class LottoService {

    public HashMap<MatchStatistics, Integer> convertToMap(List<MatchCountDto> matchCount) {
        HashMap<MatchStatistics, Integer> map = new HashMap<>();

        for (MatchCountDto dto : matchCount) {
            MatchStatistics statistic = getMatchStatistics(dto.matchCount(), dto.bonus());
            map.put(statistic, map.getOrDefault(statistic, 0) + 1);
        }
        return map;
    }

    public Profit calculateProfit(HashMap<MatchStatistics, Integer> map, Amount amount) {
        int sum = 0;

        for (MatchStatistics statistics : map.keySet()) {
            int matchMoney = statistics.getMoney();
            int count = map.get(statistics);
            sum += matchMoney * count;
        }
        double result = (double)sum / amount.getMoney();
        return new Profit(result, isProfit(result));
    }

    private boolean isProfit(double result) {
        return result >= 1.0;
    }
}
