package lotto.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinStatistics {
    private static final int DEFAULT_VALUE_OF_RESULT = 0;
    private static final int PERCENT = 100;

    private final Map<RankType, Integer> countOfResult = new HashMap<>();
    private int profit = 0;

    public WinStatistics(final List<LottoTicket> lottoTickets, final WinningLotto winningLotto) {
        for (RankType rankType : RankType.values()) {
            countOfResult.put(rankType, DEFAULT_VALUE_OF_RESULT);
        }

        makeResult(lottoTickets, winningLotto);
        calculateProfit();
    }

    private void makeResult(final List<LottoTicket> lottoTickets, final WinningLotto winningLotto) {
        for (LottoTicket lottoTicket : lottoTickets) {
            int matchingCount = winningLotto.getMatchingCount(lottoTicket);
            boolean bonusBall = lottoTicket.matchesBonusBall(winningLotto);

            int count = countOfResult.get(RankType.valueOf(matchingCount, bonusBall));
            countOfResult.put(RankType.valueOf(matchingCount, bonusBall), count + 1);
        }
    }

    private void calculateProfit() {
        for (RankType rankType : countOfResult.keySet()) {
            this.profit += rankType.getPrize() * countOfResult.get(rankType);
        }
    }

    public double calculateProfitRate(int price) {
        return (double) this.profit / price * PERCENT;
    }

    public Map<RankType, Integer> getCountOfResult() {
        return Collections.unmodifiableMap(this.countOfResult);
    }
}
