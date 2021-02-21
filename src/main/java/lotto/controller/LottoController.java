package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Scanner;

public class LottoController {
    public void tryLotto(Scanner scanner) {
        Money money = new Money(InputView.takeMoneyInput(scanner));

        Lottos lottos = buy(money);
        OutputView.showLottos(lottos);

        List<Rank> results = lottos.getResults(getWinningLotto(scanner));

        OutputView.showResultStatistics(new LottoStatistics(results, money));
    }

    private Lottos buy(Money money) {
        int numberOfLottoToBuy = money.calculateAffordableNumberOfLotto();
        AutomaticLottoGenerator automaticLottoGenerator = new AutomaticLottoGenerator();

        return automaticLottoGenerator.createLottos(numberOfLottoToBuy);
    }

    private WinningLotto getWinningLotto(Scanner scanner) {
        int[] winningNumbers = InputView.takeWinningNumbersInput(scanner);
        int bonusNumber = InputView.takeBonusNumberInput(scanner);
        return new WinningLotto(new ManualLottoGenerator(winningNumbers).createLotto(), new LottoNumber(bonusNumber));
    }
}
