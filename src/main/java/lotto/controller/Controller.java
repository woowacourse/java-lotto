package lotto.controller;

import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.RankCounter;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {

    public void run() {
        Money money = getMoney();
        Lottos lottos = Lottos.buyLottosByAuto(money);
        OutputView.printLottos(lottos);

        WinningNumbers winningNumbers = getWinningNumbers();
        RankCounter rankCounter = new RankCounter(lottos, winningNumbers);
        OutputView.printWinningStatistic(money, rankCounter);
    }

    private Money getMoney() {
        try {
            int inputMoney = InputView.inputMoney();
            return new Money(inputMoney);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getMoney();
        }
    }

    private WinningNumbers getWinningNumbers() {
        try {
            return new WinningNumbers(InputView.inputWinningLotto(), InputView.inputBonusNumber());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getWinningNumbers();
        }
    }
}
