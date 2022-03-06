package old;

import old.controller.PurchaseController;
import old.controller.WinningController;
import old.dto.PurchaseResult;

public class Application {
    public static void main(String[] args) {
        PurchaseController purchaseController = PurchaseController.getInstance();
        PurchaseResult purchaseResult = purchaseController.purchase();

        WinningController winningController = WinningController.getInstance();
        winningController.drawLots(purchaseResult);
    }
}
