package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.LottoGame;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import lotto.domain.LottoResults;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningNumbers;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    public void run() {
        Money purchaseMoney = new Money(InputView.requestPurchaseMoney());
        LottoGame lottoGame = new LottoGame();
        lottoGame.purchase(purchaseMoney, new LottoGenerator());
        ResultView.printLottos(lottoGame.getLottos());

        if (lottoGame.hasLottoTickets()) {
            WinningNumbers winningNumbers = requestWinningNumbers();
            LottoResults lottoResults = lottoGame.confirmWinnings(winningNumbers);
            ResultView.printResults(lottoResults);
        }
    }

    private WinningNumbers requestWinningNumbers() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (Integer number : InputView.requestWinningNumber()) {
            lottoNumbers.add(new LottoNumber(number));
        }
        Lotto winningLotto = new Lotto(lottoNumbers);
        LottoNumber bonusNumber = new LottoNumber(InputView.requestBonusNumber());

        return new WinningNumbers(winningLotto, bonusNumber);
    }
}
