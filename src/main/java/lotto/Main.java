package lotto;

import lotto.controller.InputViewController;
import lotto.controller.LottoController;
import lotto.controller.MoneyController;
import lotto.controller.OutputViewController;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.WinnerNumber;
import lotto.view.InputView;

import java.util.List;

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
        List<Rank> ranks = lottoController.getResult(myLotto, winnerNumber);
        double returnRate = lottoController.getReturnRate(ranks, money);
        outputViewController.printResult(ranks, returnRate);

    }
}



