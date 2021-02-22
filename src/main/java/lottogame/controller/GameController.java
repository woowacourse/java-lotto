package lottogame.controller;

import lottogame.domain.LottoMachine;
import lottogame.domain.dto.LottoResultDto;
import lottogame.domain.statistic.LottoResult;
import lottogame.domain.statistic.LottoResults;
import lottogame.domain.lotto.WinningLotto;
import lottogame.view.InputView;
import lottogame.domain.lotto.Lottos;
import lottogame.domain.Money;
import lottogame.view.OutputView;

import java.util.List;
import java.util.Scanner;

public class GameController {
    private static final InputView inputView = new InputView(new Scanner(System.in));

    public void run() {
        LottoMachine lottoMachine = new LottoMachine();
        Money money = new Money(inputView.inputMoney());
        int quantity = lottoMachine.purchaseQuantity(money);
        Lottos lottos = new Lottos(lottoMachine.buyLotto(quantity));
        OutputView.showLottos(lottos.numbersOfLottos());
        WinningLotto winningLotto = askWinningLotto();
        LottoResults lottoResults = matchLottos(lottos, winningLotto);
        LottoResultDto lottoResultDto = lottoResults.makeStatistics(money);
        OutputView.printResult(lottoResultDto);
    }

    private LottoResults matchLottos(Lottos lottos, WinningLotto winningLotto) {
        List<LottoResult> results = lottos.matchesLottos(winningLotto);
        return new LottoResults(results);
    }

    private WinningLotto askWinningLotto() {
        List<Integer> numbers = inputView.inputWinningLottoNumbers();
        int bonusBall = inputView.inputBonusNumber();
        return new WinningLotto(numbers, bonusBall);
    }
}
