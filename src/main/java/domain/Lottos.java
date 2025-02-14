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

    public WinningResult calculateWinning(WinningLotto winningLotto) {
        Map<Rank, Integer> winningResult = new HashMap<>();
        for (Lotto purchaseLotto : lottos) {
            Rank rank = calculateRank(winningLotto, purchaseLotto);
            winningResult.put(rank, winningResult.getOrDefault(rank, 0) + 1);
        }
        Money purchaseLottoMoney = LottoStore.LOTTO_PRICE.multiply(lottos.size());
        return new WinningResult(purchaseLottoMoney, winningResult);
    }

    private Rank calculateRank(WinningLotto winningLotto, Lotto purchaseLotto) {
        int matchCount = winningLotto.calculateMatchCount(purchaseLotto);
        boolean isMatchBonusNumber = winningLotto.containsBonusNumber(purchaseLotto);
        return Rank.findRank(matchCount, isMatchBonusNumber);
    }


}
