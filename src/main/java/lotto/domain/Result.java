package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private final Map<Rank, Integer> resultMap;
    private final int earningRate;

    public Result(WinningNumbers winningNumbers, List<LottoTicket> lottoTickets, int buyPrice) {
        resultMap = new HashMap<>();
        for (LottoTicket lottoTicket : lottoTickets) {
            Rank rank = winningNumbers.getRank(lottoTicket);
            resultMap.put(rank, resultMap.getOrDefault(rank, 0) + 1);
        }

        int prizePerRank = 0;
        for (Map.Entry<Rank, Integer> result : resultMap.entrySet()) {
            prizePerRank += result.getKey().getPrize() * result.getValue();
        }

        this.earningRate = prizePerRank / buyPrice * 100;
    }

    public Map<Rank, Integer> getResultMap() {
        return resultMap;
    }

    public int getEarningRate() {
        return earningRate;
    }

}
