package lotto.controller;

import static lotto.view.InputView.*;
import static lotto.view.OutputView.*;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Count;
import lotto.domain.LottoResult;
import lotto.domain.Lotto;
import lotto.domain.LottoDto;
import lotto.domain.Money;
import lotto.domain.ChoiceNumber;
import lotto.domain.WinningNumber;
import lotto.strategy.AutoBuy;
import lotto.view.InputView;

public class LottoController {
    private Money money;
    private Lotto lotto;
    private Count manualCount;
    private Count autoCount;

    public void start() {
        money = initMoney();
        initCount();
        initLotto();
        WinningNumber winningNumber = setWinningNumber();
        findAndPrintResult(winningNumber);
    }

    private Money initMoney() {
        try {
            int moneyInput = askMoneyInput();
            return new Money(moneyInput);
        } catch (IllegalArgumentException exception) {
            printErrorMessage(exception);
            return initMoney();
        }
    }

    private void initCount() {
        manualCount = initManualCount();
        autoCount = new Count(money.getAmount() / Money.UNIT_AMOUNT - manualCount.getCount());
    }

    private Count initManualCount() {
        try {
            int inputValue = InputView.askManualCountInput();
            return new Count(money, inputValue);
        } catch (IllegalArgumentException exception) {
            printErrorMessage(exception);
            return initManualCount();
        }
    }

    private void initLotto() {
        AutoBuy autoBuy = new AutoBuy();
        List<ChoiceNumber> manualNumbers = initManualNumbers(manualCount.getCount());
        this.lotto = new Lotto(autoCount.getCount(), manualNumbers, autoBuy);
        printLotto(LottoDto.from(this.lotto), manualCount.getCount(), autoCount.getCount());
    }

    private List<ChoiceNumber> initManualNumbers(int count) {
        try {
            List<List<Integer>> inputValues = askManualNumbers(count);
            List<ChoiceNumber> manualNumbers = new ArrayList<>();
            for (List<Integer> inputValue : inputValues) {
                manualNumbers.add(new ChoiceNumber(inputValue));
            }
            return manualNumbers;
        } catch (IllegalArgumentException exception) {
            printErrorMessage(exception);
            return initManualNumbers(count);
        }
    }

    private WinningNumber setWinningNumber() {
        ChoiceNumber choiceNumber = initChoiceNumber();
        BonusNumber bonusNumber = initBonusNumber(choiceNumber);
        return new WinningNumber(choiceNumber, bonusNumber);
    }

    private ChoiceNumber initChoiceNumber() {
        try {
            List<Integer> inputValue = askWinningNumbers();
            return new ChoiceNumber(inputValue);
        } catch (IllegalArgumentException exception) {
            printErrorMessage(exception);
            return initChoiceNumber();
        }
    }

    private BonusNumber initBonusNumber(ChoiceNumber choiceNumber) {
        try {
            int inputValue = askBonusNumber();
            return new BonusNumber(inputValue, choiceNumber);
        } catch (IllegalArgumentException exception) {
            printErrorMessage(exception);
            return initBonusNumber(choiceNumber);
        }
    }

    private void findAndPrintResult(WinningNumber winningNumber) {
        LottoResult result = lotto.computeResult(winningNumber);
        printResult(result);
        printYield(lotto.getYield(money));
    }
}
