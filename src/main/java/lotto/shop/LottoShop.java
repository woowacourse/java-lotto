package lotto.shop;

import lotto.controller.AutoPurchaseController;
import lotto.controller.InputWinningController;
import lotto.view.ErrorView;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoShop implements Shop {

    private final InputView inputView;
    private final OutputView outputView;
    private final ErrorView errorView;
    private final AutoPurchaseController purchaseController;
    private final InputWinningController inputWinningController;

    public LottoShop(InputView inputView, OutputView outputView, ErrorView errorView,
        AutoPurchaseController purchaseController, InputWinningController inputWinningController) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.errorView = errorView;
        this.purchaseController = purchaseController;
        this.inputWinningController = inputWinningController;
    }

    @Override
    public void operate() {
        purchaseLotto();
        compareTickets();
    }

    private void purchaseLotto() {
        try {
            String inputMoney = inputView.inputMoney();
            outputView.outputTickets(purchaseController.purchase(inputMoney));
        } catch (IllegalArgumentException e) {
            errorView.error(e.getMessage());
            purchaseLotto();
        }
    }

    private void compareTickets() {
        try {
            String inputWinningNumber = inputView.inputWinningNumber();
            String inputBonusBall = inputView.inputBonusBall();
            outputView.outputStatistics(
                inputWinningController.compareWinningNumber(inputWinningNumber, inputBonusBall)
            );
        } catch (IllegalArgumentException e) {
            errorView.error(e.getMessage());
            compareTickets();
        }
    }
}