package lotto.domain.lottoresult;

import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketGroup;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> rankStatistic = new EnumMap<>(LottoRank.class);

    public LottoResult(WinningLotto winningLotto, LottoTicketGroup lottoTickets) {
        Arrays.stream(LottoRank.values())
                .forEach(rank -> rankStatistic.put(rank, 0));

        for (LottoTicket lottoTicket : lottoTickets) {
            LottoRank rank = winningLotto.checkLottoRank(lottoTicket);
            rankStatistic.compute(rank, (LottoRank key, Integer value) -> ++value);
        }
    }

    public int countOfRank(LottoRank lottoRank) {
        return rankStatistic.get(lottoRank);
    }

    public BigDecimal earningRate() {
        BigDecimal expense = new BigDecimal(
                LottoTicket.PRICE * rankStatistic.values().stream()
                .reduce(0, Integer::sum));
        BigDecimal rewards = new BigDecimal(
                rankStatistic.keySet().stream()
                .mapToInt(x -> x.getReward() * rankStatistic.get(x))
                .sum());

        return rewards.divide(expense, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
    }
}
