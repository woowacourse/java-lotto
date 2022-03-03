package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.LottoGame;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningNumbers;
import lotto.dto.ResponsePurchaseResults;
import lotto.dto.ResponsePurchaseResultsDto;
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
        purchaseNothing();
    }

    private void purchase(Money money, LottoGame lottoGame) {
        List<Lotto> manualLottos = InputView.requestManualLottoNumbers().getManualLottos();
        ResponsePurchaseResults manualResults = lottoGame.purchaseManual(manualLottos, money);
        ResponsePurchaseResults autoResults = lottoGame.purchaseAuto(new LottoGenerator(), manualResults.getChanges());
        ResultView.printPurchaseLottos(new ResponsePurchaseResultsDto(manualResults, autoResults));

        WinningNumbers winningNumbers = InputView.requestWinningNumber();
        ResultView.printResults(lottoGame.confirmWinnings(winningNumbers));
    }

    private void purchaseNothing() {
        List<Lotto> emptyLottos = new ArrayList<>();
        Money emptyMoney = new Money(0);

        ResponsePurchaseResults manualResults = new ResponsePurchaseResults(emptyLottos, emptyMoney);
        ResponsePurchaseResults autoResults = new ResponsePurchaseResults(emptyLottos, emptyMoney);
        ResponsePurchaseResultsDto purchaseResultsDto =
                new ResponsePurchaseResultsDto(manualResults, autoResults);

        ResultView.printPurchaseLottos(purchaseResultsDto);
    }
}
