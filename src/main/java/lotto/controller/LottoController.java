package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGame;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResults;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    public void run() {
        Money purchaseMoney = new Money(InputView.requestPurchaseMoney());
        LottoGame lottoGame = new LottoGame();
        lottoGame.purchase(purchaseMoney);
        ResultView.printLottos(lottoGame.getLottos());

        WinningLotto winningLotto = requestWinningNumbers();
        LottoResults lottoResults = lottoGame.confirmWinnings(winningLotto);
        ResultView.printResults(lottoResults);
    }

    private WinningLotto requestWinningNumbers() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (Integer number : InputView.requestWinningNumber()) {
            lottoNumbers.add(new LottoNumber(number));
        }
        LottoNumber bonusNumber = new LottoNumber(InputView.requestBonusNumber());

        return new WinningLotto(new Lotto(lottoNumbers), bonusNumber);
    }
}
