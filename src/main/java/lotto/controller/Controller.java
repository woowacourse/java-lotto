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
        buyLottosByManual(lottos);

//        buyLottosByAuto(lottos, money);
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

    private void buyLottosByManual(Lottos lottos) {
        int countForManualLotto = inputCountForManualLotto();
        InputView.printInputLottoNumbersMessage();
        IntStream.range(0, countForManualLotto)
                .forEach(integer -> lottos.buyLottos(inputLotto()));
    }

    private int inputCountForManualLotto() {
        try {
            return Integer.parseInt(InputView.inputCountForManualLotto());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return inputCountForManualLotto();
        }
    }

    private Lotto inputLotto() {
        try {
            return Lotto.generateLottoByString(InputView.inputNextLine());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return inputLotto();
        }
    }


    private Lottos buyLottosByAuto(Lottos lottos) {
        lottos.buyLottosByAuto();
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
        String profitRate = money.toStringProfitRateUntilSecondDecimal(rankCount.getTotalPrize());
        OutputView.printWinningStatistic(rankCount, profitRate);
    }
}
