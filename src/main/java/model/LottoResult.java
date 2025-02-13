package model;

import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {

    private Map<RankType, Integer> result;

    public LottoResult() {
        result = new LinkedHashMap<>();
    }

    public void processLottoResult(Lottos lottos, WinningLotto winningLotto) {
        result = winningLotto.evaluateRank(lottos.getLottos());
    }
}
