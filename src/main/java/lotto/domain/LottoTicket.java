package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoTicket {

    private final List<Lotto> lottos;

    public LottoTicket(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Map<Integer, Integer> deriveMatchResults(WinningNumber winningNumber) {
        Map<Integer, Integer> matchResults = new HashMap<>();

        for (Lotto lotto : lottos) {
            int count = lotto.countWinningNumber(winningNumber);
            matchResults.put(count, matchResults.getOrDefault(count, 0) + 1);
        }

        return matchResults;
    }
}
