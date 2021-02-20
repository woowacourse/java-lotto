package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoMachine {
    
    private final InputView inputView;
    
    private final OutputView outputView;
    
    public LottoMachine() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }
    
    public Lottos purchase() {
        PaymentAmount paymentAmount = inputView.readPaymentAmount();
        
        Lottos lottos = LottoFactory.makeLottos(paymentAmount);
        
        outputView.printPurchaseLottos(paymentAmount, lottos);
        
        return lottos;
    }
    
    public void seeResults(Lottos lottos) {
        List<Integer> lotto = inputView.readWinningLotto();
        LottoNumber bonusNumber = inputView.readBonusNumber();
        WinningLotto winningLotto = WinningLotto.of(lotto, bonusNumber);
        
        LottoStatisticResult result = lottos.retrieveResults(winningLotto);
        
        outputView.printStatistic(result);
    }
}
