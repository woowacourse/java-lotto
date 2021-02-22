package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    
    private final InputView inputView;
    
    private final OutputView outputView;
    
    private final LottoMachine lottoMachine;
    
    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoMachine = new LottoMachine();
    }
    
    public void run() {
        seeResults(purchase());
    }
    
    private Lottos purchase() {
        PaymentAmount paymentAmount = inputView.readPaymentAmount();
        
        Lottos lottos = lottoMachine.purchase(paymentAmount);
        
        outputView.printPurchaseLottos(paymentAmount, lottos);
        
        return lottos;
    }
    
    private void seeResults(Lottos lottos) {
        Lotto lotto = inputView.readWinningLotto();
        LottoNumber bonusNumber = inputView.readBonusNumber();
        
        WinningLotto winningLotto = WinningLotto.of(lotto, bonusNumber);
        LottoStatisticResult result = lottoMachine.seeResults(lottos, winningLotto);
        
        outputView.printStatistic(result);
    }
}
