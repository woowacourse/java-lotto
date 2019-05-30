package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoGame {
    private final WinningLotto winningLotto;
    private final List<Lotto> lottos;

    public LottoGame(WinningLotto winningLotto, List<Lotto> lottos) {
        this.winningLotto = winningLotto;
        this.lottos = lottos;
    }

    public WinningResult play() {
        Map<LottoRank, Integer> result = initializeResult();

        for (Lotto lotto : lottos) {
            LottoRank rank = winningLotto.calculateRank(lotto);
            result.put(rank, result.get(rank) + 1);
        }
        return new WinningResult(result);
    }

    private Map<LottoRank, Integer> initializeResult() {
        Map<LottoRank, Integer> result = new HashMap<>();
        for (LottoRank lottoRank : LottoRank.values()) {
            result.put(lottoRank, 0);
        }
        return result;
    }
}
