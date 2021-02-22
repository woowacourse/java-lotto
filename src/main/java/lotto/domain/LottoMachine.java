package lotto.domain;

public class LottoMachine {
    
    public Lottos purchase(PurchaseCount purchaseCount) {
        return LottosFactory.makeLottos(purchaseCount);
    }
    
    public LottoStatisticResult seeResults(Lottos lottos, WinningLotto winningLotto) {
        return lottos.retrieveResults(winningLotto);
    }
}
