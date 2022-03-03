package lotto;

import lotto.controller.PurchaseController;
import lotto.controller.WinningController;
import lotto.dto.PurchaseResult;

public class Application {
    public static void main(String[] args) {
        PurchaseController purchaseController = PurchaseController.getInstance();
        PurchaseResult purchaseResult = purchaseController.purchase();

        WinningController winningController = WinningController.getInstance();
        winningController.drawLots(purchaseResult);
    }
}
