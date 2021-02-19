package lotto;

import java.util.List;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMachine {

    private final InputView inputView;

    private final OutputView outputView;

    public LottoMachine() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public Lottos purchase() {
        int purchaseCount = inputView.readPaymentAmount().getPurchaseCount();
        Lottos lottos = Lottos.makeLottos(purchaseCount);
        outputView.printPurchaseLottos(purchaseCount, lottos);
    
        return lottos;
    }
    
    public void seeResults(Lottos lottos) {
        List<Integer> lotto = inputView.readWinningLotto();
        LottoNumber bonusNumber = inputView.readBonusNumber();
    
        WinningLotto winningLotto = WinningLotto.of(lotto, bonusNumber);
        LottoStatisticResult result = Rank.match(lottos, winningLotto);
    
        outputView.printStatistics(result);
    }
}
