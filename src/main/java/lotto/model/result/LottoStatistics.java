package lotto.model.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lotto.model.money.Money;

public class LottoStatistics {

    private final Map<LottoRank, Long> lottoRankCountMap;

    public LottoStatistics(List<LottoRank> ranks) {
        this.lottoRankCountMap = ranks.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public long count(LottoRank rank) {
        if (lottoRankCountMap.get(rank) != null) {
            return lottoRankCountMap.get(rank);
        }
        return 0;
    }

    public double calculateEarningRates(Money money) {
        long sum = lottoRankCountMap.entrySet().stream()
                .mapToLong(x -> x.getKey().getPrize() * x.getValue())
                .sum();
        return (double) sum / money.getAmount();
    }
}
