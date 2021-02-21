package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoMachine lottoMachine = new LottoMachine();
        
        PaymentAmount paymentAmount = inputView.readPaymentAmount();
        Lottos lottos = lottoMachine.purchase(paymentAmount);
        outputView.printPurchaseLottos(paymentAmount, lottos);
        
        List<Integer> lotto = inputView.readWinningLotto();
        LottoNumber bonusNumber = inputView.readBonusNumber();
        WinningLotto winningLotto = WinningLotto.of(lotto, bonusNumber);
        LottoStatisticResult result = lottoMachine.seeResults(lottos, winningLotto);
        outputView.printStatistic(result);
    }
}
