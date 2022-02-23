package lotto.controller;

import java.util.Scanner;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {

    public void run(Scanner scanner) {
        Money money = getMoney(scanner);
        Lottos lottos = Lottos.buyLottosByAuto(money);
        OutputView.printTotalLottoCount(lottos.getTotalLottoCount());
        OutputView.printLottos(lottos);
        WinningNumbers winningNumbers = getWinningNumbers(scanner);
    }

    private Money getMoney(Scanner scanner) {
        try {
            return new Money(InputView.inputMoney(scanner));
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getMoney(scanner);
        }
    }

    private WinningNumbers getWinningNumbers(Scanner scanner) {
        try {
            return new WinningNumbers(InputView.inputWinningLotto(scanner), InputView.inputBonusNumber(scanner));
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getWinningNumbers(scanner);
        }
    }
}
