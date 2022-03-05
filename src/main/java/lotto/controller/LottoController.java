package lotto.controller;

import java.util.List;
import lotto.domain.LottoGame;
import lotto.domain.generator.Generator;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningNumbers;
import lotto.dto.ResponsePurchaseResults;
import lotto.dto.ResponsePurchaseResultsDto;
import lotto.dto.ResponseWinningResultsDto;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    private final LottoGame lottoGame = new LottoGame();

    public void run() {
        Money purchaseMoney = InputView.requestPurchaseMoney().getMoney();
        ResultView.printPurchaseLottos(purchase(purchaseMoney));
        ResultView.printResults(confirmWinnings());
    }

    private ResponsePurchaseResultsDto purchase(Money money) {
        ResponsePurchaseResults manualResults = purchaseManual(money);
        money = manualResults.getChanges();
        ResponsePurchaseResults autoResults = purchaseAuto(money, new LottoGenerator());
        return new ResponsePurchaseResultsDto(manualResults, autoResults);
    }

    private ResponsePurchaseResults purchaseManual(Money money) {
        List<Lotto> manualLottos = InputView.requestManualLottoNumbers().getManualLottos();
        return lottoGame.purchaseManual(manualLottos, money);
    }

    private ResponsePurchaseResults purchaseAuto(Money money, Generator generator) {
        return lottoGame.purchaseAuto(generator, money);
    }

    private ResponseWinningResultsDto confirmWinnings() {
        WinningNumbers winningNumbers = InputView.requestWinningNumber();
        return lottoGame.confirmWinnings(winningNumbers);
    }
}
