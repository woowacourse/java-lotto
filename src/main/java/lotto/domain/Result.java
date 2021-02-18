package lotto.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private final Map<Rank, Integer> resultMap;
    private final BigDecimal earningRate;

    public Result(WinningNumbers winningNumbers, List<LottoTicket> lottoTickets, int buyPrice) {
        BigDecimal prizePerRank = BigDecimal.ZERO;
        resultMap = new HashMap<>();

        for (LottoTicket lottoTicket : lottoTickets) {
            Rank rank = winningNumbers.getRank(lottoTicket);
            resultMap.put(rank, resultMap.getOrDefault(rank, 0) + 1);
        }

        for (Map.Entry<Rank, Integer> result : resultMap.entrySet()) {
            prizePerRank = prizePerRank.add(result.getKey().getPrize().multiply(BigDecimal.valueOf(result.getValue())));
        }

        this.earningRate = (prizePerRank.divide(BigDecimal.valueOf(buyPrice))).multiply(BigDecimal.valueOf(100));
    }

    public Map<Rank, Integer> getResultMap() {
        return resultMap;
    }

    public BigDecimal getEarningRate() {
        return earningRate;
    }

}
