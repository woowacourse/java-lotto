package lotto.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public WinningResult match(WinningLotto winningLotto) {
        Map<LottoRank, Integer> result = initializeResult();

        for (Lotto lotto : lottos) {
            LottoRank rank = winningLotto.calculateRank(lotto);
            result.put(rank, result.get(rank) + 1);
        }
        result.remove(LottoRank.MISS);
        return new WinningResult(result);
    }

    private Map<LottoRank, Integer> initializeResult() {
        Map<LottoRank, Integer> result = new LinkedHashMap<>();
        for (LottoRank lottoRank : LottoRank.values()) {
            result.put(lottoRank, 0);
        }
        return result;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
