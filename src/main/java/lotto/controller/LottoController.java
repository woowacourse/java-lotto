package lotto.controller;

import java.util.Collections;
import java.util.List;
import lotto.domain.LottoGame;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.LottoResults;
import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningNumbers;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    public void run() {
        Money purchaseMoney = InputView.requestPurchaseMoney();
        LottoGame lottoGame = new LottoGame();
        if (lottoGame.canBuyLotto(purchaseMoney)) {
            purchase(purchaseMoney, lottoGame);
            return;
        }
        purchaseNothing(purchaseMoney, lottoGame);
    }

    private void purchase(Money money, LottoGame lottoGame) {
        List<List<LottoNumber>> manualLottoNumbers = InputView.requestManualLottoNumbers();
        ResultView.printPurchaseLottos(lottoGame.purchase(money, manualLottoNumbers, new LottoGenerator()));

        WinningNumbers winningNumbers = InputView.requestWinningNumber();
        LottoResults lottoResults = lottoGame.confirmWinnings(winningNumbers);
        ResultView.printResults(lottoResults);
    }

    private void purchaseNothing(Money money, LottoGame lottoGame) {
        ResultView.printPurchaseLottos(lottoGame.purchase(money, Collections.EMPTY_LIST, new LottoGenerator()));
    }
}
