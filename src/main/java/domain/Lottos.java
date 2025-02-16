package domain;

import static java.util.Collections.unmodifiableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos, Money purchaseAmount) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return unmodifiableList(lottos);
    }

    public WinningResult calculateWinning(WinningLotto winningLotto) {
        Map<Rank, Integer> winningResult = new HashMap<>();
        for (Lotto purchaseLotto : lottos) {
            Rank rank = winningLotto.calculateRank(purchaseLotto);
            winningResult.put(rank, winningResult.getOrDefault(rank, 0) + 1);
        }
        return new WinningResult(LottoStore.calculatePurchaseAmount(lottos.size()), winningResult);
    }
}
