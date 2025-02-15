package domain;

import static java.util.Collections.unmodifiableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return unmodifiableList(lottos);
    }

    public WinningResult calculateWinning(WinningLotto winningLotto, Money purchaseLottoMoney) {
        Map<Rank, Integer> winningResult = new HashMap<>();
        for (Lotto purchaseLotto : lottos) {
            int matchCount = winningLotto.calculateMatchCount(purchaseLotto);
            boolean isMatchBonusNumber = winningLotto.containsBonusNumber(purchaseLotto);
            Rank rank = Rank.findRank(matchCount, isMatchBonusNumber);
            winningResult.put(rank, winningResult.getOrDefault(rank, 0) + 1);
        }
        return new WinningResult(purchaseLottoMoney, winningResult);
    }
}
