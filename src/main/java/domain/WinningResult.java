package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinningResult {

    private final WinningNumber winningNumber;
    private final List<Lotto> lottos;

    public WinningResult(final WinningNumber winningNumber, final List<Lotto> lottos) {
        this.winningNumber = winningNumber;
        this.lottos = lottos;
    }

    public Map<LottoRank, Integer> countWinningResult() {
        final Map<LottoRank, Integer> winningResult = initialize();
        for (Lotto lotto : lottos) {
            final LottoRank lottoRank = winningNumber.calculateRankByLotto(lotto);
            winningResult.merge(lottoRank, 1, Integer::sum);
        }
        winningResult.remove(LottoRank.NONE);
        return winningResult;
    }

    private Map<LottoRank, Integer> initialize() {
        Map<LottoRank, Integer> result = new LinkedHashMap<>();
        final List<LottoRank> byAllWithoutNone = LottoRank.findByAllWithoutNone();
        for (LottoRank lottoRank : byAllWithoutNone) {
            result.put(lottoRank, 0);
        }
        return result;
    }
}
