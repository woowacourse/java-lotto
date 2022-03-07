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
        buyManualLotto(money);
        lottos.purchaseAutoLotto(money);
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

    private void buyManualLotto(Money money) {
        int passiveLottoCount = getManualPurchase(money);
        purchaseManualLotto(passiveLottoCount);
        money.buyLotto(passiveLottoCount);
    }

    private int getManualPurchase(Money money) {
        try {
            outputView.printAskManualPurchaseCountInputMessage();
            int manualPurchaseNumberInput = Integer.parseInt(inputView.getInput());
            money.validatePurchasableNumberInput(manualPurchaseNumberInput);
            return manualPurchaseNumberInput;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getManualPurchase(money);
        }
    }

    private void purchaseManualLotto(int passiveLottoCount) {
        for (int i = 0; i < passiveLottoCount; i++) {
            lottos.purchaseLotto(getManualPickedNumber());
        }
    }

    private WinningLotto getWinningLotto() {
        PickedNumbers pickedNumbers = getPickedNumber();
        BonusNumber bonusNumber = getBonusNumber(pickedNumbers);
        return new WinningLotto(pickedNumbers, bonusNumber);
    }

    private PickedNumbers getManualPickedNumber() {
        try {
            outputView.printManualPurchaseInputMessage();
            return new PickedNumbers(inputView.getInput());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getManualPickedNumber();
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
