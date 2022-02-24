package lotto.controller;

import lotto.domain.BonusNumber;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.ChoiceNumber;
import lotto.domain.WinningNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private InputView inputView;
    private OutputView outputView;
    private Lottos lottos;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void start() {
        Money money = initMoney();
        lottos = new Lottos(money);
        outputView.printPurchasedLotto(lottos);
        ChoiceNumber choiceNumber = getPickedNumber();
        BonusNumber bonusNumber = getBonusNumber(choiceNumber);
        WinningNumber winningNumber = new WinningNumber(choiceNumber, bonusNumber);
        LottoResult result = lottos.getResult(winningNumber);
        outputView.printResult(result);
        outputView.printYield(lottos.getYield());
    }

    private Money initMoney() {
        try {
            outputView.printAskMoneyInputMessage();
            Money money = new Money(inputView.getInput());
            return money;
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            return initMoney();
        }
    }

    private ChoiceNumber getPickedNumber() {
        try {
            outputView.printLastWeekWinningMessage();
            ChoiceNumber choiceNumber = new ChoiceNumber(inputView.getInput());
            return choiceNumber;
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            return getPickedNumber();
        }
    }

    private BonusNumber getBonusNumber(ChoiceNumber choiceNumber) {
        try {
            outputView.printLastWeekBonusMessage();
            BonusNumber bonusNumber = new BonusNumber(inputView.getInput(), choiceNumber);
            return bonusNumber;
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            return getBonusNumber(choiceNumber);
        }

    }
}
