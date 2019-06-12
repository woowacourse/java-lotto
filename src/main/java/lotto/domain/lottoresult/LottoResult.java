package lotto.domain.lottoresult;

import lotto.domain.lotto.LottoTicket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class LottoResult implements Iterable<RankStatistic> {
    private final List<RankStatistic> lottoStatistic;

    public LottoResult(List<LottoRank> ranks) {
        List<RankStatistic> statistics = Arrays.stream(LottoRank.values())
                .map(rank -> new RankStatistic(rank,
                        (int) ranks.stream()
                        .filter(x -> rank == x)
                        .count()
                ))
                .collect(Collectors.toList())
        ;

        Collections.reverse(statistics);
        lottoStatistic = Collections.unmodifiableList(statistics);
    }

    public BigDecimal getEarningRate() {
        BigDecimal expense = new BigDecimal(
                LottoTicket.PRICE * lottoStatistic.stream()
                        .mapToInt(RankStatistic::getCount)
                        .sum()
        );
        BigDecimal rewards = new BigDecimal(
                lottoStatistic.stream()
                        .mapToInt(RankStatistic::reward)
                        .sum()
        );

        return rewards.divide(expense, 3, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(100));
    }

    @Override
    public Iterator iterator() {
        return lottoStatistic.iterator();
    }
}
