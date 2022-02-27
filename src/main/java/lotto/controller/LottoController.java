package lotto.controller;

import lotto.domain.BonusNumber;
import lotto.strategy.LottoBuyStrategy;
import lotto.domain.LottoResult;
import lotto.domain.Lotto;
import lotto.domain.LottoDto;
import lotto.domain.Money;
import lotto.domain.ChoiceNumber;
import lotto.domain.WinningNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoBuyStrategy lottoBuyStrategy;
    private Lotto lotto;

    public LottoController(LottoBuyStrategy lottoBuyStrategy) {
        this.inputView = new InputView();
        this.outputView = new OutputView();
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
            String inputValue = inputView.askMoneyInput();
            return new Money(inputValue);
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            return initMoney();
        }
    }

    private void initLotto(Money money) {
        lotto = new Lotto(money,lottoBuyStrategy);
        outputView.printLotto(LottoDto.from(lotto));
    }

    private WinningNumber setWinningNumber() {
        ChoiceNumber choiceNumber = initChoiceNumber();
        BonusNumber bonusNumber = initBonusNumber(choiceNumber);
        return new WinningNumber(choiceNumber, bonusNumber);
    }

    private ChoiceNumber initChoiceNumber() {
        try {
            String inputValue = inputView.askWinningNumbers();
            return new ChoiceNumber(inputValue);
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            return initChoiceNumber();
        }
    }

    private BonusNumber initBonusNumber(ChoiceNumber choiceNumber) {
        try {
            String inputValue = inputView.askBonusNumber();
            return new BonusNumber(inputValue, choiceNumber);
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            return initBonusNumber(choiceNumber);
        }
    }

    private void findAndPrintResult(WinningNumber winningNumber) {
        LottoResult result = lotto.computeResult(winningNumber);
        outputView.printResult(result);
        outputView.printYield(lotto.getYield());
    }
}
