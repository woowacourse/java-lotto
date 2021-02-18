package lotto.domain;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private final Map<Rank, Integer> resultMap;
    private final EarningRate earningRate;

    private Result(Map<Rank, Integer> resultMap, EarningRate earningRate) {
        this.resultMap = resultMap;
        this.earningRate = earningRate;
    }

    public Result(WinningNumbers winningNumbers, List<LottoTicket> lottoTickets,
            BigInteger paymentAmount) {
        this.resultMap = getResultMap(winningNumbers, lottoTickets);
        this.earningRate = new EarningRate(getTotalPrize(), paymentAmount);
    }

    private static Map<Rank, Integer> getResultMap(WinningNumbers winningNumbers, List<LottoTicket> lottoTickets) {
        Map<Rank, Integer> resultMap;
        resultMap = new HashMap<>();

        for (LottoTicket lottoTicket : lottoTickets) {
            Rank rank = winningNumbers.getRank(lottoTicket);
            resultMap.put(rank, resultMap.getOrDefault(rank, 0) + 1);
        }
        return resultMap;
    }

    private BigInteger getTotalPrize() {
        BigInteger totalPrize = BigInteger.ZERO;

        for (Rank rank : resultMap.keySet()) {
            totalPrize = totalPrize.add(getPrizePerRank(rank));
        }

        return totalPrize;
    }

    private BigInteger getPrizePerRank(Rank rank) {
        return rank.getPrize().multiply(BigInteger.valueOf(resultMap.get(rank)));
    }

    public Map<Rank, Integer> getResultMap() {
        return resultMap;
    }

    public BigInteger getEarningRate() {
        return earningRate.toBigInteger();
    }
}
