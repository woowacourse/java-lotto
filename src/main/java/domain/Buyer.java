package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Buyer {

    private final List<Lotto> lottos;

    public Buyer(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public String createResult() {
        String result = "";
        for (Lotto lotto : lottos) {
            result += lotto.toString() + "\n";
        }
        return result;
    }

    public Map<LottoRank, Integer> countMatchedRanks(WinningLotto winningLotto) {
        Map<LottoRank, Integer> result = LottoRank.createLottoRankCounter();

        for (Lotto lotto : lottos) {
            LottoRank matchedLotto = lotto.checkLottoRank(winningLotto);
            result.put(matchedLotto, result.get(matchedLotto) + 1);
        }
        return result;
    }
}
