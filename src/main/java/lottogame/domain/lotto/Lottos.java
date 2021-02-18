package lottogame.domain.lotto;

import lottogame.domain.Money;
import lottogame.domain.stats.LottoResult;
import lottogame.domain.stats.LottoResults;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();
    private LottoResults lottoResults;

    public Lottos(List<Lotto> values) {
        lottos.addAll(values);
    }

    public List<Lotto> values() {
        return new ArrayList<>(lottos);
    }

    public LottoResults findMatchLottos(WinningLotto winningLotto, Money money) {
        matchLottos(winningLotto);
        lottoResults.matchedLottos();
        lottoResults.calculateProfit(money);
        return lottoResults;
    }

    private void matchLottos(WinningLotto winningLotto) {
        lottoResults = new LottoResults();
        for (Lotto lotto : lottos) {
            int count = lotto.match(winningLotto.values());
            boolean bonus = lotto.containsBonus(winningLotto);
            lottoResults.add(new LottoResult(count, bonus));
        }
    }
}
