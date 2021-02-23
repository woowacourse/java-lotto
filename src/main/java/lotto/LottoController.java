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
        final PurchaseCount purchaseCount = askPurchaseCount();
        final Lottos lottos = sellLottos(purchaseCount);
        final WinningLotto winningLotto = askWinningLotto();
        showLottoResults(lottos, winningLotto);
    }
    
    private PurchaseCount askPurchaseCount() {
        PaymentAmount paymentAmount = inputView.readPaymentAmount();
        return inputView.readPurchaseCount(paymentAmount);
    }
    
    private Lottos sellLottos(PurchaseCount purchaseCount) {
        Lottos manualLottos = inputView.readManualLotto(purchaseCount);
        Lottos automaticLottos = lottoMachine.purchase(purchaseCount);
        Lottos allLottos = Lottos.of(manualLottos, automaticLottos);
        
        outputView.printPurchaseLottos(purchaseCount, allLottos);
        
        return allLottos;
    }
    
    private WinningLotto askWinningLotto() {
        Lotto lotto = inputView.readWinningLotto();
        LottoNumber bonusNumber = inputView.readBonusNumber();
        return WinningLotto.of(lotto, bonusNumber);
    }
    
    private void showLottoResults(Lottos lottos, WinningLotto winningLotto) {
        LottoStatisticResult result = lottoMachine.seeResults(lottos, winningLotto);
        outputView.printStatistic(result);
    }
}
