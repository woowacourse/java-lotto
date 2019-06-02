package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinStatistics {
    private static final int DEFAULT_VALUE_OF_RESULT = 0;

    private final Map<RankType, Integer> countOfResult = new HashMap<>();

    public WinStatistics(final List<LottoTicket> lottoTickets, final WinningLotto winningLotto) {
        for (RankType rankType : RankType.values()) {
            countOfResult.put(rankType, DEFAULT_VALUE_OF_RESULT);
        }

        makeResult(lottoTickets, winningLotto);
    }

    private void makeResult(final List<LottoTicket> lottoTickets, final WinningLotto winningLotto) {
        for (LottoTicket lottoTicket : lottoTickets) {
            int matchingCount = lottoTicket.getMatchingCount(winningLotto);
            boolean bonusBall = lottoTicket.matchesBonusBall(winningLotto);

            int count = countOfResult.get(RankType.valueOf(matchingCount, bonusBall));
            countOfResult.put(RankType.valueOf(matchingCount, bonusBall), count + 1);
        }
    }

    public Map<RankType, Integer> getCountOfResult() {
        return this.countOfResult;
    }

}
