package lotto.controller;

import java.util.Scanner;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.exception.MoneyException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {

    public void run(Scanner scanner) {
        Money money = getMoney(scanner);
        Lottos lottos = Lottos.buyLottosByAuto(money);
        OutputView.printTotalLottoCount(lottos.getTotalLottoCount());
        OutputView.printLottos(lottos);
    }

    private Money getMoney(Scanner scanner) {
        try {
            return new Money(InputView.inputMoney(scanner));
        } catch (MoneyException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getMoney(scanner);
        }
    }
}
