package lotto.controller;

import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.ProfitRate;
import lotto.domain.RankCount;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {

    public void run() {
        Money money = inputMoney();
        Lottos lottos = buyLottos(money);
        WinningNumbers winningNumbers = inputWinningNumbers();
        printWinningStatistic(money, lottos, winningNumbers);
    }

    private Money inputMoney() {
        try {
            return Money.generateMoneyByString(InputView.inputMoney());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return inputMoney();
        }
    }

    private Lottos buyLottos(Money money) {
        Lottos lottos = Lottos.buyLottosByAuto(money);
        OutputView.printLottos(lottos);
        OutputView.printNewLine();
        return lottos;
    }

    private WinningNumbers inputWinningNumbers() {
        try {
            String winningLotto = InputView.inputWinningLotto();
            String bonusNumber = InputView.inputBonusNumber();
            return WinningNumbers.generateWinningNumbersByString(winningLotto, bonusNumber);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return inputWinningNumbers();
        }
    }

    private void printWinningStatistic(Money money, Lottos lottos, WinningNumbers winningNumbers) {
        OutputView.printNewLine();
        RankCount rankCount = new RankCount(lottos, winningNumbers);
        ProfitRate profitRate = new ProfitRate(rankCount.getTotalPrize(), money);
        OutputView.printWinningStatistic(rankCount, profitRate);
    }
}
