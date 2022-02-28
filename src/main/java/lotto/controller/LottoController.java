package lotto.controller;

import static lotto.view.InputView.*;
import static lotto.view.OutputView.*;

import lotto.domain.BonusNumber;
import lotto.strategy.LottoBuyStrategy;
import lotto.domain.LottoResult;
import lotto.domain.Lotto;
import lotto.domain.LottoDto;
import lotto.domain.Money;
import lotto.domain.ChoiceNumber;
import lotto.domain.WinningNumber;

public class LottoController {
    private final LottoBuyStrategy lottoBuyStrategy;
    private Lotto lotto;

    public LottoController(LottoBuyStrategy lottoBuyStrategy) {
        this.lottoBuyStrategy = lottoBuyStrategy;
    }

    public void start() {
        Money money = initMoney();
        initLotto(money);
        WinningNumber winningNumber = setWinningNumber();
        findAndPrintResult(winningNumber);
    }

    private Money initMoney() {
        try {
            String inputValue = askMoneyInput();
            return new Money(inputValue);
        } catch (IllegalArgumentException exception) {
            printErrorMessage(exception);
            return initMoney();
        }
    }

    private void initLotto(Money money) {
        lotto = new Lotto(money, lottoBuyStrategy);
        printLotto(LottoDto.from(lotto));
    }

    private WinningNumber setWinningNumber() {
        ChoiceNumber choiceNumber = initChoiceNumber();
        BonusNumber bonusNumber = initBonusNumber(choiceNumber);
        return new WinningNumber(choiceNumber, bonusNumber);
    }

    private ChoiceNumber initChoiceNumber() {
        try {
            String inputValue = askWinningNumbers();
            return new ChoiceNumber(inputValue);
        } catch (IllegalArgumentException exception) {
            printErrorMessage(exception);
            return initChoiceNumber();
        }
    }

    private BonusNumber initBonusNumber(ChoiceNumber choiceNumber) {
        try {
            String inputValue = askBonusNumber();
            return new BonusNumber(inputValue, choiceNumber);
        } catch (IllegalArgumentException exception) {
            printErrorMessage(exception);
            return initBonusNumber(choiceNumber);
        }
    }

    private void findAndPrintResult(WinningNumber winningNumber) {
        LottoResult result = lotto.computeResult(winningNumber);
        printResult(result);
        printYield(lotto.getYield());
    }
}
