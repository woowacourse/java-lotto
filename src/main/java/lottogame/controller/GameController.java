package lottogame.controller;

import lottogame.domain.LottoMachine;
import lottogame.domain.dto.LottoResult;
import lottogame.domain.dto.LottoResults;
import lottogame.domain.lotto.WinningLotto;
import lottogame.view.InputView;
import lottogame.domain.lotto.Lottos;
import lottogame.domain.Money;
import lottogame.view.OutputView;

import java.util.List;

public class GameController {
    private LottoMachine lottoMachine;

    public GameController() {
        lottoMachine = new LottoMachine();
    }

    public void run() {
        Money money = new Money(InputView.inputMoney());
        int quantity = lottoMachine.purchaseQuantity(money);
        Lottos lottos = new Lottos(lottoMachine.buyLotto(quantity));
        OutputView.showLottos(lottos.numbersOfLottos());
        WinningLotto winningLotto = askWinningLotto();
        LottoResults lottoResults = matchLottos(lottos, winningLotto);
        lottoResults.makeStatistics(money);
        OutputView.printResult(lottoResults);
    }

    private LottoResults matchLottos(Lottos lottos, WinningLotto winningLotto) {
        List<LottoResult> results = lottos.matchesLottos(winningLotto);
        return new LottoResults(results);
    }

    private WinningLotto askWinningLotto() {
        List<Integer> numbers = InputView.inputWinningLottoNumbers();
        int bonusBall = InputView.inputBonusNumber();
        return new WinningLotto(numbers, bonusBall);
    }

}
