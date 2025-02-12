import controller.PurchaseController;
import controller.WinLottoController;
import model.PurchasedLottos;

public class LottoApplication {
    private final PurchaseController purchaseController = new PurchaseController();
    private final WinLottoController winLottoController = new WinLottoController();

    public static void main(String[] args) {
        new LottoApplication().run();
    }

    private void run() {
        PurchasedLottos purchase = purchaseController.purchase();
        winLottoController.winLotto();
    }
}
