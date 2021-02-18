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

    public void purchase() {
        PayAmount payAmount = inputView.readPayAmount();

        int payCount = payAmount.getPayCount();
        outputView.printPurchasingLotto(payCount);

        Lottos lottos = new Lottos();
        lottos.makeLottos(payCount);
        outputView.printLottos(lottos);

        List<Integer> lotto = inputView.readWinningLotto();
        LottoNumber bonusNumber = inputView.readBonusNumber();

        WinningLotto winningLotto = WinningLotto.of(lotto, bonusNumber);
        LottoStatisticResult result = Rank.match(lottos, winningLotto);

        outputView.printStatisticResult(result);
    }
}
