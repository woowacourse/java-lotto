package lotto;

import lotto.controller.*;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.WinnerNumber;
import lotto.view.InputView;

import java.util.List;

public class Main {
    private static MoneyController moneyController;
    private static LottoController lottoController;
    private static OutputViewController outputViewController;
    private static InputViewController inputController;
    private static WinnerNumberController winnerNumberController;

    private static void init() {
        moneyController = new MoneyController();
        lottoController = new LottoController();
        outputViewController = new OutputViewController();
        inputController = new InputViewController(new InputView());
        winnerNumberController = new WinnerNumberController();
    }

    public static void main(String[] args) {
        init();

        int money = inputController.inputMoney();
        Money round = moneyController.getMoney(money);
        Lottos myLotto = lottoController.buyLottos(round.getRound());
        outputViewController.printLottos(myLotto);

        WinnerNumber winnerNumber = winnerNumberController.makeWinnerNumber(inputController.inputWinnerNumbers());
        List<Rank> ranks = lottoController.getResult(myLotto, winnerNumber);
        outputViewController.printResult(ranks, lottoController.getReturnRate(ranks, money));
    }
}



