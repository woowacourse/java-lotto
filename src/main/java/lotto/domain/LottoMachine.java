package lotto.domain;

public class LottoMachine {
    
    public Lottos purchase(PaymentAmount paymentAmount) {
        return LottosFactory.makeLottos(paymentAmount);
    }
    
    public LottoStatisticResult seeResults(Lottos lottos, WinningLotto winningLotto) {
        return lottos.retrieveResults(winningLotto);
    }
}
