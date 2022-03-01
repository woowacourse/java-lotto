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
        int inputMoney = InputView.requestPurchaseMoney();
        // 수동 개수 입력
        int manualCount = InputView.requestManualCount(inputMoney);
        LottoGame lottoGame = new LottoGame(new Money(inputMoney));
        List<List<Integer>> manualNumbers = InputView.requestManualNumbers(manualCount);
        lottoGame.purchase(manualNumbers);

        ResultView.printLottos(lottoGame.getLottos(), manualCount);

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
