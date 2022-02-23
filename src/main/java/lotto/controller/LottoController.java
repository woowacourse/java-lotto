package lotto.controller;

import lotto.domain.BonusNumber;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.PickedNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private InputView inputView;
    private OutputView outputView;
    private Money money;
    private Lottos lottos;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void start() {
        money = getMoney();
        lottos = new Lottos(money);
        outputView.printPurchasedLotto(lottos);
        PickedNumbers pickedNumbers = getPickedNumber();
        BonusNumber bonusNumber = getBonusNumber(pickedNumbers);

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

    private Money getMoney() {
        try {
            outputView.printAskMoneyInputMessage();
            Money money = new Money(inputView.getInput());
            return money;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getMoney();
        }
    }
}
