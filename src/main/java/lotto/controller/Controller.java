package lotto.controller;

import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.RankCount;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {

    public void run() {
        Lottos lottos = new Lottos();
        Money money = inputMoney();
        buyLottosByManual(InputView.inputCountForBuy(), lottos, money);
        buyAllLottosByAuto(lottos, money);
        OutputView.printLottos(lottos);
        printWinningStatistic(lottos,money);
    }

    private Money inputMoney() {
        try {
            return Money.generateMoneyByString(InputView.inputMoney());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return inputMoney();
        }
    }

    private void buyLottosByManual(int countForBuy, Lottos lottos, Money money) {
        if (countForBuy == 0) {
            return;
        }
        try {
            InputView.printInputLottoNumbersMessage();
            IntStream.range(0, countForBuy).forEach(integer -> lottos.buyLottoByManual(inputLotto(), money));
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
        }
    }

    private Lotto inputLotto() {
        try {
            return Lotto.generateLottoByString(InputView.inputLottoNumbers());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return inputLotto();
        }
    }

    private void buyAllLottosByAuto(Lottos lottos, Money money) {
        try {
            lottos.buyAllLottosByAuto(money);
        } catch (IllegalArgumentException exception) {
        }
    }

    private void printWinningStatistic(Lottos lottos, Money money) {
        WinningNumbers winningNumbers = inputWinningNumbers();
        RankCount rankCount = new RankCount(lottos, winningNumbers);
        double profitRate = money.getProfitRate(rankCount.getTotalPrize());
        OutputView.printWinningStatistic(rankCount, profitRate);
    }

    private WinningNumbers inputWinningNumbers() {
        try {
            return WinningNumbers.generateWinningNumbersByString(
                    InputView.inputWinningLotto(),
                    InputView.inputBonusNumber()
            );
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return inputWinningNumbers();
        }
    }
}
