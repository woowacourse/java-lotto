package lotto.model.result;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lotto.model.money.Money;

public class LottoStatistics {

    private final Map<LottoRank, Long> map;

    public LottoStatistics(List<LottoRank> ranks) {
        this.map = ranks.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public long count(LottoRank rank) {
        return map.get(rank);
    }

    public double calculateEarningRates(Money money) {
        long sum = map.entrySet().stream().mapToLong(x -> x.getKey().getPrize() * x.getValue()).sum();
        return (double) sum / money.getAmount();
    }
}
