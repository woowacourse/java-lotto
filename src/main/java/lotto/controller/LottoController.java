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
        outputView.printLotto(LottoDto.from(lotto));
        WinningNumber winningNumber = setWinningNumber();
        LottoResult result = lotto.computeResult(winningNumber);
        outputView.printResult(result);
        outputView.printYield(lotto.getYield());
    }

    private WinningNumber setWinningNumber() {
        ChoiceNumber choiceNumber = initChoiceNumber();
        BonusNumber bonusNumber = initBonusNumber(choiceNumber);
        WinningNumber winningNumber = new WinningNumber(choiceNumber, bonusNumber);
        return winningNumber;
    }

    private Money initMoney() {
        try {
            String inputValue = inputView.askMoneyInput();
            Money money = new Money(inputValue);
            return money;
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            return initMoney();
        }
    }

    private ChoiceNumber initChoiceNumber() {
        try {
            String inputValue = inputView.askWinningNumbers();
            ChoiceNumber choiceNumber = new ChoiceNumber(inputValue);
            return choiceNumber;
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            return initChoiceNumber();
        }
    }

    private BonusNumber initBonusNumber(ChoiceNumber choiceNumber) {
        try {
            String inputValue = inputView.askBonusNumber();
            BonusNumber bonusNumber = new BonusNumber(inputValue, choiceNumber);
            return bonusNumber;
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            return initBonusNumber(choiceNumber);
        }

    }
}
