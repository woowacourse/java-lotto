package lotto;

import lotto.controller.InputViewController;
import lotto.controller.LottoController;
import lotto.controller.MoneyController;
import lotto.controller.OutputViewController;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.WinnerNumber;
import lotto.view.InputView;

public class Main {
    public static void main(String[] args) {
        MoneyController moneyController = new MoneyController();
        InputViewController inputController = new InputViewController(new InputView());

        int money = inputController.inputMoney();
        Money round = moneyController.getMoney(money);

        LottoController lottoController = new LottoController();
        Lottos myLotto = lottoController.buyLottos(round.getRound());

        OutputViewController outputViewController = new OutputViewController();
        outputViewController.printLottos(myLotto);

        WinnerNumber winnerNumber = WinnerNumber.create(inputController.inputWinnerNumbers());
    }
}



