package lotto.domain.lottoresult;

import lotto.domain.lotto.LottoTicket;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoResult {
    private final Map<LottoRank, Integer> rankStatistic;

    public LottoResult(List<LottoRank> ranks) {
        rankStatistic = Collections.unmodifiableMap(ranks.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.reducing(0, rank -> 1, Integer::sum)
                ))
        );
    }

    public int getCountOf(LottoRank rank) {
        return rankStatistic.getOrDefault(rank, 0);
    }

    public BigDecimal getEarningRate() {
        return getRewards().divide(getExpense()).multiply(new BigDecimal(100));
    }

    private BigDecimal getExpense() {
        return rankStatistic.keySet().stream()
                .map(rank -> multiply(rankStatistic.get(rank), LottoTicket.PRICE))
                .reduce(new BigDecimal(0), BigDecimal::add)
                ;
    }

    private BigDecimal getRewards() {
        return rankStatistic.keySet().stream()
                .map(rank -> multiply(rank.getReward(), rankStatistic.get(rank)))
                .reduce(new BigDecimal(0), BigDecimal::add)
                ;
    }

    private BigDecimal multiply(int x, int y) {
        return new BigDecimal(x).multiply(new BigDecimal(y));
    }
}
