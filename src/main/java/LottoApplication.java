import controller.PurchaseController;
import model.PurchasedLottos;

public class LottoApplication {
    PurchaseController purchaseController = new PurchaseController();

    public static void main(String[] args) {
        new LottoApplication().run();
    }

    private void run() {
        PurchasedLottos purchase = purchaseController.purchase();
    }
}
