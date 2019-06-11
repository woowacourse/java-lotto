package lotto.domain.lottoresult;

import lotto.domain.lotto.LottoTicket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class LottoResult implements Iterable<Map.Entry<LottoRank, Integer>>{
    private final Map<LottoRank, Integer> rankStatistic = new EnumMap<>(LottoRank.class);

    public LottoResult(List<LottoRank> ranks) {
        Arrays.stream(LottoRank.values())
                .forEach(rank -> rankStatistic.put(rank, 0));

        ranks.forEach(rank -> rankStatistic.compute(rank, (key, value) -> ++value));
    }

    public int countOfRank(LottoRank lottoRank) {
        return rankStatistic.get(lottoRank);
    }

    public BigDecimal getEarningRate() {
        BigDecimal expense = new BigDecimal(
                LottoTicket.PRICE * rankStatistic.values().stream()
                        .reduce(0, Integer::sum));
        BigDecimal rewards = new BigDecimal(
                rankStatistic.keySet().stream()
                        .mapToInt(x -> x.getReward() * rankStatistic.get(x))
                        .sum());

        return rewards.divide(expense, 3, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
    }

    @Override
    public String toString() {
        return String.valueOf(getEarningRate());
    }

    @Override
    public Iterator iterator() {
        return rankStatistic.entrySet().iterator();
    }
}
