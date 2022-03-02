package lotto.controller;

import lotto.domain.BonusNumber;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.PickedNumbers;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private static final int UNIT_PRICE = 1000;
    private final InputView inputView;
    private final OutputView outputView;
    private Lottos lottos;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void start() {
        Money money = getMoney();
        purchaseLotto(money);
        WinningLotto winningLotto = getWinningLotto();
        outputView.printResult(lottos.getResult(winningLotto));
        outputView.printYield(lottos.getYield(money));
        inputView.terminate();
    }

    private WinningLotto getWinningLotto() {
        PickedNumbers pickedNumbers = getPickedNumber();
        BonusNumber bonusNumber = getBonusNumber(pickedNumbers);
        WinningLotto winningLotto = new WinningLotto(pickedNumbers, bonusNumber);
        return winningLotto;
    }

    private void purchaseLotto(Money money) {
        lottos = new Lottos(money);
        outputView.printPurchasedLotto(lottos);
    }

    private Money getMoney() {
        try {
            outputView.printAskMoneyInputMessage();
            Money money = new Money(inputView.getInput(), UNIT_PRICE);
            return money;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getMoney();
        }
    }

    private PickedNumbers getPickedNumber() {
        try {
            outputView.printLastWeekWinningMessage();
            PickedNumbers pickedNumbers = new PickedNumbers(inputView.getInput());
            return pickedNumbers;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getPickedNumber();
        }
    }

    private BonusNumber getBonusNumber(PickedNumbers pickedNumbers) {
        try {
            outputView.printLastWeekBonusMessage();
            BonusNumber bonusNumber = new BonusNumber(inputView.getInput(), pickedNumbers);
            return bonusNumber;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getBonusNumber(pickedNumbers);
        }
    }
}
