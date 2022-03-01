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

    public void start() {
        money = initMoney();
        manualCount = initManualCount();
        initLotto();
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

    private Count initManualCount() {
        try {
            String inputValue = InputView.askManualCountInput();
            return new Count(money, inputValue);
        } catch (IllegalArgumentException exception) {
            printErrorMessage(exception);
            return initManualCount();
        }
    }

    private void initLotto() {
        AutoBuy autoBuy = new AutoBuy();
        List<ChoiceNumber> manualNumbers = initManualNumbers(manualCount.getCount());
        this.lotto = new Lotto(money, manualNumbers, autoBuy);
        printLotto(LottoDto.from(this.lotto), manualCount.getCount(), money.getCount() - manualCount.getCount());
    }

    private List<ChoiceNumber> initManualNumbers(int count) {
        try {
            List<String> inputValues = InputView.askManualNumbers(count);
            List<ChoiceNumber> manualNumbers = new ArrayList<>();
            for (String inputValue : inputValues) {
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
