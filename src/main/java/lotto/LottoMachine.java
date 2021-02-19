package lotto;

import lotto.domain.LottoNumber;
import lotto.domain.LottoStatisticResult;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
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
        int purchaseCount = inputView.readPaymentAmount()
                                     .getPurchaseCount();
        
        Lottos lottos = Lottos.makeLottos(purchaseCount);
        
        outputView.printPurchaseLottos(purchaseCount, lottos);
        
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
