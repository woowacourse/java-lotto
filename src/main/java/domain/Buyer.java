package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Buyer {

    private final List<Lotto> lottos;

    public Buyer(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public LottoStatistics countMatchedRanks(WinningLotto winningLotto) {
        Map<LottoRank, Integer> result = LottoRank.createLottoRankCounter();

        for (Lotto lotto : lottos) {
            LottoRank matchedLotto = lotto.checkLottoRank(winningLotto);
            result.put(matchedLotto, result.get(matchedLotto) + 1);
        }
        return new LottoStatistics(result);
    }
}
