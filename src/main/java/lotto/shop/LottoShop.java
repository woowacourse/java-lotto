package lotto.shop;

import java.util.List;

import lotto.controller.AutoPurchaseController;
import lotto.controller.InputWinningController;
import lotto.controller.ManualPurchaseController;
import lotto.controller.MoneyController;
import lotto.dto.LottoTicketResponse;
import lotto.view.ErrorView;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoShop implements Shop {

    private final InputView inputView;
    private final OutputView outputView;
    private final ErrorView errorView;
    private final MoneyController moneyController;
    private final ManualPurchaseController manualPurchaseController;
    private final AutoPurchaseController autoPurchaseController;
    private final InputWinningController inputWinningController;

    public LottoShop(InputView inputView, OutputView outputView, ErrorView errorView,
        MoneyController moneyController, ManualPurchaseController manualPurchaseController,
        AutoPurchaseController autoPurchaseController, InputWinningController inputWinningController) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.errorView = errorView;
        this.moneyController = moneyController;
        this.manualPurchaseController = manualPurchaseController;
        this.autoPurchaseController = autoPurchaseController;
        this.inputWinningController = inputWinningController;
    }

    @Override
    public void operate() {
        insertMoney();
        purchaseLotto();
        compareTickets();
    }

    private void insertMoney() {
        try {
            String inputMoney = inputView.inputMoney();
            moneyController.insertMoney(inputMoney);
        } catch (IllegalArgumentException e) {
            errorView.error(e.getMessage());
            insertMoney();
        }
    }

    private void purchaseLotto() {
        try {
            List<String> inputLottoNumbers = inputView.inputLottoNumbers();
            List<LottoTicketResponse> manualLottoTickets = manualPurchaseController.purchase(inputLottoNumbers);
            List<LottoTicketResponse> autoLottoTickets = autoPurchaseController.purchase(manualLottoTickets.size());

            outputView.outputTickets(manualLottoTickets, autoLottoTickets);
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