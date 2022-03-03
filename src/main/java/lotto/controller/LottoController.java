package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.LottoGame;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningNumbers;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    public void run() {
        LottoGame lottoGame = new LottoGame();

        Money purchaseMoney = InputView.requestPurchaseMoney().getMoney();
        if (lottoGame.canBuyLotto(purchaseMoney)) {
            purchase(purchaseMoney, lottoGame);
            return;
        }
        purchaseNothing(purchaseMoney, lottoGame);
    }

    private void purchase(Money money, LottoGame lottoGame) {
        List<Lotto> manualLottos = InputView.requestManualLottoNumbers().getManualLottos();
        ResultView.printPurchaseLottos(lottoGame.purchase(money, manualLottos, new LottoGenerator()));

        WinningNumbers winningNumbers = InputView.requestWinningNumber();
        ResultView.printResults(lottoGame.confirmWinnings(winningNumbers));
    }

    private void purchaseNothing(Money money, LottoGame lottoGame) {
        ResultView.printPurchaseLottos(lottoGame.purchase(money, new ArrayList<>(), new LottoGenerator()));
    }
}
