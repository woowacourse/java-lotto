package domain.result;

import domain.lotto.Lottos;
import domain.lotto.WinningLotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result {
    private final Lottos lottos;
    private final Map<LottoRank, Integer> results;

    public Result(Lottos lottos) {
        this.lottos = lottos;
        this.results = new HashMap<>();
    }

    public Map<LottoRank, Integer> findMatches(WinningLotto winningLotto) {
        List<LottoRank> lottoRanks = lottos.findMatches(winningLotto);
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
