package domain.result;

import domain.lotto.LottoTickets;
import domain.lotto.WinningLotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private final LottoTickets lottoTickets;
    private final Map<LottoRank, Integer> results;

    public Result(LottoTickets lottoTickets) {
        this.lottoTickets = lottoTickets;
        this.results = new HashMap<>();
    }

    public Map<LottoRank, Integer> findMatches(WinningLotto winningLotto) {
        List<LottoRank> lottoRanks = lottoTickets.findMatches(winningLotto);
        for (LottoRank lottoRank : lottoRanks) {
            putResult(lottoRank);
        }
        return results;
    }

    private void putResult(final LottoRank lottoRank) {
        if (!results.containsKey(lottoRank)) {
            results.put(lottoRank, 1);
            return;
        }
        results.put(lottoRank, results.get(lottoRank) + 1);
    }
}
