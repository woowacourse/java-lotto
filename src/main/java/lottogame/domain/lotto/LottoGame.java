package lottogame.domain.lotto;

import lottogame.domain.Money;
import lottogame.domain.Quantity;
import lottogame.domain.stats.LottoResults;

import java.util.List;

public class LottoGame {
    private final Lottos lottos;
    private final Money money;

    public LottoGame(Money money, Quantity manualQuantity) {
        LottoGenerator.generate();
        Quantity totalQuantity = money.calculateQuantity();
        List<Lotto> lottoGroup = LottoGenerator.makeLottos(totalQuantity.subtract(manualQuantity));
        this.lottos = new Lottos(lottoGroup);
        this.money = money;
    }

    public LottoGame(Lottos lottos, Money money) {
        this.lottos = lottos;
        this.money = money;
    }

    public LottoResults Results(WinningLotto winningLotto) {
        LottoResults lottoResults = lottos.matchLottos(winningLotto);
        return lottoResults;
    }

    public Lottos lottos() {
        return lottos;
    }

    public int shortage() {
        return money.calculateQuantity().value() - lottos.size();
    }

    public void addLottos(List<String> lottoNumbers) {
        lottos.add(lottoNumbers);
    }

    public boolean hasShortage() {
        return money.getMoney() != lottos.size();
    }
}
