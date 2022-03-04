package lotto.controller;

import lotto.domain.BonusNumber;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.PickedNumbers;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Lottos lottos;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottos = new Lottos();
    }

    public void start() {
        Money money = getMoney();
        buyPassiveLotto(money);
        lottos.purchaseActiveLotto(money);
        outputView.printPurchasedLotto(lottos);
        outputView.printResult(lottos.getResult(getWinningLotto()));
        outputView.printYield(lottos.getYield(money));
        inputView.terminate();
    }

    private Money getMoney() {
        try {
            outputView.printAskMoneyInputMessage();
            return new Money(inputView.getInput());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getMoney();
        }
    }

    private void buyPassiveLotto(Money money) {
        int passiveLottoCount = getPassivePurchase();
        purchasePassiveLotto(passiveLottoCount);
        money.buyLotto(passiveLottoCount);
    }

    private int getPassivePurchase() {
        try {
            outputView.printAskPassivePurchaseCountInputMessage();
            return Integer.parseInt(inputView.getInput());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getPassivePurchase();
        }
    }

    private void purchasePassiveLotto(int passiveLottoCount) {
        for (int i = 0; i < passiveLottoCount; i++) {
            lottos.purchaseLotto(getPassivePickedNumber());
        }
    }

    private WinningLotto getWinningLotto() {
        PickedNumbers pickedNumbers = getPickedNumber();
        BonusNumber bonusNumber = getBonusNumber(pickedNumbers);
        return new WinningLotto(pickedNumbers, bonusNumber);
    }

    private PickedNumbers getPassivePickedNumber() {
        try {
            outputView.printPassivePurchaseInputMessage();
            return new PickedNumbers(inputView.getInput());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getPassivePickedNumber();
        }
    }

    private PickedNumbers getPickedNumber() {
        try {
            outputView.printLastWeekWinningMessage();
            return new PickedNumbers(inputView.getInput());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getPickedNumber();
        }
    }

    private BonusNumber getBonusNumber(PickedNumbers pickedNumbers) {
        try {
            outputView.printLastWeekBonusMessage();
            return new BonusNumber(inputView.getInput(), pickedNumbers);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getBonusNumber(pickedNumbers);
        }
    }
}
