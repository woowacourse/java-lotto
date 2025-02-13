import controller.PurchaseController;
import controller.ResultController;
import controller.WinLottoController;
import model.PurchasedLottos;
import model.WinLotto;

public class LottoApplication {
    private final PurchaseController purchaseController = new PurchaseController();
    private final WinLottoController winLottoController = new WinLottoController();
    private final ResultController resultController = new ResultController();

    public static void main(String[] args) {
        new LottoApplication().run();
    }

    private void run() {
        PurchasedLottos purchase = purchaseController.purchase();
        WinLotto winLotto = winLottoController.winLotto();
        resultController.lottoResult(purchase, winLotto);
    }
}
