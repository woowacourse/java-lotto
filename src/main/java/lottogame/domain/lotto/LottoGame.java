package lottogame.domain.lotto;

import lottogame.domain.Money;
import lottogame.domain.stats.LottoResults;

import java.util.List;

public class LottoGame {
    private final Lottos lottos;
    private final Money money;

    public LottoGame(Money money) {
        LottoGenerator.generate();
        List<Lotto> lottoGroup = LottoGenerator.makeLottos(money.lottoQuantity());
        this.lottos = new Lottos(lottoGroup);
        this.money = money;
    }

    public LottoGame(Lottos lottos, Money money) {
        this.lottos = lottos;
        this.money = money;
    }

    public LottoResults Results(WinningLotto winningLotto) {
        LottoResults lottoResults = lottos.matchLottos(winningLotto);
        lottoResults.matchedLottos();
        lottoResults.calculateProfit(money);
        return lottoResults;
    }

    public Lottos lottos() {
        return lottos;
    }
}
