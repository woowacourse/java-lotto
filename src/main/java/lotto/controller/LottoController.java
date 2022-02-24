package lotto.controller;

import lotto.domain.BonusNumber;
import lotto.domain.LottoResult;
import lotto.domain.Lotto;
import lotto.domain.LottoDto;
import lotto.domain.Money;
import lotto.domain.ChoiceNumber;
import lotto.domain.WinningNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private InputView inputView;
    private OutputView outputView;
    private Lotto lotto;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void start() {
        Money money = initMoney();
        lotto = new Lotto(money);
        outputView.printPurchasedLotto(LottoDto.from(lotto));
        ChoiceNumber choiceNumber = initChoiceNumber();
        BonusNumber bonusNumber = initBonusNumber(choiceNumber);
        WinningNumber winningNumber = new WinningNumber(choiceNumber, bonusNumber);
        LottoResult result = lotto.computeResult(winningNumber);
        outputView.printResult(result);
        outputView.printYield(lotto.getYield());
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

    private ChoiceNumber initChoiceNumber() {
        try {
            outputView.printLastWeekWinningMessage();
            ChoiceNumber choiceNumber = new ChoiceNumber(inputView.getInput());
            return choiceNumber;
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            return initChoiceNumber();
        }
    }

    private BonusNumber initBonusNumber(ChoiceNumber choiceNumber) {
        try {
            outputView.printLastWeekBonusMessage();
            BonusNumber bonusNumber = new BonusNumber(inputView.getInput(), choiceNumber);
            return bonusNumber;
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            return initBonusNumber(choiceNumber);
        }

    }
}
