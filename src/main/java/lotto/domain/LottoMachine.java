package lotto.domain;

import lotto.domain.result.LottoStatisticResult;
import lotto.domain.result.WinningLotto;

public class LottoMachine {
    
    public Lottos purchase(PurchaseCount purchaseCount) {
        return LottosFactory.makeLottos(purchaseCount);
    }
    
    public LottoStatisticResult seeResults(Lottos lottos, WinningLotto winningLotto) {
        return lottos.retrieveResults(winningLotto);
    }
}
