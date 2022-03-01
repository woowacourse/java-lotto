package lotto.controller;

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
        List<List<LottoNumber>> manualLottoNumbers = InputView.requestManualLottoNumbers();
        LottoGame lottoGame = new LottoGame();
        lottoGame.purchase(purchaseMoney, new LottoGenerator());
        ResultView.printLottos(lottoGame.getLottos());

        if (lottoGame.hasLottoTickets()) {
            WinningNumbers winningNumbers = InputView.requestWinningNumber();
            LottoResults lottoResults = lottoGame.confirmWinnings(winningNumbers);
            ResultView.printResults(lottoResults);
        }
    }
}
