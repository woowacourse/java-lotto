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
        Lottos lottos = new Lottos(inputMoney());
        buyLottosByManual(lottos, inputCount());
        lottos.buyAllLottosByAuto();
//        WinningNumbers winningNumbers = inputWinningNumbers();
//        printWinningStatistic(money, lottos, winningNumbers);
    }

    private Money inputMoney() {
        try {
            return Money.generateMoneyByString(InputView.inputMoney());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return inputMoney();
        }
    }

    private int inputCount() {
        try {
            return Integer.parseInt(InputView.inputCount());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return inputCount();
        }
    }

    private void buyLottosByManual(Lottos lottos, int count) {
        InputView.printInputLottoNumbersMessage();
        IntStream.range(0, count)
                .forEach(integer -> lottos.buyLottoByManual(inputLotto()));
    }

    private Lotto inputLotto() {
        try {
            return Lotto.generateLottoByString(InputView.inputNextLine());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return inputLotto();
        }
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
        String profitRate = money.toStringProfitRateUntilSecondDecimal(rankCount.getTotalPrize());
        OutputView.printWinningStatistic(rankCount, profitRate);
    }
}
